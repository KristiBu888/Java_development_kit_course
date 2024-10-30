package common;

import java.awt.*;
/**
 * Абстрактный класс Sprite представляет собой базовый объект для всех спрайтов в игре.
 * Определяет базовые свойства (координаты и размеры) и методы для работы с положением.
 */
public abstract class Sprite implements Interactable {
    protected float x; // Координата X центра спрайта
    protected float y; // Координата Y центра спрайта
    protected float halfWidth; // Половина ширины спрайта для удобства расчета
    protected float halfHeight; // Половина высоты спрайта
    /**
     * Конструктор инициализирует спрайт с заданными координатами.
     */
    public Sprite(float x, float y) {
        this.x = x;
        this.y = y;
    }
    // Методы для получения и установки границ спрайта (лево, право, верх, низ)
    protected float getLeft(){
        return x - halfWidth;
    }

    protected void setLeft(float left){
        x = left + halfWidth;
    }

    protected float getRight(){
        return x + halfWidth;
    }

    protected void setRight(float right){
        x = right - halfWidth;
    }

    protected float getTop(){
        return y - halfHeight;
    }

    protected void setTop(float top){
        y = top + halfHeight;
    }

    protected float getBottom(){
        return y + halfHeight;
    }

    protected void setBottom(float bottom){
        y = bottom - halfHeight;
    }
    /**
     * Получает полную ширину спрайта.
     */
    protected float getWidth(){
        return 2f * halfWidth;
    }
    /**
     * Получает полную высоту спрайта.
     */
    protected float getHeight(){
        return 2f * halfHeight;
    }
    
    public abstract void update(MainCanvas canvas, float deltaTime); // Абстрактный метод для обновления состояния спрайта, который должен быть реализован в дочерних классах.
    
    public abstract void render(MainCanvas canvas, Graphics g); // Абстрактный метод для отрисовки спрайта, который должен быть реализован в дочерних классах.
}