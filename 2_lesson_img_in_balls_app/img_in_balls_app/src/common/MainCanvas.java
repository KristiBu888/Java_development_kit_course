package common;

import java.awt.*;
import javax.swing.*;
/**
 * Класс MainCanvas представляет собой контейнер, на котором происходит отрисовка спрайтов.
 * Он управляет обновлением и перерисовкой контента на экране.
 */
public class MainCanvas extends JPanel {
    private final CanvasRepaintListener controller; // слушатель для обработки отрисовки(CanvasRepaintListener - является интерфейсом в кот. может быть любой класс реализующий метод в onDrawFrame)
    private long lastFrameTime; // Время предыдущего кадра в наносекундах

    /**
     * Конструктор устанавливает слушателя перерисовки и начальное время кадра.
     */
    public MainCanvas(CanvasRepaintListener controller){
        this.controller = controller;
        lastFrameTime = System.nanoTime();// Получаем текущее время
    }
    /**
     * Метод paintComponent выполняет отрисовку каждого кадра,
     * обновляя данные с учетом времени, прошедшего с прошлого кадра.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        onDraw(g); // что и как рисовать
        sleep(16); // сон
        repaint(); // Запрашивает перерисовку canvas для следующего кадра
    }

    private void onDraw(Graphics g) { // в классе прописано что и как рисовать
        long curFrameTime = System.nanoTime(); // Текущее время в наносекундах
        float deltaTime = (curFrameTime - lastFrameTime) * 0.000000001f; // Перевод в секунды
        lastFrameTime = curFrameTime;
        controller.onDrawFrame(this, g, deltaTime); // (передаём: канвас(для рисования на нём), объект графики, сколько времени прошло от последнего изменения)
    }

    private void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    // Методы для получения границ canvas
    public int getLeft(){
        return 0;
    }

    public int getRight(){
        return getWidth() - 1;
    }

    public int getTop(){
        return 0;
    }

    public int getBottom(){
        return getHeight() - 1;
    }
}