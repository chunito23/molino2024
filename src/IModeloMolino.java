import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IModeloMolino extends IObservableRemoto {

    void setJugador(Jugador jugador) throws RemoteException;

    public Jugador getJugador(Jugador jugador) throws RemoteException;

    Jugador getJugadorActual(Jugador j) throws RemoteException;

    boolean hacerMovimiento(int indice, Jugador jugador) throws RemoteException;

    FaseJuego getFaseActual(int idTablero) throws RemoteException;

    Object GetCambios() throws RemoteException;

    void datos(Jugador j) throws RemoteException;

    void nada(Jugador j) throws RemoteException;

}
