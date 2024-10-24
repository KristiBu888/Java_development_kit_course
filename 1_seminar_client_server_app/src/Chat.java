import java.util.ArrayList;

public class Chat {
    private ArrayList<ClientGUI> clientList = new ArrayList<>();

    public void addClienttoChat(ClientGUI client){
        clientList.add(client);
    }
    public ArrayList<ClientGUI> getClientList(){
        return clientList;
    }

}