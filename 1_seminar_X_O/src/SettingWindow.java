import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 350; // размеры.
    

    private final JButton btnStart; // кнопка

    SettingWindow(GameWindow gameWindow){
        String FIELD_SIZE_PREFIX = null;
        int MIN_FIELG_SIZE = 3;
        int MAX_FIELG_SIZE = 10;
        int MIN_WIN_LENGTH = 3;
        String WIN_LEIGTH_PREFIX = null;

        JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELG_SIZE);
        JLabel lbWinLength = new JLabel(WIN_LEIGTH_PREFIX + MIN_FIELG_SIZE);
        JSlider slideFieldSize = new JSlider(MIN_FIELG_SIZE, MAX_FIELG_SIZE, MIN_FIELG_SIZE);
        JSlider slideWinLen = new JSlider(MIN_WIN_LENGTH, MAX_FIELG_SIZE, MIN_FIELG_SIZE);
        
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LEIGTH_PREFIX + slideWinLen.getValue());
            }
        });

        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        setLayout(new GridLayout(10,1));
        add (new JLabel(" Выберите режим игры:"));
        ButtonGroup bg = new ButtonGroup();
        JRadioButton pvc = new JRadioButton("Человек против человека");
        JRadioButton pvp = new JRadioButton("Человек против компа");
        bg.add(pvc);
        bg.add(pvp);
        add(pvc);
        add(pvp);
        add (new JLabel (" Выберите размер поля"));
        add (new JLabel (" Установленный размер поля"));
        add (new JSlider(MIN_FIELG_SIZE, MAX_FIELG_SIZE, MIN_WIN_LENGTH));
        add (new JLabel (" Выберите длину для победы"));
        add (new JLabel (" Установленная длина победы"));
        add (new JSlider(MIN_FIELG_SIZE, MAX_FIELG_SIZE, MIN_WIN_LENGTH));

        
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