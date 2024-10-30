package server.server.ui;

import server.server.domain.ServerController;

// Интерфейс ServerView определяет методы для отображения информации о работе сервера и управления взаимодействием с контроллером сервера.
public interface ServerView {
    // Метод для отображения сообщения в пользовательском интерфейсе сервера (принимает строку message, которая содержит текст для пользователю)
    void showMessage(String message);
    // Метод принимает объект ServerController, который обеспечивает связь между пользовательским интерфейсом и логикой сервера.
    void setServerController(ServerController serverController);
}
