import ar.edu.unlu.rmimvc.cliente.Cliente;

import javax.swing.*;

public class AplicacionMolino {
    public static void main(String[] args) {
        Jugador j = new Jugador('a');
        j.setIdTablero(2);
        System.out.println(j.getIdTablero());
    }
}