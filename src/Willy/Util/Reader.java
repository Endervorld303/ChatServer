package Willy.Util;

import java.io.*;
import java.net.Socket;

//Classe Reader
public class Reader{

    //Attributi
    protected BufferedReader in;

    public Reader(Socket s){
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
    }
}
