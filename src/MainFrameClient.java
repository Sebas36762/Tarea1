import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainFrameClient extends JFrame{
    private JTextField Lector;
    private JButton miboton;
    private JPanel Principal;
    private JLabel Texto1;
    private JTextArea textArea1;

    public  MainFrameClient(){
        setContentPane(Principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400,400);
        setTitle("Service");
        Enviar_Texto mienvento=new Enviar_Texto();
        miboton.addActionListener(mienvento);

    }
    private class Enviar_Texto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(Lector.getText());
            try {
                Socket misocket=new Socket("192.168.0.14",9999);

                DataOutputStream Salida=new DataOutputStream(misocket.getOutputStream());
                Salida.writeUTF(Lector.getText());

                Salida.close();

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
                

            }

        }
    }
    public static void main(String[] args) {
        MainFrameClient Service = new MainFrameClient();

    }

    }
