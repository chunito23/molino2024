import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Celda implements Serializable {
    private char valor;
    private List<Celda> vecinos; // 0,1 vecinos horizontales 2,3 vecinos verticalres
    private int x;
    private int y;


    public Celda(int x,int y) {
        this.x = x;
        this.y = y;
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

    public void setVecinos(List<Celda> v) {
        this.vecinos = v;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}