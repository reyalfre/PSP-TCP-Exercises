/*
package ejercicio3;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6565;
        final int NUMERO_MAXIMO_PETICIONES = 3;
        int peticionesProcesadas = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            while (peticionesProcesadas < NUMERO_MAXIMO_PETICIONES) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Crear un nuevo hilo para manejar la conexión con el cliente
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();

                peticionesProcesadas++;
            }

            // Cerrar el servidor después de procesar las 3 peticiones
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
package ejercicio3;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6565;
        final int NUMERO_MAXIMO_PETICIONES = 3;
        int peticionesProcesadas = 0;

        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            while (peticionesProcesadas < NUMERO_MAXIMO_PETICIONES) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Crear un nuevo hilo para manejar la conexión con el cliente
                Thread clientHandlerThread = new Thread(() -> {
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                        // Recibir objeto del cliente
                        ResultadoCuadrado resultado = (ResultadoCuadrado) objectInputStream.readObject();

                        // Calcular el cuadrado
                        int cuadrado = resultado.getNumero() * resultado.getNumero();

                        // Crear y enviar objeto ResultadoCuadrado al cliente
                        ResultadoCuadrado resultadoCuadrado = new ResultadoCuadrado(resultado.getNumero(), cuadrado);
                        objectOutputStream.writeObject(resultadoCuadrado);
                        objectOutputStream.flush();

                        // Cerrar conexiones
                        objectInputStream.close();
                        objectOutputStream.close();
                        clientSocket.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                clientHandlerThread.start();

                peticionesProcesadas++;
            }

            // Cerrar el servidor después de procesar las 3 peticiones
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
