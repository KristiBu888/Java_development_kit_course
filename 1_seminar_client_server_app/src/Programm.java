public class Programm {
    public static void main(String[] args) {

        Client client1 = new Client("Ivan", "127.0.0.1", "8989", "12345");
        Client client2 = new Client("Petr", "128.0.0.1", "9089", "123467");

        Chat chat1 = new Chat ();
        chat1.addClienttoChat(new ClientGUI(client1));
        chat1.addClienttoChat(new ClientGUI(client2));

        ServerWindow serverWindow = new ServerWindow(chat1);
}
}