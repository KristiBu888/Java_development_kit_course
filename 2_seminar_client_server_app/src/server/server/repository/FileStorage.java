package server.server.repository;

import java.io.FileReader;
import java.io.FileWriter;

// Класс FileStorage реализует интерфейс Repository для работы с файлами.
public class FileStorage implements Repository<String> {

    // Константа для указания пути к файлу, в котором будут храниться данные (лог).
    private static final String LOG_PATH = "src/server/server/repository/history.txt";

    // Метод save реализует сохранение строки текста в файл.
    @Override
    public void save(String text){
        // Используем FileWriter с ресурсами (try-with-resources), чтобы записывать в файл в режиме добавления.
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text); // Записывает переданный текст в файл.
            writer.write("\n"); // Переходит на новую строку после записи текста.
        } catch (Exception e){
            e.printStackTrace(); // В случае ошибки выводит трассировку исключения в консоль.
        }
    }

    // Метод load реализует чтение содержимого файла и возвращает его в виде строки.
    @Override
    public String load(){

        // Используем StringBuilder для накопления символов, считанных из файла.
        StringBuilder stringBuilder = new StringBuilder();

        // Используем FileReader с ресурсами для чтения данных из файла.
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c; // Переменная для хранения текущего символа в формате int.

            // Читаем файл символ за символом, пока не достигнем конца файла (-1).
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c); // Преобразуем int в char и добавляем к StringBuilder.
            }

            // Удаляет последний символ.
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString(); // Возвращает содержимое файла как строку.
        } catch (Exception e){
            e.printStackTrace(); // В случае ошибки выводит трассировку исключения.
            return null; // Возвращает null, если произошло исключение.
        }
    }
}
