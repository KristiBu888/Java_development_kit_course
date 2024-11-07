// Класс Fork представляет собой вилку
public class Fork {
    private final int id;  // Уникальный идентификатор вилки
    private boolean isTaken = false;  // Состояние вилки: занята или свободна

    // Конструктор, который принимает идентификатор вилки
    public Fork(int id) {
        this.id = id;  // Устанавливаем идентификатор вилки
    }

    // Метод для получения идентификатора вилки
    public int getId() {
        return id;
    }

    // Метод для захвата вилки
    public synchronized void pickUp() throws InterruptedException {
        // Пока вилка занята, ждем
        while (isTaken) {
            wait();  // Ожидаем, пока вилка не будет свободна
        }
        // Захватываем вилку
        isTaken = true;  // Устанавливаем состояние вилки как занятое
    }

    // Метод для освобождения вилки
    public synchronized void putDown() {
        // Освобождаем вилку
        isTaken = false;  // Устанавливаем состояние вилки как свободное
        notifyAll();  // Уведомляем других философов, что вилка освободилась
    }
}
