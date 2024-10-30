package server.server.domain;

import java.util.ArrayList;
import java.util.List;
import server.client.domain.ClientController;
import server.server.repository.Repository;
import server.server.ui.ServerView;

// Главный класс ServerController отвечает за управление работой сервера, взаимодействие с клиентами и хранение данных.
public class ServerController {
    private boolean work; // Флаг, указывающий, запущен ли сервер.
    private ServerView serverView; // Объект интерфейса для отображения сообщений сервера.
    private List<ClientController> clientControllerList; // Список подключенных клиентов.
    private Repository<String> repository; // Хранилище для записи и чтения истории сообщений.

    // Конструктор ServerController принимает вид интерфейса сервера и репозиторий для истории.
    public ServerController(ServerView serverView, Repository<String> repository) {
        this.serverView = serverView; // Инициализация объекта интерфейса.
        this.repository = repository; // Инициализация репозитория для хранения сообщений.
        clientControllerList = new ArrayList<>(); // Создание списка для отслеживания подключенных клиентов.
        serverView.setServerController(this); // Установка связи между ServerView и ServerController.
    }

    // Метод запуска сервера.
    public void start(){
        if (work){ // Проверка, если сервер уже работает 
            showOnWindow("Сервер уже был запущен"); // сообщение - сервер уже работает.
        } else {
            work = true; // то выставляем флаг работы сервера true.
            showOnWindow("Сервер запущен!"); // Сообщение об успешном запуске сервера.
        }
    }

    // Метод остановки сервера.
    public void stop(){
        if (!work){ // Проверка, работает ли сервер.
            showOnWindow("Сервер уже был остановлен"); // Сообщение, если сервер уже был остановлен.
        } else {
            work = false; // Установка флага работы сервера в false.
            // Отключение всех клиентов при остановке сервера.
            while (!clientControllerList.isEmpty()){
                disconnectUser(clientControllerList.get(clientControllerList.size() - 1));
            }
            showOnWindow("Сервер остановлен!"); // Сообщение об успешной остановке сервера.
        }
    }

    // Метод для отключения конкретного пользователя от сервера.
    public void disconnectUser(ClientController clientController){
        clientControllerList.remove(clientController); // Удаление клиента из списка подключенных клиентов.
        if (clientController != null){ // Проверка, что объект клиента существует.
            clientController.disconnectedFromServer(); // Сообщение клиенту о его отключении от сервера.
            showOnWindow(clientController.getName() + " отключился от беседы"); // сообщение об отключении клиента.
        }
    }

    // Метод для подключения нового клиента.
    public boolean connectUser(ClientController clientController){
        if (!work){ // Если сервер не запущен, подключение не выполняется.
            return false; // Возврат значения false при неудачном подключении.
        }
        clientControllerList.add(clientController); // Добавление клиента в список подключенных.
        showOnWindow(clientController.getName() + " подключился к беседе"); // сообщения о подключении клиента.
        return true; // Возврат значения true при успешном подключении.
    }

    // Метод для обработки нового сообщения.
    public void message(String text){
        if (!work){ // Проверка, работает ли сервер.
            return; // Прекращение обработки сообщения, если сервер не работает.
        }
        showOnWindow(text); // Отображение сообщения в интерфейсе сервера.
        answerAll(text); // Отправка сообщения всем подключенным клиентам.
        saveInHistory(text); // Сохранение сообщения в истории.
    }

    // Метод для загрузки истории сообщений.
    public String getHistory() {
        return repository.load(); // Загрузка сохраненной истории из репозитория.
    }

    // Вспомогательный метод для отправки сообщения всем клиентам.
    private void answerAll(String text){
        for (ClientController clientController : clientControllerList){ // Перебор всех клиентов.
            clientController.answerFromServer(text); // Отправка сообщения от сервера каждому клиенту.
        }
    }

    // Вспомогательный метод для отображения сообщения в интерфейсе.
    private void showOnWindow(String text){
        serverView.showMessage(text + "\n");
    }

    // Вспомогательный метод для сохранения сообщения в истории.
    private void saveInHistory(String text){
        repository.save(text); // Сохранение текста сообщения в репозиторий.
    }
}
