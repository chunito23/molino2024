import java.io.Serializable;

public class Jugador implements Serializable {
    private char simbolo;
    private int fichasDisponibles;
    private int fichasEnTablero;
    private boolean activo;
    private int ganadas;
    private int idTablero;

    public Jugador(char simbolo) {
        this.simbolo = simbolo;
        this.fichasDisponibles = 3; // Número inicial de fichas disponibles
        this.fichasEnTablero = 0;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getGanadas() {
        return ganadas;
    }

    public void setGanadas(int ganadas) {
        this.ganadas = ganadas;
    }

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
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

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public void setFichasDisponibles(int fichasDisponibles) {
        this.fichasDisponibles = fichasDisponibles;
    }

    public void setFichasEnTablero(int fichasEnTablero) {
        this.fichasEnTablero = fichasEnTablero;
    }
}