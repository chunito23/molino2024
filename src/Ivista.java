import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Ivista {

     default void actualizarVista(Object cambios) throws RemoteException {

    }

     private void actualizarTablero(List<Celda> celdas) throws RemoteException {

    }


    default void mostrar() {

    }
}
