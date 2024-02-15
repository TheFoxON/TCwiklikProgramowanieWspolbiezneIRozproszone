import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TurnBasedGameClient {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        String action = "";
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj adres IP serwera:");
            String ip = scanner.nextLine();
            Socket socket = new Socket( ip, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            System.out.println("Connected to the server.");
            System.out.println("Server: " + in.readLine());

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server: " + response);

                if (response.contains("Game Over")) {
                    break;
                }

                if (response.contains("Your turn")) {
                    while (!(action.equals("attack")|| action.equals("defend") || action.equals("evade") ||action.equals("heal"))){

                    System.out.print("Enter your action: ");
                    action = scanner.nextLine().trim().toLowerCase();
                    }

                    out.println(action);
                    action = "";
                }
            }
            out.close();
            in.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

}
