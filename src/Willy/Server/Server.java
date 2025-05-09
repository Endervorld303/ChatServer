package Willy.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static final int PORT = 8888;

    List<ClientPool> clientPools;
    ServerSocket serverSocket;
    InetAddress ip;
    int port;
    boolean running;

    public Server() {
        port = PORT;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clientPools = new ArrayList<ClientPool>();
        running = true;
    }

    public Server(int port) {
        try{
            this.port = port;
            serverSocket = new ServerSocket(this.port);
            clientPools = new ArrayList<ClientPool>();
            running = true;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public void run() throws IOException {
        createPool(2);
        while(running){
            Socket socket = serverSocket.accept();
            System.out.println("Connesso: " + socket.getInetAddress().getHostAddress());
            ClientManager client = new ClientManager(socket);
            client.start();
            client.write("Reindirizzato al pool 1");
            clientPools.get(0).addClient(client);
        }
    }




    //Metodo che crea un nuovo pool di client per metterli in comunicazione specificando il massimo di client
    public void createPool(int max){
        clientPools.add(new ClientPool(max));
    }

    /* Metodo inutile va riscritto
    public void run() throws IOException {
        while(running){
            //Procedura di acccettazzione del client al serverSocket
            Socket socket = serverSocket.accept();
            System.out.println("Connesso: " + socket.getInetAddress().getHostAddress());
            ClientManager client = new ClientManager(socket);
            client.write("Welcome to " + ip + ":" + port);
            client.start(); //Fa partire il Thread
            clients.add(client);    //Aggiunge il client alla lista dei client
        }
    }*/
}