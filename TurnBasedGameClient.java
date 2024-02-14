import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TurnBasedGameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");
            System.out.println("Server: " + in.readLine());

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server: " + response);

                if (response.contains("Game Over")) {
                    break;
                }

                if (response.contains("Your turn")) {
                    System.out.print("Enter your action: ");
                    String action = scanner.nextLine().trim().toLowerCase();
                    out.println(action);
                }
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
