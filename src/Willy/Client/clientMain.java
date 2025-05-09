package Willy.Client;

import java.util.Scanner;

public class clientMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Client client = new Client("localhost", 1111);
        while(client.isRunning()){
            client.write(sc.nextLine());
        }
    }
}
