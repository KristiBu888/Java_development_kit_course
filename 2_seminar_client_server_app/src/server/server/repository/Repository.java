package server.server.repository;

// Интерфейс Repository обобщённого типа T, служит для работы с хранилищем данных.
public interface Repository<T> {

    // Метод save сохраняет объект типа T. Тип объекта задаётся при реализации интерфейса.
    void save(T text);

    // Метод load загружает и возвращает объект типа T из хранилища.
    T load();
}
