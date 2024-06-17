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

    public boolean hacerMovimiento(int indice) {
        return modelo.hacerMovimiento(indice);
    }
}
