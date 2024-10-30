package common;

import java.awt.*;

/**
 * Интерфейс CanvasRepaintListener определяет метод для отрисовки каждого кадра на canvas.
 * Этот интерфейс реализуется классами, которые хотят управлять отрисовкой на canvas.
 */
public interface CanvasRepaintListener {
    /**
     * Вызывается для обновления и отрисовки каждого кадра.
     *
     * @param canvas Контейнер для отрисовки.
     * @param g Графический контекст для рисования на canvas.
     * @param deltaTime Время, прошедшее с последнего кадра.
     */
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}
