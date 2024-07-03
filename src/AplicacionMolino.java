import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        ModeloMolino modelo = new ModeloMolino();
        Login l1 = new Login(modelo);
        Login l2 = new Login(modelo);


        SwingUtilities.invokeLater(() -> {
            l1.setVisible(true);
            l2.setVisible(true);

        });
    }
}