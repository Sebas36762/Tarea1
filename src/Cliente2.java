import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente2 extends JFrame{
    private JTextPane textPane1;
    private JButton miboton2;
    private JTextField textField1;
    private JPanel Principal2;

    public Cliente2() {
        setContentPane(Principal2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400, 400);
        setTitle("Service2");
        Enviar_Texto2 mienvento2 = new Enviar_Texto2();
        miboton2.addActionListener(mienvento2);
    }
    private class Enviar_Texto2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println(textField1.getText());
            try {
                Socket misocket2=new Socket("192.168.0.14",9999);

                DataOutputStream Salida2=new DataOutputStream(misocket2.getOutputStream());
                Salida2.writeUTF(textField1.getText());

                Salida2.close();

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());


            }

        }
    }
    public static void main(String[] args) {
        Cliente2 Service2 = new Cliente2();
}
}
