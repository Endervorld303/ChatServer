package Willy.Client;

import Willy.Util.ThreadReader;
import Willy.Util.Writer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ThreadReader in;
    private Writer out;
    private boolean running;
    private Thread readingThread;


    public Client(InetAddress address, int port) {
        try {
            socket = new Socket(address,port);
            in = new ThreadReader(socket);
            out = new Writer(socket);
            in.start();
            running = true;
            this.readingThread = new Thread(in);
            readingThread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Client(String address, int port) {
        try {
            socket = new Socket(address,port);
            in = new ThreadReader(socket);
            out = new Writer(socket);
            in.start();
            running = true;
            this.readingThread = new Thread(in);
            readingThread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void write(String msg) {
        try {
            out.write(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
