package draw_pic.view;

import circles.exceptions.BallsOverflowException;
import common.CanvasRepaintListener;
import common.Interactable;
import common.MainCanvas;
import draw_pic.sprites.Background;
import draw_pic.sprites.Picture;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
/**
 * Класс MainWindow создает главное окно приложения, где отображаются спрайты и фон.
 * Наследуется от JFrame, чтобы создать графический интерфейс, и реализует CanvasRepaintListener
 * для управления отрисовкой на холсте и Thread.UncaughtExceptionHandler для обработки исключений.
 */
public class MainWindow extends JFrame implements CanvasRepaintListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 800; // Ширина окна в пикселях
    private static final int HEIGHT = 600; // Высота окна в пикселях
    private static final String TITLE = "Circles"; // Заголовок окна, который будет отображаться в заголовке окна
    private static final int DEFAULT_COUNT_SPRITES = 10; // Начальное количество спрайтов при запуске
    private static final int MAX_COUNT_SPRITES = 15; // Максимально допустимое количество спрайтов в окне
    private static final Random random = new Random(); // Экземпляр Random для генерации случайных позиций спрайтов

    private Interactable[] sprites; // Массив для хранения спрайтов, которые будут отображаться на экране
    private int countSprites; // Переменная для отслеживания текущего количества спрайтов в массиве
    /**
     * Конструктор MainWindow инициализирует окно, добавляет элементы интерфейса
     * и устанавливает обработчики событий.
     */
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрытие программы при нажатии кнопки закрытия окна
        setSize(WIDTH, HEIGHT); // Установка размера окна
        setTitle(TITLE); // Установка заголовка окна

        initSprites(); // Вызов метода для инициализации спрайтов
        MainCanvas canvas = new MainCanvas(this); // Создание экземпляра MainCanvas и передача ссылки на него
        add(canvas); // Добавление холста (canvas) в окно, чтобы можно было отрисовывать объекты

        addMouseListener(new MouseListener(this)); // Добавление обработчика событий мыши

        setVisible(true); // Установка видимости окна (чтобы окно стало видно на экране)
    }
    /**
     * Метод initSprites инициализирует массив спрайтов и добавляет начальные спрайты
     * (один фон и несколько объектов Picture).
     */
    private void initSprites() {
        sprites = new Interactable[MAX_COUNT_SPRITES]; // Создание массива с максимальным количеством спрайтов
        sprites[0] = new Background(); // Установка фона как первого спрайта
        countSprites = 1; // Установка начального количества спрайтов на 1 (фон)
        // Добавление нескольких случайно расположенных спрайтов Picture на экран
        for (int i = 1; i < DEFAULT_COUNT_SPRITES; i++) {
            addSprite(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
    }
    /**
     * Метод addSprite добавляет новый спрайт Picture на экран по указанным координатам (x, y).
     * Если достигнуто максимальное количество спрайтов, генерируется исключение BallsOverflowException.
     * @param x начальная координата по X для нового спрайта
     * @param y начальная координата по Y для нового спрайта
     */
    public void addSprite(int x, int y){
        if (countSprites >= MAX_COUNT_SPRITES){ // Проверка, достигнуто ли максимальное количество спрайтов
            throw new BallsOverflowException(); // Генерация исключения, если больше добавлять нельзя
        }
        sprites[countSprites++] = new Picture(x, y); // Добавление нового спрайта и увеличение счетчика
    }
    /**
     * Метод removeSprite удаляет последний добавленный спрайт, если есть хотя бы один
     * спрайт, помимо фона. 
     */
    public void removeSprite(){
        if (countSprites <= 1){ // Если остался только фон, метод ничего не делает
            return;
        }
        countSprites--; // Уменьшение счетчика спрайтов, удаляя последний добавленный
    }
    /**
     * Метод onDrawFrame вызывается при каждом обновлении кадра. Он отвечает за
     * обновление и отрисовку всех спрайтов на холсте.
     * canvas холст, на котором рисуются спрайты.
     * g графический объект, используемый для рисования.
     * deltaTime время, прошедшее с последнего кадра, для обеспечения плавности анимации.
     */
    @Override
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime){
        update(canvas, deltaTime); // Обновление состояния всех спрайтов
        render(canvas, g); // Отрисовка всех спрайтов
    }

     // Метод update вызывает метод update() для каждого спрайта, обновляя его.
    private void update(MainCanvas canvas, float deltaTime){
        // Цикл по каждому спрайту, чтобы обновить их положение и состояние
        for (int i = 0; i < countSprites; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }
    /**
     * Метод render вызывает метод render() для каждого спрайта, отрисовывая
     * каждый из них на экране.
     * @param canvas холст, на котором рисуются спрайты.
     * @param g объект Graphics, используемый для отрисовки.
     */
    private void render(MainCanvas canvas, Graphics g){
        // Цикл по каждому спрайту для их отрисовки на экране
        for (int i = 0; i < countSprites; i++) {
            sprites[i].render(canvas, g);
        }
    }
    /**
     * Метод uncaughtException обрабатывает неперехваченные исключения, выводя
     * их на экран и показывая сообщение об ошибке пользователю.
     * @param t поток, в котором возникло исключение
     * @param e объект исключения
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) { // обработка исключение, когда добавляется больше спрайтов, чем позволяет MAX_COUNT_SPRITES, не прерывая выполнение программы.
        if (e instanceof BallsOverflowException){ //если исключение является экземпляром BallsOverflowException - выбрасывается исключение при попытке добавить больше спрайтов, чем допустимо
            e.fillInStackTrace(); // Метод fillInStackTrace() перезаписывает информацию о трассировке стека для исключения.
        }
    }
}

