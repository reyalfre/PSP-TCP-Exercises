package ejercicio4;

import java.io.Serializable;

public class ResultadoCuadrado implements Serializable {
    private int numero;
    private int cuadrado;
    private String mensaje;

    // Constructor para solicitar el cálculo del cuadrado
    public ResultadoCuadrado(int numero) {
        this.numero = numero;
    }

    // Constructor para agradecer por usar el servicio
    public ResultadoCuadrado(String mensaje) {
        this.mensaje = mensaje;
    }

    // Métodos getter
    public int getNumero() {
        return numero;
    }

    public int getCuadrado() {
        return cuadrado;
    }

    public String getMensaje() {
        return mensaje;
    }

    // Método para establecer el resultado del cuadrado
    public void setResultadoCuadrado(int cuadrado) {
        this.cuadrado = cuadrado;
    }
}
