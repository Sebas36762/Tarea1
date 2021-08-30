import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Esta clase sirve para crear la interfaz de servidor
 * @author Sebastián
 *
 */

public class Servidor extends JFrame implements Runnable{
    private JPanel Principal2;
    private JTextArea textArea1;

    public  Servidor() {
        setContentPane(Principal2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(500, 500);
        setTitle("Servidor");
        Thread mihilo=new Thread(this);
        mihilo.start();
    }

    public static void main(String[] args) {
        Servidor Cap = new Servidor();

    }

    /**
     * Este parametro sirve para que el servidor reciba los mensajes del cliente
     * @param "run"
     */
    @Override//ESCUCHA
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(8080);
            String nick, mensaje;
            Paquete_Cliente paquete_recibido;

            while (true) {
                Socket misocket = servidor.accept();
                ObjectInputStream paquete_datos=new ObjectInputStream(misocket.getInputStream());
                paquete_recibido=(Paquete_Cliente) paquete_datos.readObject();
                nick=paquete_recibido.getNick();
                mensaje=paquete_recibido.getMensaje();
                textArea1.append("\n"+ nick+": "+mensaje);
                Socket enviaDestinatario=new Socket("LocalHost",9090);
                ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
                paqueteReenvio.writeObject(paquete_recibido);
                enviaDestinatario.close();
                paqueteReenvio.close();

                misocket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}


