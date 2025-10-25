import dataaccess.DataAccessException;
import server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try{
            server.run(8080);
            System.out.println("♕ 240 Chess Server");
        } catch (DataAccessException e) {
            System.out.println("Failed to start the database");
        }
    }
}