package ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6565;

        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

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
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

