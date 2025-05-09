package Willy.Util;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWriter extends Writer  implements Runnable {

    protected boolean running;
    private Scanner scanner;

    public ThreadWriter(Socket socket) {
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
    public void run() {
        while (running) {
            try {
                write(scanner.nextLine());
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
