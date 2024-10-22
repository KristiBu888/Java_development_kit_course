import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350; // размеры

    private JButton btnStart; // кнопка

    SettingWindow(GameWindow gameWindow){
        btnStart = new JButton("Start new game"); // инициализация кнопки

        setLocationRelativeTo(gameWindow); // расположение относительно стартового окна gameWindow
        setSize(WIDTH, HEIGHT); // установка размеров

        btnStart.addActionListener(new ActionListener() { // слушатель нажатий 
            @Override
            public void actionPerformed(ActionEvent e) { 
                setVisible(false); // при старте игры скрываем окно настроек
                gameWindow.startNewGame(0, 3, 3, 3); // при нажатии на старт передаются константы (не настраиваются пока) размеры, 3 в ряд выстроить что бы победить
            }
        });

        add(btnStart); // кнопка добавляется на панель, вся панель это одна кнопка
    }
}