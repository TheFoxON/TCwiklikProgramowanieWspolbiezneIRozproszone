import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            System.out.println("Server running. Waiting for connection...");

            try (Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Client connected.");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Recieved: " + inputLine);
                    String[] tokens = inputLine.split(" ");
                    try {
                        double num1 = Double.parseDouble(tokens[0]);
                        double num2 = Double.parseDouble(tokens[1]);
                        String operation = tokens[2];
                        double result = 0;

                        switch (operation) {
                            case "add":
                                result = num1 + num2;
                                break;
                            case "subtract":
                                result = num1 - num2;
                                break;
                            case "multiply":
                                result = num1 * num2;
                                break;
                            case "divide":
                                if (num2 != 0) {
                                    result = num1 / num2;
                                } else {
                                    out.println("Error: Divisioned by zero!");
                                    continue;
                                }
                                break;
                            default:
                                out.println("Unknown operation");
                                continue;
                        }

                        if (tokens[3].equals("integers") && result == (int) result) {
                            out.println((int) result);
                        } else {
                            out.println(result);
                        }
                    } catch (NumberFormatException e) {
                        out.println("Error: Invalid numbers");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Error: Incomplete data");
                    }
                }
            } catch (IOException e) {
                System.out.println("Server Exception:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
