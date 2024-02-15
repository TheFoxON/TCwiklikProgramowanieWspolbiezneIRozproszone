import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class TurnBasedGameServer {
    private static final int PORT = 12345;
    private static final int MAX_HP = 100;
    private static final int MAX_ROLL = 20;
    private static final int MAX_DEFENSE_ROLL = 12;
    private static final int MAX_EVADE_ROLL = 10;
    private static final int MAX_HEAL_ROLL = 6;

    private int playerHP;
    private int enemyHP;

    public TurnBasedGameServer() {
        playerHP = MAX_HP;
        enemyHP = MAX_HP;
    }

    public static void main(String[] args) {
        TurnBasedGameServer server = new TurnBasedGameServer();
        server.start();
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for connections...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    out.println("Welcome to the Turn-Based arena duel game! Your opponent is ready.");
                    while (playerHP > 0 && enemyHP > 0) {
                        out.println("Your turn. Choose your action: Attack, Defend, Evade, Heal");
                        String action = in.readLine().trim().toLowerCase();

                        switch (action) {
                            case "attack":
                                int damageDealt = rollDice(MAX_ROLL);
                                enemyHP -= damageDealt;
                                out.println("You attacked and dealt " + damageDealt + " damage to the enemy.");
                                break;
                            case "defend":
                                int defensePoints = rollDice(MAX_DEFENSE_ROLL);
                                playerHP -= defensePoints / 2; // Reduce incoming damage
                                out.println("You defended and reduced incoming damage by " + (defensePoints / 2) + ".");
                                break;
                            case "evade":
                                int evadeRoll = rollDice(MAX_EVADE_ROLL);
                                if (evadeRoll > 5) {
                                    out.println("You successfully evaded the enemy's attack.");
                                } else {
                                    int damageTaken = rollDice(MAX_ROLL);
                                    playerHP -= damageTaken;
                                    out.println("You failed to evade. You took " + damageTaken + " damage.");
                                }
                                break;
                            case "heal":
                                int healAmount = rollDice(MAX_HEAL_ROLL);
                                playerHP = Math.min(playerHP + healAmount, MAX_HP);
                                out.println("You healed yourself for " + healAmount + " points.");
                                break;
                            default:
                                out.println("Invalid action. Try again.");
                        }

                        out.println("Enemy's turn...");

                        String enemyAction = scanner.nextLine().trim().toLowerCase();
                        switch (enemyAction) {
                            case "attack": // Attack
                                int enemyDamage = rollDice(MAX_ROLL);
                                playerHP -= enemyDamage;
                                out.println("Enemy attacked and dealt " + enemyDamage + " damage to you.");
                                System.out.println("You attacked and dealt " + enemyDamage + " damage to your enemy.");
                                break;
                            case "defend":// Defend
                                int enemyDefensePoints = rollDice(MAX_DEFENSE_ROLL);
                                enemyHP -= enemyDefensePoints / 2; // Reduce incoming damage
                                out.println("Enemy defended and reduced incoming damage by " + (enemyDefensePoints / 2) + ".");
                                System.out.println("You defended and reduced incoming damage by " + (enemyDefensePoints / 2) + ".");
                                break;
                            case "evade":// Evade
                                int enemyEvadeRoll = rollDice(MAX_EVADE_ROLL);
                                if (enemyEvadeRoll > 5) {
                                    out.println("Enemy successfully evaded your attack.");
                                    System.out.println("You successfully evaded the enemy's attack.");
                                } else {
                                    int enemyDamageTaken = rollDice(MAX_ROLL);
                                    enemyHP -= enemyDamageTaken;
                                    out.println("Enemy failed to evade. You dealt " + enemyDamageTaken + " damage.");
                                    System.out.println("You failed to evade. You got " + enemyDamageTaken + " damage.");
                                }
                                break;
                            case "heal":// Heal
                                int enemyHealAmount = rollDice(MAX_HEAL_ROLL);
                                enemyHP = Math.min(enemyHP + enemyHealAmount, MAX_HP);
                                out.println("Enemy healed themselves for " + enemyHealAmount + " points.");
                                System.out.println("You healed yourself for " + enemyHealAmount + " points.");
                                break;
                            }
                        out.println("Your current HP: " + playerHP + " Enemy's current HP: " + enemyHP);
                        System.out.println("Your current HP: " + playerHP + " Enemy's current HP: " + enemyHP);
                        
                        out.println("══════════════════════════════════════════════════════════");
                    }

                    if (playerHP <= 0) {
                        out.println("You lose! Game Over.");
                    } else {
                        out.println("Congratulations! You defeated the enemy. You win!");
                    }
                    scanner.close();
                    out.close();
                    in.close();
                    socket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private int rollDice(int max) {
        Random random = new Random();
        return random.nextInt(max) + 1;
    }
}
