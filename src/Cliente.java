import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends JFrame{
    private JTextField textField1;
    private JButton enviarButton;
    private JPanel Principal;
    private JTextArea textArea1;
    private JTextField IPTextField;
    private JTextField NICKTextField;


    public  Cliente() {
        setContentPane(Principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(400, 400);
        setTitle("Service");
        Envia_texto mievento=new Envia_texto();
        enviarButton.addActionListener(mievento);
    }
    private class Envia_texto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket misocket=new Socket("192.168.0.12",8080);
                DataOutputStream flujo_salida=new DataOutputStream(misocket.getOutputStream());
                flujo_salida.writeUTF(textField1.getText());
                flujo_salida.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
    public static void main(String[] args) {
        Cliente Service = new Cliente();


    }
}
class PaqueteEnvio{
    private String NICKTextField,IPTextField, mensaje;

    public String getNICKTextField() {
        return NICKTextField;
    }

    public void setNICKTextField(String NICKTextField) {
        this.NICKTextField = NICKTextField;
    }

    public String getIPTextField() {
        return IPTextField;
    }

    public void setIPTextField(String IPTextField) {
        this.IPTextField = IPTextField;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
