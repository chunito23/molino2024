import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField userField;
    private JComboBox<String> viewOptions;
    private ModeloMolino m;

    // Lista de usuarios permitidos
    private  String usuario;

    public Login(ModeloMolino m) {
        this.m = m;
        // Configuración del marco
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Añadiendo componentes al panel
        panel.add(new JLabel("Usuario:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Vista:"));
        viewOptions = new JComboBox<>(new String[]{"Vista Gráfica", "Vista de Texto"});
        panel.add(viewOptions);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        // Añadiendo el panel al marco
        add(panel);

        // Acción del botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String selectedView = (String) viewOptions.getSelectedItem();
                Controlador c = new Controlador(m);
                if ("Vista Gráfica".equals(selectedView)) {
                    VistaGraficaMolino vg = new VistaGraficaMolino(c,new Jugador(username.charAt(0)));
                    vg.setVisible(true);
                } else {
                    VistaTexto vt = new VistaTexto(c,new Jugador(username.charAt(0)));
                    vt.setVisible(true);
                }
                m.agregarObservador(c);
                dispose();
            }
        });
    }

    private void showGraphicalView() {
        JFrame graphicalFrame = new JFrame("Vista Gráfica");
        graphicalFrame.setSize(400, 300);
        graphicalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graphicalFrame.setLocationRelativeTo(null);

        // Aquí puedes añadir componentes gráficos a la ventana
        graphicalFrame.add(new JLabel("Bienvenido a la Vista Gráfica"), BorderLayout.CENTER);

        graphicalFrame.setVisible(true);
    }

    private void showTextView() {
        JFrame textFrame = new JFrame("Vista de Texto");
        textFrame.setSize(400, 300);
        textFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textFrame.setLocationRelativeTo(null);

        // Aquí puedes añadir componentes de texto a la ventana
        JTextArea textArea = new JTextArea("Bienvenido a la Vista de Texto");
        textArea.setEditable(false);
        textFrame.add(textArea, BorderLayout.CENTER);

        textFrame.setVisible(true);
    }

}