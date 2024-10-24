import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame {
    private static final int WIDTH = 555;
    private static final int HEIGHT = 507; // размеры.

    private JButton btnStart, btnExit; // виджеты (кнопки).
    private SettingWindow settingWindow;
    private static Map map; // отвечает за игру.

    GameWindow(){ // класс конструктор
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрытие по нажатию на крестик
        setSize(WIDTH, HEIGHT); // установка размеров
        setLocationRelativeTo(null); // позволяет окну появиться по центру экрана

        setTitle("TicTacToe"); // заголовок
        setResizable(false); // возможность изменения размера false
        btnStart = new JButton("New Game"); 
        btnExit = new JButton("Exit"); //инициализация кнопок с текстом на них
        settingWindow = new SettingWindow(this); // this - указываем ссылку на GameWindow
        map = new Map(); // создание Map

        btnExit.addActionListener(new ActionListener() { // Добавляет прослушивателя действий к кнопке, созданный анонимный класс ActionListener чтоб использовать именно в этом методе
            @Override
            public void actionPerformed(ActionEvent e) { // определяем метод actionPerformed 
                System.exit(0); // при нажитии кнопки закрытие
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // а кнопла старт при нажатии делает видимым окно настроек
                settingWindow.setVisible(true);
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2)); // создали панели где размещаются кнопки (одна строка, 2 панели)
        panBottom.add(btnStart);
        panBottom.add(btnExit); // добавили 2 объекта (будут расположены по горизонтали в 1 линию)

        add(panBottom, BorderLayout.SOUTH); // уточняем для BorderLayout что кнопки в низу
        add(map); // а сама игра автоматически расположится по центру

        setVisible(true); // чтоб экран после всех настроек появился
    }

    static void startNewGame(int mode, int sizeX, int sizeY, int winLen){ // принимает mode, размеры и длинну(сколько подряд крестиков нужно выставить для победы)
        map.startNewGame(mode, sizeX, sizeY, winLen);
    }
}