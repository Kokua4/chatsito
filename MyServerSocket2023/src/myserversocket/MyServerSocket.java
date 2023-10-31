/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package myserversocket;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyServerSocket {
    private static final int PORT = 12345;
    private static CopyOnWriteArrayList<MiNegocio> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor conectado, conectate a: " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Usuario extra");
                MiNegocio client = new MiNegocio(clientSocket);
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String message, MiNegocio sender) {
        for (MiNegocio client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
}
