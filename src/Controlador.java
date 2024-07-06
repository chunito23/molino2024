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

    public boolean JuegoTerminado() throws RemoteException {
        return modelo.isJuegoTerminado();
    }

    public void setJugador(Jugador jugador) throws RemoteException {
        try {
            this.j = jugador;
            modelo.setJugador(jugador);
            System.out.println(modelo.getJugadorActual());
        }catch (RemoteException e){
            e.printStackTrace();
            System.out.println("falle agregando jugador");
        }

    }

    public Jugador getJugador()  throws RemoteException{
        return j;
    }

    public Jugador getJugadorActual() throws RemoteException {
        return modelo.getJugadorActual();
    }

    public boolean hacerMovimiento(int indice) {
        boolean valor = false;
        try {
            System.out.println(j.getSimbolo() + " " + modelo.getJugadorActual().getSimbolo());
            if (j.getSimbolo() == modelo.getJugadorActual().getSimbolo()){
                valor = modelo.hacerMovimiento(indice);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return valor;
    }

    public String getFase() throws RemoteException {
        return modelo.getFaseActual().name();
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