// Класс Philosopher реализует логику каждого философа
public class Philosopher implements Runnable {
    private final int id;  // Уникальный идентификатор философа
    private final Fork leftFork;  // Левая вилка
    private final Fork rightFork;  // Правая вилка

    // Конструктор, принимающий идентификатор философа и его вилки
    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;  // Устанавливаем идентификатор философа
        this.leftFork = leftFork;  // Устанавливаем левую вилку
        this.rightFork = rightFork;  // Устанавливаем правую вилку
    }

    // Метод, который будет выполнен в потоке
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {  // Каждый философ будет есть 3 раза
            try {
                think();  // Философ размышляет
                eat();    // Философ ест
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Обработка прерывания потока
            }
        }
    }

    // Метод размышлений философа
    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет.");
        Thread.sleep((int) (Math.random() * 1000));  // Случайная пауза для размышлений
    }

    // Метод еды философа
    private void eat() throws InterruptedException {
        // Блокировка на получение вилок
        if (id % 2 == 0) {  // Четные философы сначала берут левую вилку
            leftFork.pickUp();  // Философ пытается взять левую вилку
            System.out.println("Философ " + id + " взял левую вилку " + leftFork.getId());
            rightFork.pickUp();  // Затем правую вилку
            System.out.println("Философ " + id + " взял правую вилку " + rightFork.getId());
        } else {  // Нечетные философы сначала берут правую вилку
            rightFork.pickUp();  // Философ пытается взять правую вилку
            System.out.println("Философ " + id + " взял правую вилку " + rightFork.getId());
            leftFork.pickUp();  // Затем левую вилку
            System.out.println("Философ " + id + " взял левую вилку " + leftFork.getId());
        }

        // Процесс еды (снятие блокировки вне synchronized)
        System.out.println("Философ " + id + " ест.");
        Thread.sleep((int) (Math.random() * 1000));  // Случайная пауза для еды

        // Освобождаем вилки после еды
        leftFork.putDown();  // Философ освобождает левую вилку
        rightFork.putDown();  // Философ освобождает правую вилку
        System.out.println("Философ " + id + " закончил есть и освободил вилки.");
    }
}
