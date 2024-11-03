public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(1, "Hello");
        
        System.out.println("First value: " + pair.getFirst()); // Вывод: 1
        System.out.println("Second value: " + pair.getSecond()); // Вывод: Hello
        System.out.println(pair); // Вывод: Pair{first=1, second=Hello}
    }
}

