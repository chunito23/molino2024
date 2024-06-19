import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class VistaGraficaMolino extends JFrame {
    private Controlador c;
    private List<JButton> botones;
    private JTextField cuadroTexto;
    private JButton botonAnterior;
    private Jugador j;

    public VistaGraficaMolino(Controlador controlador, Jugador jugador) {
        c = controlador;
        c.setVista(this);
        c.setJugador(jugador);
        j = jugador;
        setTitle("Juego del Molino " + j.getSimbolo());
        setLayout(new BorderLayout());

        JPanel panelTexto = new JPanel(new BorderLayout());
        cuadroTexto = new JTextField();
        panelTexto.add(cuadroTexto, BorderLayout.CENTER);

        JPanel panelTablero = new JPanel(new GridBagLayout());
        botones = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension botonDimension = new Dimension(40, 40);
        for (int i = 0; i < 24; i++) {
            JButton boton = new JButton("-");
            boton.setPreferredSize(botonDimension);
            final int indice = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!c.hacerMovimiento(indice)) {
                        JOptionPane.showMessageDialog(null, "Este movimiento no es válido");
                    } else {
                        actualizarBoton(boton);
                    }
                    cuadroTexto.setText(c.getFase());
                }
            });
            botones.add(boton);
        }

        colocarBotones(panelTablero, gbc);

        add(panelTexto, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void colocarBotones(JPanel panel, GridBagConstraints gbc) {
        int[][] posiciones = {
                {0, 0, 0}, {1, 0, 3}, {2, 0, 6}, {3, 1, 1}, {4, 1, 3}, {5, 1, 5},
                {6, 2, 2}, {7, 2, 3}, {8, 2, 4}, {9, 3, 0}, {10, 3, 1}, {11, 3, 2},
                {12, 3, 4}, {13, 3, 5}, {14, 3, 6}, {15, 4, 2}, {16, 4, 3}, {17, 4, 4},
                {18, 5, 1}, {19, 5, 3}, {20, 5, 5}, {21, 6, 0}, {22, 6, 3}, {23, 6, 6}
        };
        for (int[] posicion : posiciones) {
            colocarBoton(panel, gbc, posicion[0], posicion[1], posicion[2]);
        }
    }

    private void colocarBoton(JPanel panel, GridBagConstraints gbc, int indice, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(botones.get(indice), gbc);
    }

    private void actualizarBoton(JButton boton) {
        if (botonAnterior != null) {
            botonAnterior.setBackground(null);
        }
        boton.setBackground(Color.LIGHT_GRAY);
        botonAnterior = boton;
    }

    public void actualizarVista(Object cambios) {
        ArrayList<Object> cambiosList = (ArrayList<Object>) cambios;
        List<Celda> celdas = (List<Celda>) cambiosList.get(0);
        Jugador jugadorActual = (Jugador) cambiosList.get(2);
        FaseJuego faseActual = (FaseJuego) cambiosList.get(1);
        actualizarTablero(celdas);

        if (c.JuegoTerminado()) {
            JOptionPane.showMessageDialog(this, "¡Juego terminado!");
        } else {
            setTitle("Jugador: " + j.getSimbolo() + " Turno: " + jugadorActual.getSimbolo() + "\n" + faseActual);
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