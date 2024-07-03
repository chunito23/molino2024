import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField userField;
    private JComboBox<String> viewOptions;
    private ModeloMolino m;

    public Login(ModeloMolino m) {
        this.m = m;
        // Configuración del marco
        setTitle("Login");
        setSize(300, 400);  // Ajuste del tamaño para dar espacio a la imagen y los componentes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación del panel con imagen de fondo
        JPanel backgroundPanel = new BackgroundPanel("/molino.jpg");
        backgroundPanel.setLayout(new BorderLayout());

        // Creación del panel de componentes con BoxLayout
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false); // Hacer el panel transparente
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Añadiendo componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        topPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        userField = new JTextField(10);
        topPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        topPanel.add(new JLabel("Vista:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        viewOptions = new JComboBox<>(new String[]{"Vista Gráfica", "Vista de Texto"});
        topPanel.add(viewOptions, gbc);

        // Panel para el botón de login
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Hacer el panel transparente
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        bottomPanel.add(loginButton);

        // Añadiendo los paneles al backgroundPanel
        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Añadiendo el panel de fondo al marco
        add(backgroundPanel);

        // Acción del botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String selectedView = (String) viewOptions.getSelectedItem();
                Controlador c = new Controlador(m);
                if ("Vista Gráfica".equals(selectedView)) {
                    VistaGraficaMolino vg = new VistaGraficaMolino(c, new Jugador(username.charAt(0)));
                    vg.setVisible(true);
                } else {
                    VistaTexto vt = new VistaTexto(c, new Jugador(username.charAt(0)));
                    vt.setVisible(true);
                }
                m.agregarObservador(c);
                dispose();
            }
        });
    }

    // Clase personalizada para el panel con fondo de imagen
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        // Supongamos que ModeloMolino es una clase existente
        ModeloMolino modelo = new ModeloMolino();
        Login login = new Login(modelo);
        login.setVisible(true);
    }
}
