import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModeloMolino extends ObservableRemoto implements IModeloMolino{
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tablero> tableros;
    private static ArrayList<cambio> cambios;
    private static int idtableros;
    private int contador;
    private static ModeloMolino instacia;

    public static ModeloMolino getinstacia(){
        if(instacia == null){
            instacia = new ModeloMolino();
        }
        return instacia;
    }

    private ModeloMolino() {
        tableros = new ArrayList<Tablero>();
        jugadores = new ArrayList<Jugador>();
        cambios = new ArrayList<cambio>();
        contador = 0;
        idtableros = 0;
    }

    public void setJugador(Jugador jugador) throws RemoteException{ //siempre es el que agrego primero el que arranca
        if(jugadores.size() % 2 == 0){
            iniciarTablero(jugador);
        }else{
            unirJugadorTablero(jugador);
        }
        jugadores.add(jugador);
    }

    public Jugador getJugador(Jugador jugador) throws RemoteException {
        for (Jugador j : jugadores) {
            if (j.getSimbolo() == jugador.getSimbolo()) {
                return j;
            }
        }
        return null;
    }


    public void iniciarTablero(Jugador j1){
        System.out.println("id tableros al iniciar al agregar al jugador: " + idtableros);
        tableros.add(new Tablero(idtableros));//idt 1
        j1.setIdTablero(idtableros);
        cambios.add(new cambio(FaseJuego.COLOCACION, (ArrayList<Celda>) new Tablero(-99).getCeldas(),j1));
        cambios.get(idtableros).setAux(j1); //jugador actual el primero que se coencta
    }

    public void unirJugadorTablero(Jugador j2){
        j2.setIdTablero(idtableros);
        cambios.get(idtableros).setJna(j2);
        idtableros++;
    }

    public Tablero getTablero(int tableroid){
        return tableros.get(tableroid);
    }


    public static void intercambiarJugadores(int idcambio) {
        cambio c = cambios.get(idcambio);
        if(c.getAux().getSimbolo() == c.getJa().getSimbolo()){
            c.setAux(c.getJna());
        }else{
            c.setAux(c.getJa());
        }
    }

    public Jugador getJugadorActual(Jugador j) throws RemoteException{
        return cambios.get(j.getIdTablero()).getAux();
    }

    private Tablero conseguirTablero(Jugador jugador){
        for (int i = 0;i<tableros.size();i++){
           if (jugador.getIdTablero() == tableros.get(i).getIdTablero()){
               return tableros.get(i);
           }
        }
        return null;
    }

    private void contextos(Jugador j){
        System.out.println("jugador rival en cotexto: " + j.getIdTablero());
        cambio c;
        Tablero t;
        for (int i = 0;i<cambios.size();i++){
            c = cambios.get(i);
            t = tableros.get(i);
            System.out.println(c.getFj());
            System.out.println(c.getJa().getSimbolo() + " " + c.getJna().getSimbolo() + " " + c.getAux().getSimbolo());
            System.out.println(c.getJa().getIdTablero() + " " + c.getJna().getIdTablero() + " " + c.getAux().getIdTablero());
            System.out.println(t + " " + t.getIdTablero());
            System.out.println();
        }
    }

    public void nada(Jugador j) throws RemoteException{
        System.out.println();
    }

    private void JugadoresActivos(){
        for(int i = 0;i<jugadores.size();i++){
            System.out.print(jugadores.get(i).getSimbolo() + " " + jugadores.get(i).getPrueba());
        }
    }

    public void datos(Jugador j) throws RemoteException {
        System.out.println("jugador que toco: " + j.getSimbolo() + " idtablero: " + j.getIdTablero());
        System.out.println("jugador actual en el tablero de jugador que toco: " + getJugadorActual(j));
        cambio c;
        Tablero t;
        for (int i = 0;i<cambios.size();i++){
            c = cambios.get(i);
            t = tableros.get(i);
            System.out.println(c.getFj());
            System.out.println(c.getJa().getSimbolo() + " " + c.getJna().getSimbolo() + " " + c.getAux().getSimbolo());
            System.out.println(c.getJa().getIdTablero() + " " + c.getJna().getIdTablero() + " " + c.getAux().getIdTablero());
            System.out.println(t + " " + t.getIdTablero());
            System.out.println();
        }
    }



    public boolean hacerMovimiento(int indice,Jugador jugador) throws RemoteException { //las celdas
        boolean valor = false;
        int sucesoMoviento = 0;
        Tablero tableroPartida = conseguirTablero(jugador);
        FaseJuego faseActual = cambios.get(tableroPartida.getIdTablero()).getFj();
        Jugador j1 = cambios.get(tableroPartida.getIdTablero()).getJa();
        Jugador j2 = cambios.get(tableroPartida.getIdTablero()).getJna();
        Jugador actual = cambios.get(tableroPartida.getIdTablero()).getAux();
        Jugador noactual;
        if (actual == j1){
            noactual = j2;
        }else{
            noactual = j1;
        }
        //JugadoresActivos();
        //System.out.println("tableroPartida: " + tableroPartida);
        //System.out.println("faseActual: " + faseActual);
        //System.out.println("j1: " + j1.getSimbolo());
        //System.out.println("j2: " + j2.getSimbolo());
        //System.out.println("actual: " + actual.getSimbolo());
        //System.out.println("noactual: " + noactual.getSimbolo());
        if (indice < 0 || indice >= 24 || faseActual == FaseJuego.FIN) {
            return valor;
        }
        if (faseActual == FaseJuego.COLOCACION) {
            valor = tableroPartida.colocarFicha(indice, actual, noactual);
            if (tableroPartida.getFaseActual() != FaseJuego.ELIMINACION && valor) {
                intercambiarJugadores(tableroPartida.getIdTablero());
            }
        } else if (faseActual == FaseJuego.MOVIMIENTO) {
            sucesoMoviento = tableroPartida.MoverFicha(indice, actual);
            valor = true;
            if (contador == 0 && 1 == sucesoMoviento) {
                contador += 1;
            } else if (1 == sucesoMoviento) {
                if (tableroPartida.getFaseActual() != FaseJuego.ELIMINACION) {
                    intercambiarJugadores(tableroPartida.getIdTablero());
                }
                contador = 0;
            } else if (2 == sucesoMoviento) {
                contador = 0;
            }else{
                valor = false;
            }
        } else if (faseActual == FaseJuego.ELIMINACION) {
            System.out.println("entre a eliminar: " + j1 + " " +j2);
            valor = tableroPartida.Eliminar(indice,actual,noactual);
            if(valor){
                intercambiarJugadores(tableroPartida.getIdTablero());
            }
            if(tableroPartida.getFaseActual() == FaseJuego.FIN){
                intercambiarJugadores(tableroPartida.getIdTablero());
            }
        }

        cambios.get(tableroPartida.getIdTablero()).setCeldas(tableroPartida.getCeldas());
        cambios.get(tableroPartida.getIdTablero()).setFj(tableroPartida.getFaseActual());
        cambios.get(tableroPartida.getIdTablero()).setIdtablero(tableroPartida.getIdTablero());
        notificarObservadores(cambios.get(tableroPartida.getIdTablero()));
        return valor;
    }

    public FaseJuego getFaseActual(int idt) throws RemoteException{
        return tableros.get(idt).getFaseActual();
    }

    public Object GetCambios() throws RemoteException{
        return cambios;
    }

}