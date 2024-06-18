import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        Jugador jugador1 = new Jugador('*');
        Jugador jugador2 = new Jugador('/');
        ModeloMolino modelo = new ModeloMolino();

        ControladorGraficoMolino controladorGrafico = new ControladorGraficoMolino(modelo);
        ControladorGraficoMolino controladorGrafico2 = new ControladorGraficoMolino(modelo);

        VistaGraficaMolino vistaGrafica = new VistaGraficaMolino(controladorGrafico,jugador1);
        VistaGraficaMolino vistaGrafica2 = new VistaGraficaMolino(controladorGrafico2,jugador2);

        modelo.agregarObservador(controladorGrafico);
        modelo.agregarObservador(controladorGrafico2);

        SwingUtilities.invokeLater(() -> {
            vistaGrafica.mostrar();
            vistaGrafica2.mostrar();
        });
    }
}