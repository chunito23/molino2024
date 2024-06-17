import java.util.ArrayList;
import java.util.List;

public class Celda {
    private char valor;
    private List<Celda> vecinos;

    public Celda() {
        this.valor = '-';
        this.vecinos = new ArrayList<>();
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public boolean estaVacia() {
        return valor == '-';
    }

    public void agregarVecino(Celda vecino) {
        vecinos.add(vecino);
    }

    public List<Celda> getVecinos() {
        return vecinos;
    }
}