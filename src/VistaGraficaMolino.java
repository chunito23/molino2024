import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaGraficaMolino extends JFrame {
    private ControladorGraficoMolino c;
    private List<JButton> botones;
    private JTextField cuadroTexto;
    private JButton botonAnterior; // Referencia al botón previamente presionado
    private Jugador j;

    public VistaGraficaMolino(ControladorGraficoMolino controlador, Jugador jugador) {
        c = controlador;
        c.setVista(this);
        c.setJugador(jugador);
        j = jugador;
        setTitle("Juego del Molino " + j.getSimbolo());
        setLayout(new BorderLayout());

        // Crear el panel para el cuadro de texto
        JPanel panelTexto = new JPanel(new BorderLayout());
        cuadroTexto = new JTextField();
        panelTexto.add(cuadroTexto, BorderLayout.CENTER);

        // Crear el panel principal con un GridBagLayout
        JPanel panelTablero = new JPanel(new GridBagLayout());
        botones = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();

        // Añadir botones en las posiciones válidas
        Dimension botonDimension = new Dimension(40, 40); // Tamaño de los botones
        for (int i = 0; i < 24; i++) {
            JButton boton = new JButton("-");
            boton.setPreferredSize(botonDimension); // Establecer tamaño preferido
            final int indice = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!controlador.hacerMovimiento(indice)) {
                        JOptionPane.showMessageDialog(null, "Este movimiento no es válido");
                    } else {
                        if(j != c.getJugadorActual()){
                            panelTablero.setBackground(Color.DARK_GRAY);
                        }else{
                            panelTablero.setBackground(Color.red);
                        }
                        if (botonAnterior != null) {
                            botonAnterior.setBackground(null); // Restaurar el color original
                        }
                        boton.setBackground(Color.LIGHT_GRAY); // Cambiar color del botón actual
                        botonAnterior = boton; // Actualizar la referencia del botón previo
                    }
                    cuadroTexto.setText(controlador.getFase());
                }
            });
            botones.add(boton);
        }

        // Colocar botones en las posiciones específicas
        colocarBoton(panelTablero, gbc, 0, 0, 0);
        colocarBoton(panelTablero, gbc, 1, 0, 3);
        colocarBoton(panelTablero, gbc, 2, 0, 6);
        colocarBoton(panelTablero, gbc, 3, 1, 1);
        colocarBoton(panelTablero, gbc, 4, 1, 3);
        colocarBoton(panelTablero, gbc, 5, 1, 5);
        colocarBoton(panelTablero, gbc, 6, 2, 2);
        colocarBoton(panelTablero, gbc, 7, 2, 3);
        colocarBoton(panelTablero, gbc, 8, 2, 4);
        colocarBoton(panelTablero, gbc, 9, 3, 0);
        colocarBoton(panelTablero, gbc, 10, 3, 1);
        colocarBoton(panelTablero, gbc, 11, 3, 2);
        colocarBoton(panelTablero, gbc, 12, 3, 4);
        colocarBoton(panelTablero, gbc, 13, 3, 5);
        colocarBoton(panelTablero, gbc, 14, 3, 6);
        colocarBoton(panelTablero, gbc, 15, 4, 2);
        colocarBoton(panelTablero, gbc, 16, 4, 3);
        colocarBoton(panelTablero, gbc, 17, 4, 4);
        colocarBoton(panelTablero, gbc, 18, 5, 1);
        colocarBoton(panelTablero, gbc, 19, 5, 3);
        colocarBoton(panelTablero, gbc, 20, 5, 5);
        colocarBoton(panelTablero, gbc, 21, 6, 0);
        colocarBoton(panelTablero, gbc, 22, 6, 3);
        colocarBoton(panelTablero, gbc, 23, 6, 6);

        // Añadir los paneles al JFrame

        add(panelTexto, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        setSize(400, 400); // Aumentar el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void colocarBoton(JPanel panel, GridBagConstraints gbc, int indice, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(botones.get(indice), gbc);
    }

    public void actualizarVista(Object cambios) {
        ArrayList<Object> cambioslist = (ArrayList<Object>) cambios;
        ArrayList<Celda> celdas = (ArrayList<Celda>) cambioslist.get(0);
        Jugador jactual = (Jugador) cambioslist.get(2);
        FaseJuego faseActual = (FaseJuego) cambioslist.get(1);
        actualizarTablero(celdas);//tablero
        if (c.isJuegoTerminado()) {
            JOptionPane.showMessageDialog(this, "¡Juego terminado!");
        } else {
            setTitle("jugador : " + j.getSimbolo() + " Turno: " + jactual.getSimbolo() + "\n" + faseActual);
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