package Willy.Client;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Client client = new Client("localhost", 1111);
        while(client.isRunning()){
            client.write(sc.nextLine());
        }
    }
}
