public class ControladorGraficoMolino implements ObservadorMolino{
    private ModeloMolino modelo;
    private VistaGraficaMolino vistaGrafica;

    public ControladorGraficoMolino(ModeloMolino modelo) {
        this.modelo = modelo;

    }

    public boolean isJuegoTerminado() {
        return modelo.isJuegoTerminado();
    }

    public void setVista(VistaGraficaMolino v){
        this.vistaGrafica = v;
    }

    public void setJugador(Jugador jugador) {
        modelo.setJugador(jugador);
    }

    public String getFase() {
        FaseJuego fase = modelo.getFaseActual();
        String texto = "eliminar";
        if (fase == FaseJuego.COLOCACION){
            texto = "Colocasion";
        }
        else if(fase == FaseJuego.MOVIMIENTO){
            texto = "movimiento";
        }
        return texto;
    }

    public boolean hacerMovimiento(int indice) {
        return modelo.hacerMovimiento(indice);
    }


    @Override
    public void actualizar(Object cambios) {
        vistaGrafica.actualizarVista(cambios);
    }


}
