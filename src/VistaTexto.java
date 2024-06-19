import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class VistaTexto extends JFrame {
    private JTextArea textArea;
    private JTextField textField;
    private Jugador j;
    private Controlador c;

    public VistaTexto(Controlador controlador, Jugador jugador) {
        c = controlador;
        c.setJugador(jugador);
        c.setVistaTexto(this);
        j = jugador;

        setTitle("Terminal Básica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        textField = new JTextField();
        add(textField, BorderLayout.SOUTH);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                processCommand(input);
                textField.setText("");
            }
        });
        ArrayList<Object> cambiosList = (ArrayList<Object>) c.getCambios();
        actualizarTablero((java.util.List<Celda>) cambiosList.get(0));
    }

    private void processCommand(String command) {
        textArea.append("> " + command + "\n");

        if (command.startsWith("poner: ")) {

                String numberString = command.substring(6).trim();
                textArea.append(" substring: "+ numberString + "\n");
                int numeroPoner = Integer.parseInt(numberString);
                if (!c.hacerMovimiento(numeroPoner)){
                    textArea.append("Movimiento invalido \n");
                }
        }else if(command.startsWith("mover: ")){
            try {
                String numberString = command.substring(7).trim();
                int numeroPoner = Integer.parseInt(numberString);
                c.hacerMovimiento(numeroPoner);
            } catch (NumberFormatException e) {
                textArea.append("Comando 'mover' inválido. Uso correcto: poner: (número)\n");
            }
        }
        else {
            textArea.append("Comando '" + command + "' no reconocido.\n");
        }
    }

    public void actualizarVista(Object cambios) {
        ArrayList<Object> cambiosList = (ArrayList<Object>) cambios;
        java.util.List<Celda> celdas = (java.util.List<Celda>) cambiosList.get(0);
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
        int k = 0;
        textArea.setText("");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if(celdas.get(k).getX() == i && celdas.get(k).getY() == j){
                    //textArea.append(celdas.get(k).getX() + " " + celdas.get(k).getY() + " " + celdas.get(k).getValor());
                    textArea.append(" " + celdas.get(k).getValor() + " ");
                    k = k + 1;
                }else{
                    textArea.append(" --- ");
                }
            }
            textArea.append("\n");
        }
    }
}