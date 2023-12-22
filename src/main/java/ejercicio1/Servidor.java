package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6565;

        try {
            // Crear el socket del servidor
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("ejercicio1.Servidor esperando conexiones en el puerto " + PUERTO);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clientSocket = serverSocket.accept();
                System.out.println("ejercicio1.Cliente conectado desde " + clientSocket.getInetAddress());

                // Configurar streams de entrada y salida
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream outputStream = clientSocket.getOutputStream();

                // Recibir datos del cliente (10 bytes)
                char[] buffer = new char[10];
                int bytesRead = reader.read(buffer);

                if (bytesRead == 10) {
                    // Convertir bytes a número
                    String receivedData = new String(buffer).trim();
                    int numeroRecibido = Integer.parseInt(receivedData);

                    // Calcular el cuadrado
                    int cuadrado = numeroRecibido * numeroRecibido;

                    // Construir la respuesta
                    String respuesta = "El cuadrado de " + numeroRecibido + " es " + cuadrado;
                    System.out.println("Del cliente se ha recibido el numero: "+numeroRecibido+ " Enviando respuesta al cliente");

                    // Enviar la respuesta al cliente
                    outputStream.write(respuesta.getBytes());
                    outputStream.flush();
                }

                // Cerrar conexiones
                reader.close();
                outputStream.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
