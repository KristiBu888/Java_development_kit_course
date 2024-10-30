package server.server.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import server.server.domain.ServerController;

// Класс ServerWindow - графический интерфейс сервера (он реализует интерфейс ServerView).
public class ServerWindow extends JFrame implements ServerView {

    // Константы для установки ширины и высоты окна.
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    // Кнопки для запуска и остановки сервера.
    private JButton btnStart, btnStop;
    // Текстовая область для вывода логов сервера.
    private JTextArea log;

    // Поле для хранения ссылки на контроллер сервера.
    private ServerController serverController;

    // Конструктор ServerWindow, который настраивает окно и добавляет панели.
    public ServerWindow(){
        setting(); // Настраивает основные параметры окна.
        createPanel(); // Создаёт и добавляет панели с компонентами в окно.

        setVisible(true); // Устанавливает видимость окна на true, чтобы его было видно.
    }

    // Метод интерфейса ServerView для установки контроллера сервера.
    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрытие программы при закрытии окна.
        setSize(WIDTH, HEIGHT); // Установка ширины и высоты окна.
        setResizable(false); // Запрещает изменение размера окна.
        setTitle("Chat server"); // Устанавливает заголовок окна.
        setLocationRelativeTo(null); // Центрирует окно на экране.
    }

     // Метод для получения ссылки на контроллер сервера, установленного в окне.
    public ServerController getConnection(){
        return serverController;
    }

    // Метод createPanel() создаёт и добавляет компоненты в основную панель окна.
    private void createPanel() {
        log = new JTextArea(); // Создаёт текстовую область для отображения логов.
        add(log); // Добавляет текстовую область в основную панель окна.
        add(createButtons(), BorderLayout.SOUTH); // Добавляет панель с кнопками в нижнюю часть окна.
    }

    // Метод createButtons() создаёт панель с кнопками Start и Stop и возвращает её.
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2)); // Создаёт панель с 2-колоночной сеткой для размещения 
        btnStart = new JButton("Start");  // Создаёт кнопку Start.
        btnStop = new JButton("Stop"); // Создаёт кнопку Stop.

        // Устанавливает обработчик нажатия для кнопки Start.
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.start(); // Запускает сервер через метод start() контроллера сервера.
            }
        });

        // Устанавливает обработчик нажатия для кнопки Stop.
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stop(); // Останавливает сервер через метод stop() контроллера сервера.
            }
        });

         // Добавляет кнопки Start и Stop в панель.
        panel.add(btnStart);
        panel.add(btnStop);
        return panel; // Возвращает панель с кнопками.
    }

    // Реализация метода showMessage интерфейса ServerView, добавляющего сообщение в лог.
    @Override
    public void showMessage(String msg) {
        log.append(msg); // Добавляет переданное сообщение в текстовую область log.
    }
}
