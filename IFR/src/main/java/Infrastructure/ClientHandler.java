package Infrastructure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Domain.Message;

public class ClientHandler extends Thread {
	private Socket clientSocket;
	@SuppressWarnings("unused")
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		System.out.println("Client connected from: " + socket.getInetAddress());
	}

	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());

			while (true) {
				Message message = (Message) in.readObject();
				System.out.println(
						"Received message from " + clientSocket.getInetAddress() + ": " + message.getContent());
				sendMessage(message);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMessage(Message message) throws IOException {
		try (Socket destinationSocket = new Socket(message.getDestinationIP(), 12345);
				ObjectOutputStream destOut = new ObjectOutputStream(destinationSocket.getOutputStream())) {
			destOut.writeObject(message);
			destOut.flush();
			System.out.println("Sent message to: " + message.getDestinationIP());
		}
	}
}