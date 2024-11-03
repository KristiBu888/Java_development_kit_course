public class Pair<T, U> {
    private final T first;
    private final U second;

    // Конструктор для инициализации пары значений
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Метод для получения первого значения
    public T getFirst() {
        return first;
    }

    // Метод для получения второго значения
    public U getSecond() {
        return second;
    }

    // Переопределение метода toString() для представления пары в виде строки
    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }
}
