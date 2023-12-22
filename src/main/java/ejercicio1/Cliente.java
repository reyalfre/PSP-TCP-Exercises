package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        final String SERVIDOR_IP = "localhost";
        final int PUERTO = 6565;

        try {
            // Conectar al servidor
            Socket socket = new Socket(SERVIDOR_IP, PUERTO);

            // Configurar streams de entrada y salida
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outputStream = socket.getOutputStream();

            // Enviar un número al servidor
            int numeroEnviar = 5; // Puedes cambiar este valor por el número que desees
            System.out.println("El numero a enviar al servidor es: "+numeroEnviar);
            String numeroString = String.format("%10d", numeroEnviar);
            outputStream.write(numeroString.getBytes());
            outputStream.flush();

            // Recibir y mostrar la respuesta del servidor
            System.out.println("Respuesta del servidor:");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerrar conexiones
            reader.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
