package ejercicio2;


import java.io.Serializable;

public class ResultadoCuadrado implements Serializable {
    private int numero;
    private int cuadrado;

    public ResultadoCuadrado(int numero, int cuadrado) {
        this.numero = numero;
        this.cuadrado = cuadrado;
    }

    public int getNumero() {
        return numero;
    }

    public int getCuadrado() {
        return cuadrado;
    }
}
