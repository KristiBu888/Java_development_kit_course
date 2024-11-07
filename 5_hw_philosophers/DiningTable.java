// Класс DiningTable управляет взаимодействием философов и вилок
// DiningTable.java
import java.util.ArrayList;
import java.util.List;

public class DiningTable {
    public static void main(String[] args) {
        // Создаем вилки с уникальными идентификаторами
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        // Создаем список для хранения потоков философов
        List<Thread> philosophers = new ArrayList<>();
        // Создаем философов и передаем им соседние вилки
        philosophers.add(new Thread(new Philosopher(1, fork1, fork2)));  // Философ 1
        philosophers.add(new Thread(new Philosopher(2, fork2, fork3)));  // Философ 2
        philosophers.add(new Thread(new Philosopher(3, fork3, fork4)));  // Философ 3
        philosophers.add(new Thread(new Philosopher(4, fork4, fork5)));  // Философ 4
        philosophers.add(new Thread(new Philosopher(5, fork5, fork1)));  // Философ 5

        // Запускаем всех философов как потоки
        for (Thread philosopher : philosophers) {
            philosopher.start();  // Запуск потока философа
        }
    }
}
