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
    private int[][] posiciones = {
            {0, 0}, {0, 3}, {0, 6}, {1, 1}, {1, 3}, {1, 5},
            {2, 2}, {2, 3}, {2, 4}, {3, 0}, {3, 1}, {3, 2},
            {3, 4}, {3, 5}, {3, 6}, {4, 2}, {4, 3}, {4, 4},
            {5, 1}, {5, 3}, {5, 5}, {6, 0}, {6, 3}, {6, 6}
    };

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

        JPanel panelTablero = new JPanel(new GridLayout(7, 7));
        botones = new ArrayList<>();

        Dimension botonDimension = new Dimension(40, 40);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(botonDimension);
                final int x = i;
                final int y = j;
                final int indice = getIndice(x, y);
                if (indice != -1) {
                    boton.setText( x + " " + y );
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
                } else {
                    boton.setEnabled(false);
                }
                botones.add(boton);
                panelTablero.add(boton);
            }
        }

        add(panelTexto, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndice(int x, int y) {
        for (int i = 0; i < posiciones.length; i++) {
            if (posiciones[i][0] == x && posiciones[i][1] == y) {
                return i;
            }
        }
        return -1;
    }

    private int[] getCoordenadas(int indice) {
        if (indice >= 0 && indice < posiciones.length) {
            return posiciones[indice];
        } else {
            return null; // Devolver null si el índice está fuera de rango
        }
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
        Celda celda;
        int x;
        int y;
        int botonIndex;
        int contador = 0;
        int siguiente = 0;
        for (int i = 0; i < 7; i++) {
            for(int j = 0; j < 7;j++){
                celda = celdas.get(siguiente);
                x = celda.getX();
                y = celda.getY();
                if (x == i && y == j){
                    botones.get(contador).setText(String.valueOf(celda.getValor()));
                    siguiente = siguiente + 1;
                }
                contador = contador + 1;
            }

        }
    }

    public void mostrar() {
        setVisible(true);
    }
}