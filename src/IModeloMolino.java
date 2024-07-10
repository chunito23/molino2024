import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IModeloMolino extends IObservableRemoto {

    Jugador getJugadorActual() throws RemoteException;

    void setJugador(Jugador jugador) throws RemoteException;

    boolean hacerMovimiento(int indice) throws RemoteException;

    FaseJuego getFaseActual() throws RemoteException;

    Object GetCambios() throws RemoteException;

}
