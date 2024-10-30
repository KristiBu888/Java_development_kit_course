package circles.exceptions;

/**
 * Класс BallsOverflowException - исключение, которое возникает
 * при превышении максимального количества спрайтов в окне приложения.
 */
public class BallsOverflowException extends RuntimeException {
    /**
     * Конструктор без параметров, использующий стандартное сообщение об ошибке.
     */
    public BallsOverflowException() {
        super("Превышено максимальное количество объектов на экране!");
    }
}

