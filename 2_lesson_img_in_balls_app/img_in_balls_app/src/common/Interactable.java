package common;

import java.awt.*;
/** 
 * Интерфейс Interactable определяет общие методы для объектов,
 * которые будут взаимодействовать с игровой областью и могут
 * обновляться и отрисовываться на экране.
 */
public interface Interactable {
    void update(MainCanvas canvas, float deltaTime); // обновляет состояние объекта каждый кадр.
    void render(MainCanvas canvas, Graphics g); // отрисовка объекта на экране каждый кадр.
}
