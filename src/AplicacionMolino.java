import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        Jugador jugador1 = new Jugador('*');
        Jugador jugador2 = new Jugador('/');
        ModeloMolino modelo = new ModeloMolino();


        Controlador controladorGrafico = new Controlador(modelo);
        Controlador controladorGrafico2 = new Controlador(modelo);

        VistaGraficaMolino vistaGrafica = new VistaGraficaMolino(controladorGrafico,jugador1);
        VistaTexto vt = new VistaTexto(controladorGrafico2,jugador2);
        //VistaGraficaMolino vistaGrafica2 = new VistaGraficaMolino(controladorGrafico2,jugador2);

        modelo.agregarObservador(controladorGrafico);
        modelo.agregarObservador(controladorGrafico2);

        SwingUtilities.invokeLater(() -> {
            vistaGrafica.mostrar();
           // vistaGrafica2.mostrar();
            vt.setVisible(true);

        });
    }
}