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
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JButton calcularButton;


    public  Cliente() {
        setContentPane(Principal);//Se seleccion la interfaz creada en suing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);//Se establece que la ventana no cambie su tamaño
        setSize(500, 500);
        setTitle("Chat");//Titulo de la ventana
        Envia_texto mievento=new Envia_texto();//Se establece la funcion del boton
        enviarButton.addActionListener(mievento);//Se establece la que el boton tenga una accion al tocar
        Thread hilo=new Thread(this);//Hilo para que la interfaz funcione en todo momento
        hilo.start();


        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Porcentaje= new String();
                Porcentaje=text1.getText();
                double P = Double.parseDouble(Porcentaje);
                String Producto= new String();
                Producto=text2.getText();
                double Pro = Double.parseDouble(Producto);
                String Peso= new String();
                Peso=text3.getText();
                double Pes = Double.parseDouble(Peso);
                double R= (Pro*P/100)+(Pes*0.15);
                System.out.println(R);
                textArea2.append(String.valueOf(R));
            }
        });
    }


    /**
     * Este parametro sirve para que el cliente reciba los mensajes que llegan al servidor
     * @param "run"
     */
    @Override
    public void run() {
        try{
            ServerSocket servidor_Cliente=new ServerSocket(9090);// Socket que permite la conexion del servidor
            Socket cliente;
            Paquete_Cliente paquete_recibido;
                while (true){
                cliente=servidor_Cliente.accept();

                ObjectInputStream flujoentrada=new ObjectInputStream(cliente.getInputStream());
                paquete_recibido= (Paquete_Cliente) flujoentrada.readObject();
                textArea2.append("\n"+paquete_recibido.getNick()+": "+paquete_recibido.getMensaje());
            }


        }catch (Exception ex){
            System.out.println(ex.getMessage());// se imprime el mensaje del servidor
        }
    }

    private class Envia_texto implements ActionListener{//funcion para enviar los mensajes dandole el click al boton
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

