package circles.sprites;

import common.Interactable;
import common.MainCanvas;
import java.awt.*;

/**
 * Класс Background реализует интерфейс Interactable и отвечает за анимированное изменение
 * цвета фона, создавая плавный переход между цветами на основе синусоидальных функций.
 */
public class Background implements Interactable {
    // Поле времени, используемое для расчета цветов на основе синусоидальных функций
    private float time;

    // Постоянная амплитуда цвета, которая равна половине максимального значения RGB (255)
    private static final float AMPLITUDE = 255f / 2;

    // Поле для хранения текущего цвета фона
    private Color color;

    /**
     * Метод update вызывается для обновления состояния фона. Он вычисляет новый цвет,
     * основываясь на текущем времени и синусоидальных функциях, чтобы создать плавную анимацию.
     * @param canvas холст, на котором рисуются все объекты.
     * @param deltaTime время, прошедшее с момента последнего обновления, используется для
     *                  обеспечения плавности анимации, независимо от производительности.
     */
    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        // Увеличиваем значение времени с учетом deltaTime для плавной анимации
        time += deltaTime;

        // Вычисляем значения цветов с помощью синусоидальных функций, чтобы создать плавные
        // переходы цвета по каналам RGB
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2)); // цветовой канал красного
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3)); // цветовой канал зеленого
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time)); // цветовой канал синего

        // Создаем новый цвет с рассчитанными значениями
        color = new Color(red, green, blue);
    }

    /**
     * Метод render устанавливает цвет фона холста на текущий цвет.
     * @param canvas холст, на котором рисуются все объекты.
     * @param g объект Graphics, используемый для отрисовки.
     */
    @Override
    public void render(MainCanvas canvas, Graphics g) {
        // Устанавливаем цвет фона canvas на текущий цвет color
        canvas.setBackground(color);
    }
}
