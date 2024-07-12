import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class VistaTexto extends JFrame implements Ivista{
    private JTextArea textArea;
    private JTextField textField;
    private Controlador controlador;
    private int[][] posiciones = {
            {0, 0}, {0, 3}, {0, 6}, {1, 1}, {1, 3}, {1, 5},
            {2, 2}, {2, 3}, {2, 4}, {3, 0}, {3, 1}, {3, 2},
            {3, 4}, {3, 5}, {3, 6}, {4, 2}, {4, 3}, {4, 4},
            {5, 1}, {5, 3}, {5, 5}, {6, 0}, {6, 3}, {6, 6}
    };

    public VistaTexto(Controlador c) throws RemoteException {
        this.controlador = c;
        setTitle("Terminal Básica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 32)); // Cambia el tamaño de la fuente aquí
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

        // Inicializa el tablero con el estado actual
        ArrayList<Object> cambiosList = (ArrayList<Object>) c.getCambios();
        actualizarTablero((List<Celda>) cambiosList.get(0));
    }

    private void processCommand(String command) {
        textArea.append("> " + command + "\n");

        if (command.startsWith("poner: ")) {
            handlePonerCommand(command.substring(7).trim());
        } else if (command.startsWith("mover: ")) {
            handleMoverCommand(command.substring(7).trim());
        } else if (command.startsWith("eliminar: ")) {
            handleEliminarCommand(command.substring(10).trim());
        } else {
            textArea.append("Comando '" + command + "' no reconocido.\n");
        }
    }

    private void handlePonerCommand(String input) {
        String[] numbers = input.split("\\s+");

        if (numbers.length != 2) {
            textArea.append("Comando 'poner' inválido. Uso correcto: poner: (número1) (número2)\n");
            return;
        }

        try {
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            int indice = getIndice(x, y);

            if (indice == -1 || !controlador.hacerMovimiento(indice)) {
                textArea.append("Error: Movimiento inválido. Asegúrate de que las coordenadas sean válidas.\n");
            }
        } catch (NumberFormatException e) {
            textArea.append("Error: ambos valores deben ser números enteros.\n");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleMoverCommand(String input) {
        String[] numbers = input.split("\\s+");

        if (numbers.length != 4) {
            textArea.append("Comando 'mover' inválido. Uso correcto: mover: (número1) (número2) (número3) (número4)\n");
            return;
        }

        try {
            int x1 = Integer.parseInt(numbers[0]);
            int y1 = Integer.parseInt(numbers[1]);
            int x2 = Integer.parseInt(numbers[2]);
            int y2 = Integer.parseInt(numbers[3]);
            int indice1 = getIndice(x1, y1);
            int indice2 = getIndice(x2, y2);

            if (indice1 == -1 || indice2 == -1) {
                textArea.append("Error: Coordenadas inválidas. Asegúrate de que las coordenadas sean válidas.\n");
                return;
            }

            if (!controlador.hacerMovimiento(indice1) || !controlador.hacerMovimiento(indice2)) {
                controlador.hacerMovimiento(indice1); // Deshacer el primer movimiento si el segundo falla
                textArea.append("Error: Movimiento inválido. Asegúrate de que las coordenadas sean válidas y que la casilla de destino esté libre.\n");
            } else {
                textArea.append("Movimiento exitoso.\n");
            }
        } catch (NumberFormatException | RemoteException e) {
            textArea.append("Error: todos los valores deben ser números enteros.\n");
        }
    }

    private void handleEliminarCommand(String input) {
        String[] numbers = input.split("\\s+");

        if (numbers.length != 2) {
            textArea.append("Comando 'eliminar' inválido. Uso correcto: eliminar: (número1) (número2)\n");
            return;
        }

        try {
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            int indice = getIndice(x, y);

            if (indice == -1 || !controlador.hacerMovimiento(indice)) {
                textArea.append("Error: Movimiento inválido. Asegúrate de que las coordenadas sean válidas.\n");
            }
        } catch (NumberFormatException e) {
            textArea.append("Error: ambos valores deben ser números enteros.\n");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
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

    public void actualizarVista(Object cambios) throws RemoteException {
        ArrayList<Object> cambiosList = (ArrayList<Object>) cambios;
        List<Celda> celdas = (List<Celda>) cambiosList.get(0);
        Jugador jugadorActual = (Jugador) cambiosList.get(2);
        FaseJuego faseActual = (FaseJuego) cambiosList.get(1);
        actualizarTablero(celdas);

        if (faseActual == FaseJuego.FIN) {
            JOptionPane.showMessageDialog(this, "¡Juego terminado!");
        } else {
            setTitle("Jugador: " + jugadorActual.getSimbolo() + " Turno: " + jugadorActual.getSimbolo() + "\n" + faseActual);
        }
    }

    private void actualizarTablero(List<Celda> celdas) {
        int k = 0;
        textArea.setText("");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (k < celdas.size() && celdas.get(k).getX() == i && celdas.get(k).getY() == j) {
                    if (celdas.get(k).getValor() == '-') {
                        textArea.append(" . ");
                    } else {
                        textArea.append(" " + celdas.get(k).getValor() + " ");
                    }
                    k++;
                } else {
                    if ((i == 1 || i == 2 || i == 4 || i == 5) && (j == 1 || j == 5 || j == 0 || j == 6)) {
                        textArea.append(" | ");
                    } else {
                        textArea.append(" - ");
                    }
                }
            }
            textArea.append("\n");
        }
    }

    public void mostrar() {
        setVisible(true);
    }


}
