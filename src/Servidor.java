import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame implements Runnable{
    private JTextField textField1;
    private JButton enviarButton;
    private JPanel Principal2;
    private JTextArea textArea1;
    private JTextField NICKTextField;
    private JTextField IPTextField;

    public  Servidor() {
        setContentPane(Principal2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400, 400);
        setTitle("Service2");
        Thread mihilo=new Thread(this);
        mihilo.start();
    }

    public static void main(String[] args) {
        Servidor Service2 = new Servidor();

    }

    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(8080);
            while (true) {
                Socket misocket = servidor.accept();
                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                String mensaje_texto = flujo_entrada.readUTF();
                textArea1.append("\n" + mensaje_texto);
                misocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
