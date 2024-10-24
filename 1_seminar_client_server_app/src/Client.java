public class Client {
    private String name;
    private String ipAdress;
    private String port;
    private String password;

    public Client(String name, String ipAdress, String port, String password){
        this.name = name;
        this.ipAdress = ipAdress;
        this.port = port;
        this.password = password;
    }

    public String getName(){
        return name;
    }
    public String getIpAdress(){
        return ipAdress;
    }
    public String getPort(){
        return port;
    }
    public String getPassword(){
        return password;
    }
}
