public class Calculator {

    // Метод для сложения двух чисел
    public static <T extends Number, U extends Number> double sum(T num1, U num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    // Метод для умножения двух чисел
    public static <T extends Number, U extends Number> double multiply(T num1, U num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    // Метод для деления двух чисел
    public static <T extends Number, U extends Number> double divide(T num1, U num2) {
        if (num2.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return num1.doubleValue() / num2.doubleValue();
    }

    // Метод для вычитания двух чисел
    public static <T extends Number, U extends Number> double subtract(T num1, U num2) {
        return num1.doubleValue() - num2.doubleValue();
    }

    public static void main(String[] args) {
        // Примеры использования
        System.out.println("Сумма: " + Calculator.sum(10, 20.5));            // 30.5
        System.out.println("Произведение: " + Calculator.multiply(10, 5.5)); // 55.0
        System.out.println("Деление: " + Calculator.divide(20, 4));          // 5.0
        System.out.println("Разность: " + Calculator.subtract(10.5, 5));     // 5.5
    }
}
