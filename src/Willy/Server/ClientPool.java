package Willy.Server;

import Willy.Util.MessageListener;

import java.util.ArrayList;

/*  Classe che gestisce i Pool di client.
*   Ogni Clientpool è un gruppo di client che comunicano tra di loro.
*   Il compito del server è quello che quando riceve un messaggio da un client lo spedisce agli altri client
*   Se attiva la criptografia allora decriptografa il messaggio e lo rimanda a tutti(Punto ancora da vedere)[forse E2EE]
* */
public class ClientPool extends Thread implements MessageListener {
    private ArrayList<ClientManager> clients; //Lista dei client
    private int maxClients;
    private int activeClients;
    private static int progressive = 0;
    public final int id;


    public ClientPool(int maxClients) {
        this.maxClients = maxClients;
        clients = new ArrayList<ClientManager>(maxClients);
        id = progressive++;
    }


    public boolean addClient(ClientManager client) {
        if(activeClients < maxClients) {
            activeClients++;
            client.setMessageListener(this);
            clients.add(client);
            return true;
        }else{
            return false;
        }
    }

    public void broadcastMessage(String message) {
        for(ClientManager client : clients) {
            client.write(message);
        }
    }

    public void notifyOthers(String msg, ClientManager client) {
        for(ClientManager client1 : clients) {
            if(client1 != client) { //Confronta l'indirizzo in memoria da ricordare
                client1.write(msg);
            }
        }
    }

    @Override
    public void onMessageReceived(ClientManager sender, String message) {
        if(message == null || message.trim().isEmpty()) {
            sender.stopClient();
            clients.removeIf(clientManager -> sender == clientManager);
            message = "Client uscito";
        }
        System.out.println(message);
        notifyOthers(message,sender);
    }
}