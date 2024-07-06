import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class VistaGraficaMolino extends JFrame implements Ivista{
    private Controlador c;
    private List<JButton> botones;
    private JTextField cuadroTexto;
    private int[][] posiciones = {
            {0, 0}, {0, 3}, {0, 6}, {1, 1}, {1, 3}, {1, 5},
            {2, 2}, {2, 3}, {2, 4}, {3, 0}, {3, 1}, {3, 2},
            {3, 4}, {3, 5}, {3, 6}, {4, 2}, {4, 3}, {4, 4},
            {5, 1}, {5, 3}, {5, 5}, {6, 0}, {6, 3}, {6, 6}
    };

    public VistaGraficaMolino(Controlador c) throws RemoteException {
        this.c = c;
        setTitle("Juego del Molino " + this.c.getJugador().getSimbolo());
        setLayout(new BorderLayout());

        JPanel panelTexto = new JPanel(new BorderLayout());
        cuadroTexto = new JTextField();
        cuadroTexto.setEditable(false);
        panelTexto.add(cuadroTexto, BorderLayout.CENTER);

        JPanel panelTablero = new JPanel(new GridLayout(7, 7));
        botones = new ArrayList<>();

        Dimension botonDimension = new Dimension(50, 50);
        cuadroTexto.setText(c.getFase() + " turno: " + c.getJugadorActual().getSimbolo());
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(botonDimension);
                boton.setMargin(new Insets(0, 0, 0, 0));
                boton.setFont(new Font("Arial", Font.BOLD, 20));
                boton.setFocusPainted(false);
                boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                final int x = i;
                final int y = j;
                final int indice = getIndice(x, y);
                if (indice != -1) {
                    boton.setBackground(Color.WHITE);
                    boton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                System.out.println(c.getJugador() + " " + c.getJugadorActual());
                            } catch (RemoteException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (!c.hacerMovimiento(indice)) {
                                JOptionPane.showMessageDialog(null, "Este movimiento no es válido");
                            }

                        }
                    });
                } else {
                    boton.setEnabled(false);
                    boton.setBackground(Color.LIGHT_GRAY);
                }
                botones.add(boton);
                panelTablero.add(boton);
            }
        }
        System.out.println("para pintar los botones: " + c.getJugadorActual() + " " + c.getJugador());
        if (c.getJugadorActual().getSimbolo() == c.getJugador().getSimbolo()){
            botones.get(24).setBackground(Color.green);
        }else{
            botones.get(24).setBackground(Color.red);
        }

        add(panelTexto, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        setSize(450, 450);
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


    public void actualizarVista(Object cambios) throws RemoteException {
        ArrayList<Object> cambiosList = (ArrayList<Object>) cambios;
        List<Celda> celdas = (List<Celda>) cambiosList.get(0);
        FaseJuego faseActual = (FaseJuego) cambiosList.get(1);
        Jugador jugadorActual = (Jugador) cambiosList.get(2);
        actualizarTablero(celdas);
        cuadroTexto.setText(c.getFase() + " turno: " + c.getJugadorActual().getSimbolo());
        if (c.JuegoTerminado()) {
            JOptionPane.showMessageDialog(this, "¡Juego terminado!");
        } else {
            setTitle("Jugador: " + jugadorActual.getSimbolo() + " Turno: " + jugadorActual.getSimbolo() + "\n" + faseActual);
        }
    }

    public void actualizarTablero(List<Celda> celdas) throws RemoteException {
        Celda celda;
        int x;
        int y;
        int contador = 0;
        int siguiente = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (siguiente < celdas.size()) {
                    celda = celdas.get(siguiente);
                    x = celda.getX();
                    y = celda.getY();
                    if (x == i && y == j) {
                        JButton boton = botones.get(contador);
                        if (celda.getValor() == '-') {
                            boton.setText("");
                            boton.setBackground(Color.WHITE);
                        } else {
                            boton.setText(String.valueOf(celda.getValor()));
                        }
                        siguiente++;
                    }
                }
                contador++;
            }
        }
        System.out.println("para pintar los botones: " + c.getJugadorActual() + " " + c.getJugador());
        if (c.getJugadorActual().getSimbolo() == c.getJugador().getSimbolo()){
            botones.get(24).setBackground(Color.green);
        }else{
            botones.get(24).setBackground(Color.red);
        }

    }

    public void mostrar() {
        setVisible(true);
    }
}
