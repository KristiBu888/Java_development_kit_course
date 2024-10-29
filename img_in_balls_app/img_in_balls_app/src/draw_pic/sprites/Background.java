package draw_pic.sprites;

import common.Interactable;
import common.MainCanvas;
import java.awt.*;
/**
 * Класс Background управляет фоном, который меняет цвет в зависимости от времени.
 */
public class Background implements Interactable {
    private float time; // Переменная времени для отслеживания текущего момента
    private static final float AMPLITUDE = 255f / 2; // Максимальная амплитуда цвета
    private Color color; // Текущий цвет фона

    /**
     * Обновляет цвет фона на основе времени, меняя значения RGB.
     */
    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }
    /**
     * Устанавливает цвет фона для canvas на основе текущего значения color.
     */
    @Override
    public void render(MainCanvas canvas, Graphics g) {
        canvas.setBackground(color); // устанавливает цвет фона
    }
}