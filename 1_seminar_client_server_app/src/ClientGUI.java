import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 0;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    static int bias;



    public JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private JTextField tfIPAdress;
    //    private final JTextField tfIPAdress = new JTextField("IP 127.0.0.1");
    private JTextField tfPort;
    //    private final JTextField tfPort = new JTextField("8989");
    public JTextField tfLogin;
    //    private final JTextField tfLogin = new JTextField("Petr Petrovich");
    private JPasswordField tfPassword;
    //    private final JPasswordField tfPassword = new JPasswordField("12345");

    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelFooter = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private static int countClient;

    private static boolean isClientLogin = false;
    //private static String message;


    public ClientGUI(Client client){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBias();
        setBounds(POS_X + bias, POS_Y + bias, WIDTH, HEIGHT);
//        setSize(WIDTH, HEIGHT);
        setCountClient();
        setTitle("ChatClient" + countClient);
        tfIPAdress = new JTextField(client.getIpAdress());
        tfPort = new JTextField(client.getPort());
        tfLogin = new JTextField(client.getName());
        tfPassword = new JPasswordField(client.getPassword());

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelTop.add(tfMessage);
        panelTop.add(btnSend);
        add(panelTop, BorderLayout.NORTH);

        panelFooter.add(tfMessage, BorderLayout.CENTER);
        panelFooter.add(btnSend, BorderLayout.EAST);
        add(panelFooter, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerWindow.getServerStatus()) {
                    log.append("Вы успешно подключились!\n\n");
                    ServerWindow.log.append(client.getName() + " Подключился к беседе\n");
                    isClientLogin = true;

                    btnSend.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String message = tfMessage.getText();
                            if (ServerWindow.getServerStatus()) {
                                if (message != null) {
                                    ServerWindow.sendMessageToChat(tfLogin.getText(), message);
//                                    log.append(String.format("%s: %s\n", client.getName(), message));
                                    ServerWindow.log.append(client.getName() + ": " + message + "\n");
                                }
                            } else {
                                log.append("Сервер не доступен\nСообщение не отправлено!\n\n");
                            }
                        }
                    });
                }else{
                    log.append("Сервер не доступен\nПодключение не удалось!\n\n");
                }
            }
        });




        setVisible(true);
    }

    public static boolean getClientStatus(){
        return isClientLogin;
    }


    private void setBias(){
        bias += 100;
    }

    private void setCountClient(){
        countClient ++;
    }


}

