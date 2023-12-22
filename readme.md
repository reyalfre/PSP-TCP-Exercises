1. Realiza un servidor que esté escuchando conexiones por un socket TCP en el puerto 6565. Una vez se establezca una
   conexión, el servidor recibirá una serie de bytes, que
   transformará a número, y calculará el cuadrado de ese número. Devolverá al cliente
   por el socket la frase “El cuadrado de <numeroRecibido> es <cuadrado>”, siendo
   <numeroRecibido> el número recibido en el servidor, y <cuadrado> el cuadrado del
   primer número. El cliente se quedará escuchando la respuesta y luego terminará.
   PISTA: El servidor puede leer 10 bytes de lo que le manda el cliente, pero deberá
   hacerle un “trim()” al String que recoja.


   a. Pista: Para recibir los datos en el cliente podéis usar otro modo de recoger
   datos del InputStream, que es con el BufferedReader. Trozo de código útil:


BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
System.out.print("El servidor nos contesta:\n ");
String linea;
while ((linea = reader.readLine()) !=null){
System.out.println(linea);
}


2. Duplica el ejercicio anterior, y ahora en vez de enviarlo como stream de datos, lo
   enviaremos como objetos. Crea una clase ResultadoCuadrado, que tendrá de atributos
   un número, y su cuadrado. Ya no se devolverá una frase, sino que sólo devolveremos
   un objeto de ResultadoCuadrado, con la información del número y su cuadrado.


Ejercicios UT4. Parte II


3. Duplicando el código del ejercicio 2, queremos ahora que el servidor pueda atender
   varios clientes para el cálculo del cuadrado. Con cada petición de conexión, abrirá un
   socket, y se tendrá que crear un hilo para atender esa petición de cálculo del
   cuadrado. El hilo, una vez haya recibido el número, calculado el cuadrado y devuelto
   al cliente, cerrará el socket y terminará su ejecución. El servidor seguirá atendiendo
   peticiones hasta que hayan procesado 3 peticiones desde su inicio.


4. Duplicando el código del ejercicio 3, queremos ahora que el cliente, una vez cogida la
   conexión con el servidor, pueda enviarle múltiples peticiones de cálculo de cuadrado,
   hasta que envíe un -1. En ese caso, no hará falta calcular el cuadrado y el hilo
   devolverá el resultado al cliente con un mensaje dentro con el texto “GRACIAS POR
   USAR SERVICIOS CUADRADO” y procederá a cerrar el socket y terminar la ejecución.


   a. Pista 1: Tendrás que añadir un atributo “mensaje” en la clase
   ResultadoCuadrado.
   

   b. Pista 2: Para que no te de problemas, tanto en el Cliente como en el Hilo,
   tendrás que crear los ObjectInputStream y ObjectOutputStream una vez, y
   luego usarlos todas las veces que se soliciten los cuadrados.