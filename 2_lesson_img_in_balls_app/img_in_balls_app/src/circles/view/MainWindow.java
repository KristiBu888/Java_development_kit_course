package circles.view;

import circles.exceptions.BallsOverflowException;
import circles.sprites.Background;
import circles.sprites.Ball;
import common.CanvasRepaintListener;
import common.Interactable;
import common.MainCanvas;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MainWindow extends JFrame implements CanvasRepaintListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 800; // ширина
    private static final int HEIGHT = 600; // высота
    private static final String TITLE = "Circles"; // заголовок
    private static final int DEFAULT_COUNT_SPRITES = 10; // количество спрайтов на старте
    private static final int MAX_COUNT_SPRITES = 15; // максимальное количество спрайтов
    private static final Random random = new Random(); // переменная для рандома

    private Interactable[] sprites; // массив спрайтов (кругов)
    private int countSprites;

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);

        initSprites();
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);

        addMouseListener(new MouseListener(this));

        setVisible(true);
    }

    private void initSprites() {
        sprites = new Interactable[MAX_COUNT_SPRITES];
        sprites[0] = new Background();
        countSprites = 1;
        for (int i = 1; i < DEFAULT_COUNT_SPRITES; i++) {
            addSprite(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
    }

    public void addSprite(int x, int y){
        if (countSprites >= MAX_COUNT_SPRITES){
            throw new BallsOverflowException();
        }
        sprites[countSprites++] = new Ball(x, y);
    }

    public void removeSprite(){
        if (countSprites <= 1){
            return;
        }
        countSprites--;
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime){
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime){
        for (int i = 0; i < countSprites; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }
    private void render(MainCanvas canvas, Graphics g){
        for (int i = 0; i < countSprites; i++) {
            sprites[i].render(canvas, g);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof BallsOverflowException){
            e.fillInStackTrace();
        }
    }
}
