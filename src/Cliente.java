import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Esta clase sirve para crear la interfaz de cliente
 * @author Sebastián
 *
 */

public class Cliente extends JFrame implements Runnable{
    private JTextField textField1;
    private JButton enviarButton;
    private JPanel Principal;
    private JTextArea textArea2;
    private JTextField usuario;


    public  Cliente() {
        setContentPane(Principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(500, 500);
        setTitle("Chat");
        Envia_texto mievento=new Envia_texto();
        enviarButton.addActionListener(mievento);
        Thread hilo=new Thread(this);
        hilo.start();



    }
    /**
     * Este parametro sirve para que el cliente reciba los mensajes que llegan al servidor
     * @param "run"
     */
    @Override
    public void run() {
        try{
            ServerSocket servidor_Cliente=new ServerSocket(9090);
            Socket cliente;
            Paquete_Cliente paquete_recibido;
                while (true){
                cliente=servidor_Cliente.accept();

                ObjectInputStream flujoentrada=new ObjectInputStream(cliente.getInputStream());
                paquete_recibido= (Paquete_Cliente) flujoentrada.readObject();
                textArea2.append("\n"+paquete_recibido.getNick()+": "+paquete_recibido.getMensaje());
            }


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private class Envia_texto implements ActionListener{
        /**
         * Este parametro sirve para que el clienete envie los mensajes al servirdor lo cual agarra los datos de la clase "Paquete_Cliente"
         * @param "run"
         */
        @Override//ENVIA
        public void actionPerformed(ActionEvent e) {
            try {
                Socket misocket=new Socket("LocalHost",8080);

                Paquete_Cliente datos=new Paquete_Cliente();
                datos.setNick(usuario.getText());
                datos.setMensaje(textField1.getText());
                ObjectOutputStream paquete_datos=new ObjectOutputStream(misocket.getOutputStream());
                paquete_datos.writeObject(datos);
                misocket.close();


            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        Cliente Service = new Cliente();


    }


    }
/**
 * Guarda los datos de la interfaz y la envia al servidor
 * @author Sebas
 */
class Paquete_Cliente implements Serializable {
    private String usuario,mensaje;

    public String getNick() {
        return usuario;
    }

    public void setNick(String nick) {
        this.usuario = nick;
    }



    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

