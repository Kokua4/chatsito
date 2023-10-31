package myclientsocket;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClientSocket {
     private static final String SERVER_IP = "192.168.20.83"; // Reemplaza con la dirección IP del servidor
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Hilo para enviar mensajes al servidor
            Thread sendMessageThread = new Thread(() -> {
                try {System.out.println("Yo: ");
                    String userInput;
                    while (true) {
                        userInput = consoleInput.readLine();
                        if ("exit".equalsIgnoreCase(userInput)) {
                            out.println(userInput);
                            break;
                        } else if (!userInput.isEmpty()) {
                            out.println(protocolo.eco(new textito(userInput)).resultado1.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendMessageThread.start();

            String response;
            while ((response = in.readLine()) != null) {
                
                System.out.println("Personita: " + protocolo.deco(new textito(response)).resultado1.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }