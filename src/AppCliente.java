import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

public class AppCliente {

    public static void main(String[] args) {
        ArrayList<String> ips = Util.getIpDisponibles();
        String ip = "127.0.0.1";
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                9999
        );

        String ipServidor = "127.0.0.1";;
        String portServidor = "8888"
                ;
        Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
        Login l = new Login(c);
        l.lmostrar();

    }

}