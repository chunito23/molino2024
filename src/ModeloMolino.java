import java.util.ArrayList;
import java.util.List;

public class ModeloMolino {
    private List<Celda> celdas;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Jugador jugadorActual;
    private List<ObservadorMolino> observadores;
    private boolean juegoTerminado;
    private FaseJuego faseActual;

    public ModeloMolino() {
        celdas = new ArrayList<>();
        jugadorX = new Jugador('X');
        jugadorO = new Jugador('O');
        jugadorActual = jugadorX;
        observadores = new ArrayList<>();
        juegoTerminado = false;
        faseActual = FaseJuego.COLOCACION;
        inicializarCeldas();
        establecerVecinos();
    }

    private void inicializarCeldas() {
        for (int i = 0; i < 24; i++) {
            celdas.add(new Celda());
        }
    }

    private void establecerVecinos() {
        // Definir los vecinos para cada celda según las conexiones del tablero del molino
        // Ejemplo:
        // celdas.get(0).agregarVecino(celdas.get(1));
        // celdas.get(0).agregarVecino(celdas.get(9));
        // ...
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void agregarObservador(ObservadorMolino observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ObservadorMolino observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (ObservadorMolino observador : observadores) {
            observador.actualizar(this);
        }
    }

    public boolean hacerMovimiento(int indice) {
        if (indice < 0 || indice >= 24 || !celdas.get(indice).estaVacia() || juegoTerminado) {
            return false;
        }

        if (faseActual == FaseJuego.COLOCACION) {
            return colocarFicha(indice);
        } else if (faseActual == FaseJuego.MOVIMIENTO) {
            // Implementar lógica de movimiento de fichas
        } else if (faseActual == FaseJuego.ELIMINACION) {
            // Implementar lógica de eliminación de fichas
        }
        return false;
    }

    private boolean colocarFicha(int indice) {
        if (jugadorActual.getFichasDisponibles() > 0) {
            celdas.get(indice).setValor(jugadorActual.getSimbolo());
            jugadorActual.disminuirFichasDisponibles();
            jugadorActual.aumentarFichasEnTablero();
            if (comprobarMolino(indice)) {
                faseActual = FaseJuego.ELIMINACION;
            } else {
                cambiarJugador();
            }
            notificarObservadores();
            return true;
        }
        return false;
    }

    private void cambiarJugador() {
        jugadorActual = (jugadorActual == jugadorX) ? jugadorO : jugadorX;
        if (jugadorX.getFichasDisponibles() == 0 && jugadorO.getFichasDisponibles() == 0) {
            faseActual = FaseJuego.MOVIMIENTO;
        }
    }

    private boolean comprobarMolino(int indice) {
        // Implementar lógica para comprobar si se ha creado un molino
        return false;
    }

    public FaseJuego getFaseActual() {
        return faseActual;
    }
}