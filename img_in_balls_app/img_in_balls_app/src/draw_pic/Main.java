package draw_pic;

import draw_pic.view.MainWindow;
import javax.swing.*;
/**
 * Основной класс Main, содержащий точку входа в программу.
 * Создает и запускает главное окно приложения в отдельном потоке.
 */
public class Main {
    /**
     * Точка входа в программу.
     * Метод main запускает интерфейсное окно приложения в потоке диспетчеризации событий(EDT).
     */
    public static void main(String[] args) {
        // Метод SwingUtilities.invokeLater используется для запуска кода в потоке диспетчеризации событий для обеспечения безопасного обновления интерфейса.
        // Runnable и метод run(): Создает анонимный класс, реализующий интерфейс Runnable, и его метод run() запускает создание главного окна MainWindow в потоке EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(); // инициализирует (создает экземпляр главного окна MainWindow) и отображает главное окно приложения
            }
        });
    }
}