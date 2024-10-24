import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Map extends JPanel {
    private static final Random RANDOM = new Random(); // генерация случайных чисел
    private static final int HUMAN_DOT = 1; // 1- сходил человек
    private static final int AI_DOT = 2; // 2 - сходил AI
    private static final int EMPTY_DOT = 0; // 0 - ничего не поставил 
    // 1,2,0 для определения статуса
    private static final int PADDING = 10; // отступы внутрь от стенок ячейки, кот. использованы при рисовании 

    private int gameStateType; // статус состояния игры (в переменную передаются цифры)
    private static final int STATE_GAME = 0; // игра
    private static final int STATE_WIN_HUMAN = 1; // победил человек
    private static final int STATE_WIN_AI = 2; // победил AI
    private static final int STATE_DRAW = 3; // ничья
    // сообщения для состояний игры:
    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил компьютер!";
    private static final String MSG_DRAW = "Ничья!"; 

    private static final int MODE_HVA = 0;
    private static final int MODE_HVH = 1;

    // private void btnStartDelegate() {
    //     int gameMode;
    //     if (pvp.isSelected()) {
    //         gameMode = Map.MODE_HVA; 
    //     }else if (pvc.isSelected()) {
    //         gameMode = Map.MODE_HVH;
    //     }else {
    //         throw new RuntimeException("Unknown game mode");  
    //     }    
    
    //     int fieldSize = slideFieldSize.getValue();
    //     int winLength = slideWinLen.getValue();
    //     GameWindow.startNewGame(mode, fieldSize, fieldSize, winLength);
    //     setVisible(false);
    // }

    private int width, height, cellWidth, cellHeight; // ширина, высота, кол-во ячеек по ширине, кол-во ячеек по высоте
    private int mode, fieldSizeX, fieldSizeY, winLen; // mode пока не используется, размер ячейки по горизонтали, -||- по вертикали, необх. кол-во подряд идущих значений для победы
    private int[][] field; // в массив записываются все ходы
    private boolean gameWork; // флажок, кот обозначает что игра ещё не закончена

    Map() { // конструктор класса 
        setBackground(Color.WHITE); // цвет фона
        addMouseListener(new MouseAdapter() { // слушатель нажатий по нажатию мышки
            @Override
            public void mouseReleased(MouseEvent e) { // в "е" сохранится куда мышка щёлкнула 
                if (gameWork) { // если "флаг игра ещё не закончена"
                    update(e); // передаём "е" в update
                }
            }
        });
    }

    private void initMap() { // метод создания массива, где будут храниться крестики и нолики(кот поставили), а по умолч значение 0 - EMPTY_DOT (ничего не поставил).
        field = new int[fieldSizeY][fieldSizeX];
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen) { // метод начала игры
        this.mode = mode;
        this.fieldSizeX = sizeX;
        this.fieldSizeY = sizeY;
        this.winLen = winLen; // переменные сохраняем в поля чтобы в дальнейшем использовать

        initMap(); // создание нового поля
        gameWork = true; // флаг в значении true, т.е. игра пошла
        gameStateType = STATE_GAME; // установлено состояние - игра идёт

        repaint(); // метод отрисовки
        
    }

    private void update(MouseEvent mouseEvent) { // с помощью события, кот. получено слушателем нажатий mouseEvent, достаём координаты того куда мы нажали(Х,Y).
        int x = mouseEvent.getX() / cellWidth;
        int y = mouseEvent.getY() / cellHeight;
        if (!isValidCell(x, y) || !isEmptyCell(x, y)) { // если человек промахнулся мимо игрового поля, и или ячейка не пуста
            return; // работа метода закончится и ничего не случится
        }
        field[y][x] = HUMAN_DOT; // если проверка пройдена, то к значениям запишем ход человека, т.е. "1"
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) { // проверяем закончена ли игра с победой HUMAN_DOT
            return;
        }
        aiTurn(); // ai делает ход
        repaint(); // перерисовка с учётом нового значения в массиве
        checkEndGame(AI_DOT, STATE_WIN_AI); // проверяем закончена ли игра с победой AI_DOT
    }

    
    private void testBoard(){ // выводит состояние каждой ячейки из которых состоит массив (для дебага)
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
    }

    private boolean isValidCell(int x, int y) { // проверяет что Х и Y лежат в нужном диапазоне fieldSize (что человек щёлкнул именно в игровое поле)
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY; // значение положительное и не превышает размер игрового поля
    }

    private boolean isEmptyCell(int x, int y) { // проверяем закончена ли игра (3 в ряд)
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() { // много раз делает рандом пока случайно не попадёт в ячейку.
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y)); // когда попал в пустую ячейку,
        field[y][x] = AI_DOT; // делает в неё свой ход
        TurnAiWinCell();
        TurnHumanWinCell();

    }
    // Доработать алгоритм, чтобы он мог примитивно блокировать ходы игрока, и примитивно пытаться выиграть сам
    private boolean TurnAiWinCell() { 
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(i, j)){
                    field[i][j] = AI_DOT;
                    if (checkWin(AI_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }
    private boolean TurnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(i, j)){
                    field[i][j] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT)) return true;
                    field[i][j] = HUMAN_DOT;
                    return true;
                }
                field[i][j] = EMPTY_DOT;
            }
        }
        return false;
    }

    private boolean isMapFull() { // проверка что все ячейки не пустые
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) { // если хоть одна ячейка пустая, то
                    return false; // продолжаем играть
                }
            }
        }
        return true; // в противном случае все ячейки заполнены и пустых нет - true
    }

    private boolean checkEndGame(int dot, int gameOverType) { 
        if (checkWin(dot)) { // если победа произошла
            this.gameStateType = gameOverType; // меняем статус на gameOver
            repaint(); // делаем перерисовку
            return true; // возвращаем true как рез-т проверки конца игры
        } else if (isMapFull()) { // если поле полностью заполнено
            this.gameStateType = STATE_DRAW; // установим состояние ничья
            repaint(); // перерисовка
            return true; // true как рез-т проверки конца игры
        }
        return false; // в противн. случае игра продолжается
    }

    private boolean checkWin(int dot){
        for (int i = 0; i < fieldSizeX; i++) { // цикл по горизонтали
            for (int j = 0; j < fieldSizeY; j++) { // цикл по вертикали
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            } // проверяем есть ли в ячейках возможность для победы (координаты ячейки(i, j), куда будем шагать (горизонталь, вертикаль, диагональ), победная длинна значений, кто ходил (крестик или нолик)); если хоть один из направлений выйграл то вернем true,
        }
        return false; // если не одно из направлений не выйграло, вернём false
    }
    // принимает(координаты ячейки(i, j) по кот. был щелчёк, куда будем шагать для проверки подряд идущих значений, длинна (значения подряд) кот. нужна, число по кот видим кто именно ходил (значения: крестик или нолик))
    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot){
        int far_x = x + (len - 1) * vx;
        int far_y = y + (len - 1) * vy; // находим координаты следующей ячейки
        if (!isValidCell(far_x, far_y)){
            return false;
        } // проверили что ячейка лежит на игровом поле
        for (int i = 0; i < len; i++) { // проходим по всем ячейкам до len длинны
            if (field[y + i * vy][x + i * vx] != dot){ // если все dot (крестики или нолики) шли подряд len раз то переходим к return true, если нет то false
                return false;
            }
        }
        return true; // если ни разу из len раз на false не напоролись - то возвращаем true
    }

    @Override
    protected void paintComponent(Graphics g) { // автоматически вызывается после метода repaint(), а paintComponent предоставляет нам объект (Graphics g) на котором будем рисовать 
        super.paintComponent(g);
        if (gameWork) { // если игра ещё идёт, то
            render(g); // перерисовываем её
        }
    }

    private void render(Graphics g) { // перерисовка
        width = getWidth();
        height = getHeight(); // получааем размеры игрового поля
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY; // получаем размер одной ячейки

        g.setColor(Color.BLACK); // выбрали цвет, кот. будем рисовать
        for (int h = 0; h < fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, height);
        } // отрисовали по 3 линии по горизонтали и по вертикали

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) { // перебираем каждую ячейку массива
                if (field[y][x] == EMPTY_DOT){ // если она пустая - ничего не делаем
                    continue;
                }
                if (field[y][x] == HUMAN_DOT) { // если в ячейку ходил человек рисуем 2 линии - т.е. крестик
                    g.drawLine(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            (x + 1) * cellWidth - PADDING, (y + 1) * cellHeight - PADDING); // из левого верхнего в правый нижний.
                    g.drawLine(x * cellWidth + PADDING, (y + 1) * cellHeight - PADDING,
                            (x + 1) * cellWidth - PADDING, y * cellHeight + PADDING);
                } else if (field[y][x] == AI_DOT) { // если в ячейку сходил AI то рисуем круг
                    g.drawOval(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            cellWidth - PADDING * 2, cellHeight - PADDING * 2);
                } else {
                    throw new RuntimeException("unchecked value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (gameStateType != STATE_GAME){ // если любое сост. игры кроме STATE_GAME, то есть игра закончена, то
            showMessage(g); // показываем сообщ. о победителе
        }
    }

    private void showMessage(Graphics g) { // показ сообщения
        g.setColor(Color.DARK_GRAY); // выбрали цвет для рисования
        g.fillRect(0, getHeight() / 2, getWidth(), 70); // сделали прямоугольник с заливкой цветом
        g.setColor(Color.YELLOW); // выбрали другой цвет
        g.setFont(new Font("Times new roman", Font.BOLD, 48)); // установка шрифта
        switch (gameStateType){ // в зависимости от состояния устанвливаем сообщение о победителе
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2 + 60);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 20, getHeight() / 2 + 60);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 70, getHeight() / 2 + 60);
            default -> throw new RuntimeException("Unchecked gameOverState: " + gameStateType);
        }
        gameWork = false;
    }
    
}