package Willy.Util;

import java.io.IOException;
import java.net.Socket;

//Implementazione della Classe runnable ed estensione della classe Reader
public class ThreadReader extends Reader implements Runnable {
    protected boolean running;


    public ThreadReader(Socket socket) {
        super(socket);
        start();
    }


    public void start(){
        running = true;
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run(){

        while (running) {
            try {
                System.out.println(readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
