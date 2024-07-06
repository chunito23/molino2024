import java.io.Serializable;

public class Jugador implements Serializable {
    private char simbolo;
    private int fichasDisponibles;
    private int fichasEnTablero;

    public Jugador(char simbolo) {
        this.simbolo = simbolo;
        this.fichasDisponibles = 3; // Número inicial de fichas disponibles
        this.fichasEnTablero = 0;
    }

    public char getSimbolo() {
        return simbolo;
    }


    public int getFichasDisponibles() {
        return fichasDisponibles;
    }

    public int getFichasEnTablero() {
        return fichasEnTablero;
    }

    public void disminuirFichasDisponibles() {
        if (fichasDisponibles > 0) {
            fichasDisponibles--;
        }
    }

    public void aumentarFichasEnTablero() {
        fichasEnTablero++;
    }

    public void DisminuirFichasEnTablero() {
        fichasEnTablero--;
    }
}