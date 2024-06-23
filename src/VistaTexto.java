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
    private int[][] posiciones = {
            {0, 0}, {0, 3}, {0, 6}, {1, 1}, {1, 3}, {1, 5},
            {2, 2}, {2, 3}, {2, 4}, {3, 0}, {3, 1}, {3, 2},
            {3, 4}, {3, 5}, {3, 6}, {4, 2}, {4, 3}, {4, 4},
            {5, 1}, {5, 3}, {5, 5}, {6, 0}, {6, 3}, {6, 6}
    };

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
            String numbersString = command.substring(6).trim();
            textArea.append(" substring: " + numbersString + "\n");

            String[] numbers = numbersString.split("\\s+");

            if (numbers.length == 2) {
                try {
                    int numero1 = Integer.parseInt(numbers[0]);
                    int numero2 = Integer.parseInt(numbers[1]);

                    textArea.append("Número 1: " + numero1 + ", Número 2: " + numero2 + "\n");

                    // Aquí puedes usar los números como necesites, por ejemplo:
                    if (!c.hacerMovimiento(getIndice(numero1, numero2))) {
                        textArea.append("poner invalido \n");
                    }
                } catch (NumberFormatException e) {
                    textArea.append("Error: ambos valores deben ser números enteros.\n");
                }
            } else {
                textArea.append("Comando 'poner' inválido. Uso correcto: poner: (número1) (número2)\n");
            }
        } else if (command.startsWith("mover: ")) {
            String numbersString = command.substring(7).trim();
            textArea.append(" substring: " + numbersString + "\n");

            String[] numbers = numbersString.split("\\s+");

            if (numbers.length == 4) {
                try {
                    int numero1 = Integer.parseInt(numbers[0]);
                    int numero2 = Integer.parseInt(numbers[1]);
                    int numero3 = Integer.parseInt(numbers[2]);
                    int numero4 = Integer.parseInt(numbers[3]);

                    textArea.append("Número 1: " + numero1 + ", Número 2: " + numero2 + ", Número 3: " + numero3 + ", Número 4: " + numero4 + "\n");

                    if (c.hacerMovimiento(getIndice(numero1, numero2))) {
                        if (c.hacerMovimiento(getIndice(numero3, numero4))){
                            textArea.append("se logro");
                        }else{
                            c.hacerMovimiento(getIndice(numero1, numero2));
                            textArea.append("casilla invalida 2");
                        }
                    }else{
                        textArea.append("casilla invalida");
                    }
                } catch (NumberFormatException e) {
                    textArea.append("Error: todos los valores deben ser números enteros.\n");
                }
            } else {
                textArea.append("Comando 'mover' inválido. Uso correcto: mover: (número1) (número2) (número3) (número4)\n");
            }
        }else if (command.startsWith("eliminar: ")) {
            String numbersString = command.substring(9).trim();
            textArea.append(" substring: " + numbersString + "\n");

            String[] numbers = numbersString.split("\\s+");

            if (numbers.length == 2) {
                try {
                    int numero1 = Integer.parseInt(numbers[0]);
                    int numero2 = Integer.parseInt(numbers[1]);

                    if (!c.hacerMovimiento(getIndice(numero1, numero2))) {
                        textArea.append("eliminar invalido \n");
                    }

                } catch (NumberFormatException e) {
                    textArea.append("Error: ambos valores deben ser números enteros.\n");
                }
            } else {
                textArea.append("Comando 'eliminar' inválido. Uso correcto: eliminar: (número1) (número2)\n");
            }
        } else {
            textArea.append("Comando '" + command + "' no reconocido.\n");
        }
    }


    private int getIndice(int x, int y) {
        for (int i = 0; i < posiciones.length; i++) {
            if (posiciones[i][0] == x && posiciones[i][1] == y) {
                return i;
            }
        }
        return -1;
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
                if (celdas.get(k).getX() == i && celdas.get(k).getY() == j) {
                    //textArea.append(celdas.get(k).getX() + " " + celdas.get(k).getY() + " " + celdas.get(k).getValor());
                    textArea.append(" " + celdas.get(k).getValor() + " ");
                    k = k + 1;
                } else {
                    textArea.append(" --- ");
                }
            }
            textArea.append("\n");
        }

    }
}