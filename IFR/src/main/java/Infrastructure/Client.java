package Infrastructure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Domain.Factory;
import Domain.Message;
import UI.Controllers;

public class Client {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Client(String serverIP, int port) throws IOException {
		socket = new Socket(serverIP, port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	public void sendMessage(String content, String destinationIP) throws IOException {
		Factory fMessage = new Factory();
		fMessage.messageFactory(content, destinationIP);
		out.writeObject(fMessage);
		out.flush();
	}

	public void startReceiving() {
		new Thread(() -> {
			try {
				while (true) {
					Message message = (Message) in.readObject();
					System.out.println("Received message: " + message.getContent());
					new Controllers().updateChatArea(message.getDestinationIP() + ": " + message.getContent());
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}).start();
	}
}