package Willy.Util;

import Willy.Server.ClientManager;

public interface MessageListener {
    void onMessageReceived(ClientManager sender, String message);
}
