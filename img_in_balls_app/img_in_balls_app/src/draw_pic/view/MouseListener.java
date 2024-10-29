package draw_pic.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * Класс MouseListener управляет событиями мыши для добавления и удаления спрайтов.
 * Наследует MouseAdapter для реализации событий клика.
 */
public class MouseListener extends MouseAdapter {
    private final MainWindow mainWindow; // Ссылка на главное окно
     /**
     * Конструктор принимает главное окно, с которым будет взаимодействовать.
     */
    public MouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    /**
     * Добавляет спрайт в точке клика при нажатии левой кнопки мыши.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){ // Проверка на левую кнопку
            mainWindow.removeSprite(); // Добавление спрайта
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Проверка на правую кнопку
            mainWindow.addSprite(e.getX(), e.getY()); // Удаление последнего спрайта
        }
    }
}
