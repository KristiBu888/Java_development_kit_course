import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerWindow extends JFrame {
//    private static final int POS_X = 500;
//    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    public static JTextArea log = new JTextArea();
    private static Chat chat;

    private static boolean isServerWorking;


    public ServerWindow(Chat chat){
        this.chat = chat;
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    log.append("Server has been stopped\n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    log.append("Server is running\n");
                }
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
//        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
//        setLocationRelativeTo(null);

        setTitle("Chat server");
        setAlwaysOnTop(true);


        JPanel panelBtn = new JPanel(new GridLayout(1, 2));
        panelBtn.add(btnStart);
        panelBtn.add(btnStop);
        add (panelBtn, BorderLayout.SOUTH);

        log.setEditable(false);
        log = new JTextArea();

        log.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);

        setVisible(true);
    }

    public static void sendMessageToChat(String clientFrom, String message){
        for (ClientGUI client : chat.getClientList()) {
            if (client.getClientStatus()){
                client.log.append(clientFrom + ": " + message + "\n");
            }
        }
    }

    public static boolean getServerStatus(){
        return isServerWorking;
    }

}