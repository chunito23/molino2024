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


    public void setJugador(Jugador parametro) throws RemoteException {
        try {
            this.j = parametro;
            modelo.setJugador(this.j);
            // Recuperar el estado actualizado del jugador desde el modelo
            this.j = modelo.getJugador(this.j);
            System.out.println("despues de setear j.getprueba: " + this.j.getIdTablero());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public Jugador getJugador()  throws RemoteException{
        return j;
    }

    public Jugador getJugadorActual() throws RemoteException {
        return modelo.getJugadorActual(j);
    }

    public boolean hacerMovimiento(int indice) throws RemoteException {
        boolean valor = false;
        valor = this.modelo.hacerMovimiento(indice,this.j);
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
        cambio c = (cambio) cambios;
        if (j.getIdTablero() == c.getIdtablero()){
            vista.actualizarVista(cambios);
        }
    }
}