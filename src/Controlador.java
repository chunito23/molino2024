public class Controlador implements ObservadorMolino {
    private ModeloMolino modelo;
    private VistaGraficaMolino vista;
    private VistaTexto vistaTexto;
    private Jugador j;

    public Controlador(ModeloMolino modelo) {
        this.modelo = modelo;
    }

    public boolean JuegoTerminado() {
        return modelo.isJuegoTerminado();
    }

    public void setVista(VistaGraficaMolino v) {
        this.vista = v;
    }

    public void setVistaTexto(VistaTexto v) {
        this.vistaTexto = v;
    }

    public void setJugador(Jugador jugador) {
        this.j = jugador;
        modelo.setJugador(jugador);
    }

    public Jugador getJugadorActual() {
        return modelo.getJugadorActual();
    }

    public boolean hacerMovimiento(int indice) {
        if (j == modelo.getJugadorActual()){
            return modelo.hacerMovimiento(indice);
        }else {
            return false;
        }
    }

    public String getFase() {
        return modelo.getFaseActual().name();
    }

    public Object getCambios(){
        return modelo.GetCambios();
    }

    @Override
    public void actualizar(Object cambios) {
        if (vista != null) {
            vista.actualizarVista(cambios);
        }
        if (vistaTexto != null) {
            vistaTexto.actualizarVista(cambios);
        }
    }
}