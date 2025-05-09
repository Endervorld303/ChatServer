package Willy.Server;

import Willy.Util.MessageListener;
import Willy.Util.Reader;
import Willy.Util.Writer;

import java.io.IOException;
import java.net.Socket;

public class ClientManager extends Thread{
    protected Socket socket;//Socket del client
    protected Reader reader;
    protected Writer writer;
    boolean running;
    private MessageListener listener;

    public ClientManager(Socket socket) {
        this.socket = socket;
        reader = new Reader(socket);
        writer = new Writer(socket);
        running = true;
    }

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }

    public void receiveMessage(String message) {
        // Notifica il ClientsPool
        if (listener != null) {
            listener.onMessageReceived(this, message);
        }
    }


    public void run() {
        while(running){
            try {
                receiveMessage(reader.readLine());
            } catch (IOException e) {
                receiveMessage(null);
                System.out.println("Client disconnesso");
                running = false;
            }
        }
    }

    public void write(String msg){
        try {
            writer.write(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopClient(){
        running = false;
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Client gia disconnesso");
        }

    }
}
