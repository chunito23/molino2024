import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        ModeloMolino modelo = new ModeloMolino();
        VistaGraficaMolino vistaGrafica = new VistaGraficaMolino();

        ControladorGraficoMolino controladorGrafico = new ControladorGraficoMolino(modelo, vistaGrafica);

        vistaGrafica.setControlador(controladorGrafico);
        modelo.agregarObservador(vistaGrafica);

        SwingUtilities.invokeLater(() -> {
            vistaGrafica.mostrar();
        });
    }
}