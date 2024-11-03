public class Main {
    public static void main(String[] args) {
        Integer[] intArray1 = {1, 2, 3, 4, 5};
        Integer[] intArray2 = {1, 2, 3, 4, 5};
        boolean result1 = compareArrays(intArray1, intArray2);
        System.out.println("Результат для целочисленных массивов: " + result1);

        String[] stringArray1 = {"Hello", "World"};
        String[] stringArray2 = {"Hello", "World"};
        boolean result2 = compareArrays(stringArray1, stringArray2);
        System.out.println("Результат для строковых массивов: " + result2);

        Double[] doubleArray1 = {1.5, 2.4, 3.7, 4.9, 5.5};
        Double[] doubleArray2 = {1.5, 2.4, 3.7};
        boolean result3 = compareArrays(doubleArray1, doubleArray2);
        System.out.println("Результат если массивы разной длины: " + result3);

        Float[] floatArray1 = {1.5f, 2.4f, 3.7f, 4.9f, 5.5f};
        Float[] floatArray2 = {1.5f, 2.4f, 3.7f, 4.9f, 3.3f};
        boolean result4 = compareArrays(floatArray1, floatArray2);
        System.out.println("Результат если массивы не одинаковые: " + result4);
    }

    // Обобщенный метод для сравнения двух массивов любого типа
    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        // Проверка, что массивы имеют одинаковую длину
        if (array1.length != array2.length) {
            return false; // Если длины не совпадают, возвращаем false
        }

        // Сравнение элементов массивов по индексам
        for (int i = 0; i < array1.length; i++) {
            // Проверяем, равны ли элементы на одинаковых индексах
            if (!array1[i].equals(array2[i])) {
                return false; // Если хоть один элемент не совпадает, возвращаем false
            }
        }

        // Если все элементы совпадают, возвращаем true
        return true;
    }
}
