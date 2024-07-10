import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import javax.swing.*;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ModeloMolino extends ObservableRemoto implements IModeloMolino{
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Jugador noActual;
    private Tablero tablero;
    private int contador;
    private ArrayList<Object> cambios = new ArrayList<>(); //celdas,fase,jugadorActual
    private static ModeloMolino instacia;

    public static ModeloMolino getinstacia(){
        if(instacia == null){
            instacia = new ModeloMolino();
        }
        return instacia;
    }

    private ModeloMolino() {
        tablero = new Tablero();
        contador = 0;
        cambios.add(tablero.getCeldas());
        cambios.add(FaseJuego.COLOCACION);
    }

    public Jugador getJugadorActual() throws RemoteException{
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


    public void setJugador(Jugador jugador) throws RemoteException{ //siempre es el que agrego primero el que arranca
        if (jugador1 == null) {
            jugador1 = jugador;
            jugadorActual = jugador1;
            noActual = jugador2;
        } else {
            jugador2 = jugador;
            cambios.add(jugadorActual); //lo envia sin nada al principio
        }
    }

    private void setCambios(ArrayList<Object> cambios) {
        this.cambios = cambios;
    }



    public boolean hacerMovimiento(int indice) throws RemoteException { //las celdas
        boolean valor = false;
        int sucesoMoviento = 0;
        if (indice < 0 || indice >= 24 || tablero.getFaseActual() == FaseJuego.FIN) {
            return valor;
        }
        if (tablero.getFaseActual() == FaseJuego.COLOCACION) {
            valor = tablero.colocarFicha(indice, jugadorActual, noActual);
            if (tablero.getFaseActual() != FaseJuego.ELIMINACION && valor) {
                cambiarJugador();
            }
        } else if (tablero.getFaseActual() == FaseJuego.MOVIMIENTO) {
            sucesoMoviento = tablero.MoverFicha(indice, jugadorActual);
            valor = true;
            if (contador == 0 && 1 == sucesoMoviento) {
                contador += 1;
            } else if (1 == sucesoMoviento) {
                if (tablero.getFaseActual() != FaseJuego.ELIMINACION) {
                    cambiarJugador();
                }
                contador = 0;
            } else if (2 == sucesoMoviento) {
                contador = 0;
            }else{
                valor = false;
            }
        } else if (tablero.getFaseActual() == FaseJuego.ELIMINACION) {
            valor = tablero.Eliminar(indice,jugadorActual,noActual);
            if(valor){
                cambiarJugador();
            }
            if(tablero.getFaseActual() == FaseJuego.FIN){
                cambiarJugador();
            }
        }
        cambios.set(1,tablero.getFaseActual());

        notificarObservadores(cambios);
        return valor;
    }

    public FaseJuego getFaseActual() throws RemoteException{
        return tablero.getFaseActual();
    }

    public Object GetCambios() throws RemoteException{
        return cambios;
    }

}