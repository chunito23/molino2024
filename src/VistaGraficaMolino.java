import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaGraficaMolino extends JFrame implements ObservadorMolino {
    private ControladorGraficoMolino controlador;
    private List<JButton> botones;

    public VistaGraficaMolino() {
        setTitle("Juego del Molino");
        setLayout(new BorderLayout());

        // Crear el panel principal con un GridBagLayout
        JPanel panelTablero = new JPanel(new GridBagLayout());
        botones = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();

        // Añadir botones en las posiciones válidas
        for (int i = 0; i < 24; i++) {
            JButton boton = new JButton("-");
            final int indice = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!controlador.hacerMovimiento(indice)) {
                        JOptionPane.showMessageDialog(null, "Este movimiento no es válido");
                    }
                }
            });
            botones.add(boton);
        }

        // Colocar botones en las posiciones específicas
        colocarBoton(panelTablero, gbc, 0, 0, 0);
        colocarBoton(panelTablero, gbc, 1, 3, 0);
        colocarBoton(panelTablero, gbc, 2, 6, 0);
        colocarBoton(panelTablero, gbc, 3, 1, 1);
        colocarBoton(panelTablero, gbc, 4, 3, 1);
        colocarBoton(panelTablero, gbc, 5, 5, 1);
        colocarBoton(panelTablero, gbc, 6, 2, 2);
        colocarBoton(panelTablero, gbc, 7, 3, 2);
        colocarBoton(panelTablero, gbc, 8, 4, 2);
        colocarBoton(panelTablero, gbc, 9, 0, 3);
        colocarBoton(panelTablero, gbc, 10, 1, 3);
        colocarBoton(panelTablero, gbc, 11, 2, 3);
        colocarBoton(panelTablero, gbc, 12, 4, 3);
        colocarBoton(panelTablero, gbc, 13, 5, 3);
        colocarBoton(panelTablero, gbc, 14, 6, 3);
        colocarBoton(panelTablero, gbc, 15, 2, 4);
        colocarBoton(panelTablero, gbc, 16, 3, 4);
        colocarBoton(panelTablero, gbc, 17, 4, 4);
        colocarBoton(panelTablero, gbc, 18, 1, 5);
        colocarBoton(panelTablero, gbc, 19, 3, 5);
        colocarBoton(panelTablero, gbc, 20, 5, 5);
        colocarBoton(panelTablero, gbc, 21, 0, 6);
        colocarBoton(panelTablero, gbc, 22, 3, 6);
        colocarBoton(panelTablero, gbc, 23, 6, 6);

        add(panelTablero, BorderLayout.CENTER);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void colocarBoton(JPanel panel, GridBagConstraints gbc, int indice, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(botones.get(indice), gbc);
    }

    public void setControlador(ControladorGraficoMolino controlador) {
        this.controlador = controlador;
    }

    @Override
    public void actualizar(ModeloMolino modelo) {
        actualizarTablero(modelo.getCeldas());
        if (modelo.isJuegoTerminado()) {
            JOptionPane.showMessageDialog(this, "¡Juego terminado!");
        } else {
            setTitle("Turno del jugador " + modelo.getJugadorActual().getSimbolo());
        }
    }

    private void actualizarTablero(List<Celda> celdas) {
        for (int i = 0; i < celdas.size(); i++) {
            botones.get(i).setText(String.valueOf(celdas.get(i).getValor()));
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
