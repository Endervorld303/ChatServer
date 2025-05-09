package Willy.Server;

import java.io.IOException;

public class serverMain {
    public static void main(String[] args) throws IOException {
        Server server = new Server(1111);
        server.run();
    }
}
