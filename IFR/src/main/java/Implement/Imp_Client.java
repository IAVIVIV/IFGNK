package Implement;

import java.io.IOException;

import Infrastructure.Client;

public class Imp_Client {
	public static Client startClient(String IPServer, int portServer) throws IOException {
		Client client = new Client(IPServer, portServer);
		return client;
	}

	public static void sendMessageToDestinationIP(Client client, String content, String destinationIP)
			throws IOException {
		client.sendMessage(content, destinationIP);
	}

	public static void waitMessage(Client client) {
		client.startReceiving();
	}
}
