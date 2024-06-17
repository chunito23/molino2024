import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        ModeloMolino modelo = new ModeloMolino();
        VistaGraficaMolino vistaGrafica = new VistaGraficaMolino();
        VistaGraficaMolino vistaGrafica2 = new VistaGraficaMolino();

        ControladorGraficoMolino controladorGrafico = new ControladorGraficoMolino(modelo, vistaGrafica);
        ControladorGraficoMolino controladorGrafico2 = new ControladorGraficoMolino(modelo, vistaGrafica2);

        vistaGrafica.setControlador(controladorGrafico);
        vistaGrafica2.setControlador(controladorGrafico2);
        modelo.agregarObservador(vistaGrafica);
        modelo.agregarObservador(vistaGrafica2);

        SwingUtilities.invokeLater(() -> {
            vistaGrafica.mostrar();
            vistaGrafica2.mostrar();
        });
    }
}