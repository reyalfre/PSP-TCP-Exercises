package ejercicio4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

                // Configurar streams de entrada con ObjectInputStream y salida una vez con ObjectOutputStream
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                // Bucle while: Procesar múltiples peticiones hasta recibir -1
                while (true) {
                    // Recibir objeto del cliente
                    ResultadoCuadrado resultado = (ResultadoCuadrado) objectInputStream.readObject();

                    if (resultado.getNumero() == -1) {
                        // Enviar mensaje de agradecimiento y cerrar conexión
                        ResultadoCuadrado mensajeAgradecimiento = new ResultadoCuadrado("GRACIAS POR USAR SERVICIOS CUADRADO");
                        objectOutputStream.writeObject(mensajeAgradecimiento);
                        objectOutputStream.flush();

                        // Cerrar conexiones y terminar hilo
                        objectInputStream.close();
                        objectOutputStream.close();
                        clientSocket.close();
                        break; // Salir del bucle y terminar el hilo
                    } else {
                        // Calcular el cuadrado
                        int cuadrado = resultado.getNumero() * resultado.getNumero();

                        // Configurar el resultado del cuadrado en el objeto
                        resultado.setResultadoCuadrado(cuadrado);

                        // Enviar objeto ResultadoCuadrado al cliente
                        objectOutputStream.writeObject(resultado);
                        objectOutputStream.flush();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
