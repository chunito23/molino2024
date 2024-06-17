public class ControladorGraficoMolino {
    private ModeloMolino modelo;
    private VistaGraficaMolino vistaGrafica;

    public ControladorGraficoMolino(ModeloMolino modelo, VistaGraficaMolino vistaGrafica) {
        this.modelo = modelo;
        this.vistaGrafica = vistaGrafica;
    }

    public boolean isJuegoTerminado() {
        return modelo.isJuegoTerminado();
    }

    public String getFase() {
        FaseJuego fase = modelo.getFaseActual();
        String texto = "eliminar";
        if (fase == FaseJuego.COLOCACION){
            texto = "Colocasion";
        }
        else if(fase == FaseJuego.MOVIMIENTO){
            texto = "Colocasion";
        }
        return texto;
    }

    public boolean hacerMovimiento(int indice) {
        return modelo.hacerMovimiento(indice);
    }
}
