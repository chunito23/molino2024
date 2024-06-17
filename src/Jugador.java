public class Jugador {
    private char simbolo;
    private int fichasDisponibles;
    private int fichasEnTablero;

    public Jugador(char simbolo) {
        this.simbolo = simbolo;
        this.fichasDisponibles = 9; // Número inicial de fichas disponibles
        this.fichasEnTablero = 0;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public int getFichasDisponibles() {
        return fichasDisponibles;
    }

    public void disminuirFichasDisponibles() {
        if (fichasDisponibles > 0) {
            fichasDisponibles--;
        }
    }

    public void aumentarFichasEnTablero() {
        fichasEnTablero++;
    }
}