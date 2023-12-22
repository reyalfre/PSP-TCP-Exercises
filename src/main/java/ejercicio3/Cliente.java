package ejercicio3;
import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        final String SERVIDOR_IP = "localhost";
        final int PUERTO = 6565;

        try {
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Enviar objeto ResultadoCuadrado al servidor
            ResultadoCuadrado resultadoCuadrado = new ResultadoCuadrado(5, 0); // Cambiar el número según sea necesario
            objectOutputStream.writeObject(resultadoCuadrado);
            objectOutputStream.flush();

            // Recibir y mostrar el objeto ResultadoCuadrado del servidor
            ResultadoCuadrado resultadoRecibido = (ResultadoCuadrado) objectInputStream.readObject();
            System.out.println("Resultado del servidor: Número = " + resultadoRecibido.getNumero() +
                    ", Cuadrado = " + resultadoRecibido.getCuadrado());

            // Cerrar conexiones
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

