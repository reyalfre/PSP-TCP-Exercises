package ejercicio4;
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

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int numeroEnviar;
            do {
                System.out.print("Ingrese un número (-1 para salir): ");
                String input = reader.readLine();
                numeroEnviar = Integer.parseInt(input);

                // Enviar objeto ResultadoCuadrado al servidor
                ResultadoCuadrado resultadoCuadrado = new ResultadoCuadrado(numeroEnviar);
                objectOutputStream.writeObject(resultadoCuadrado);
                objectOutputStream.flush();

                // Recibir y mostrar el objeto ResultadoCuadrado del servidor
                ResultadoCuadrado resultadoRecibido = (ResultadoCuadrado) objectInputStream.readObject();
                System.out.println("Resultado del servidor: Número = " + resultadoRecibido.getNumero() +
                        ", Cuadrado = " + resultadoRecibido.getCuadrado());

            } while (numeroEnviar != -1);

            // Cerrar conexiones
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

