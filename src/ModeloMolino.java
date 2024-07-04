import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ModeloMolino {
    private List<ObservadorMolino> observadores;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador noActual;
    private Tablero tablero;
    private int contador;
    private ArrayList<Object> cambios = new ArrayList<>();

    public ModeloMolino() {

        observadores = new ArrayList<>();
        tablero = new Tablero();
        //estado inicial de la partida
        contador = 0;
        cambios.add(tablero.getCeldas());
        cambios.add(FaseJuego.COLOCACION);
        System.out.println("fase: " + getFaseActual() + "jugador: " + getJugadorActual());
    }



    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    private void cambiarJugador() {
        if (jugadorActual == jugador1){
            jugadorActual = jugador2;
            noActual = jugador1;
        }else{
            jugadorActual = jugador1;
            noActual = jugador2;
        }
        cambios.set(2, jugadorActual);
    }


    public void setJugador(Jugador jugador) { //siempre es el que agrego primero el que arranca
        if (jugador1 == null) {
            jugador1 = jugador;
            jugadorActual = jugador1;
            noActual = jugador2;
        } else {
            jugador2 = jugador;
            cambios.add(jugadorActual); //lo envia sin nada al principio
        }
    }


    public void agregarObservador(ObservadorMolino observador) {
        observadores.add(observador);
    }


    public void setCambios(ArrayList<Object> cambios) {
        this.cambios = cambios;
    }

    public void notificarObservadores(Object cambios) {
        for (ObservadorMolino observador : observadores) {
            observador.actualizar(cambios);
        }
    }

    public boolean hacerMovimiento(int indice) { //las celdas
        boolean valor = false;
        FaseJuego faseActual = tablero.getFaseActual();
        if (indice < 0 || indice >= 24 || tablero.getjuegoTerminado()) {
            return valor;
        }
        if (tablero.getFaseActual() == FaseJuego.COLOCACION) {
            valor = tablero.colocarFicha(indice,jugadorActual,noActual);
            if(tablero.getFaseActual() != FaseJuego.ELIMINACION && valor){
                cambiarJugador();
            }
        } else if (tablero.getFaseActual() == FaseJuego.MOVIMIENTO) {
            if (contador == 0){
                if (true == (valor = tablero.MoverFicha(indice,jugadorActual))){
                    contador += 1;
                }
            }else{
                if (true == (valor = tablero.MoverFicha(indice,jugadorActual))){
                    if(tablero.getFaseActual() != FaseJuego.ELIMINACION){
                        cambiarJugador();
                    }
                    contador = 0;
                }
            }

        } else if (tablero.getFaseActual() == FaseJuego.ELIMINACION) {

            if(valor = tablero.Eliminar(indice,jugadorActual,noActual)){
                cambiarJugador();
            }
        }
        System.out.println("fase: " + getFaseActual() + "jugador: " + getJugadorActual().getSimbolo());
        notificarObservadores(cambios);
        return valor;
    }

    public FaseJuego getFaseActual() {
        return tablero.getFaseActual();
    }

    public Object GetCambios() {
        return cambios;
    }

    public boolean isJuegoTerminado() {
        return tablero.getjuegoTerminado();
    }
}