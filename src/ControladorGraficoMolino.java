public class ControladorGraficoMolino implements ObservadorMolino{
    private ModeloMolino modelo;
    private VistaGraficaMolino vistaGrafica;
    private Jugador j;

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
        this.j = jugador;
        modelo.setJugador(jugador);
    }

    public Jugador getJugadorActual() {
        return modelo.getJugadorActual();
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
            if(j == modelo.getJugadorActual()){
                return modelo.hacerMovimiento(indice);
            }else{
                return false;
            }
    }

    @Override
    public void actualizar(Object cambios) {
        vistaGrafica.actualizarVista(cambios);
    }


}
