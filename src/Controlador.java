import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto {
    private IModeloMolino modelo;
    private Ivista vista;
    private Jugador j;

    public Controlador() {

    }

    public void setVista(Ivista vista)  throws RemoteException{
        this.vista = vista;
    }


    public void setJugador(Jugador jugador) throws RemoteException {
        try {
            this.j = jugador;
            modelo.setJugador(jugador);
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    public Jugador getJugador()  throws RemoteException{
        return j;
    }

    public Jugador getJugadorActual() throws RemoteException {
        return modelo.getJugadorActual(j.getIdTablero());
    }

    public boolean hacerMovimiento(int indice) {
        boolean valor = false;
        try {
            if (j.getSimbolo() == modelo.getJugadorActual(j.getIdTablero()).getSimbolo()){
                valor = modelo.hacerMovimiento(indice,j);
            }
        }catch (RemoteException e){
            e.printStackTrace();
            System.out.println("fallo al hacer movimiento");
        }
        return valor;
    }

    public String getFase() throws RemoteException {
        return modelo.getFaseActual(j.getIdTablero()).toString();
    }

    public Object getCambios() throws RemoteException {
        return modelo.GetCambios();
    }


    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo  = (IModeloMolino) modeloRemoto;
    }

    @Override
    public void actualizar(IObservableRemoto modelo, Object cambios) throws RemoteException {
        vista.actualizarVista(cambios);
    }
}