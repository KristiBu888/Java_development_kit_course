package draw_pic.sprites;

import common.MainCanvas;
import common.Sprite;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
/**
 * Класс Picture управляет изображением, которое перемещается по экрану.
 * Оно отражается от краев canvas при достижении границ.
 */
public class Picture extends Sprite {
    private static final Random random = new Random(); // Генератор случайных чисел
    private Image image; // Изображение для отображения
    private float vX; // Скорость по X
    private float vY; // Скорость по Y
    /**
     * Конструктор инициализирует изображение и его начальную скорость.
     */
    public Picture(int x, int y){
        super(x, y);
        halfHeight = 64; // Половина высоты изображения
        halfWidth = 62; // Половина ширины изображения
        try {
            image = ImageIO.read(new File("src/draw_pic/resource/picachu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e); // Если изображение не загружено, генерируется ошибка
        }
        vX = 100 + random.nextFloat(200); // Случайная скорость по X
        vY = 100 + random.nextFloat(200); // Случайная скорость по Y
    }
    /**
     * Обновляет положение изображения и отражает его при достижении краев.
     */
    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (getLeft() < canvas.getLeft()){
            setLeft(canvas.getLeft());
            vX = -vX; // Меняет направление по X при столкновении с левым краем
        }
        if (getRight() > canvas.getRight()){
            setRight(canvas.getRight());
            vX = -vX; // Меняет направление по X при столкновении с правым краем
        }
        if (getTop() < canvas.getTop()){
            setTop(canvas.getTop());
            vY = -vY; // Меняет направление по Y при столкновении с верхним краем
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY; // Меняет направление по Y при столкновении с нижним краем
        }
    }
    /**
     * Отрисовывает изображение на canvas.
     */
    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.drawImage(image, (int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight(), null);
    }
}   