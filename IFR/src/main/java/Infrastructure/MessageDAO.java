package Infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Factory;
import Domain.Message;

public class MessageDAO {
	private Connection connection;

	public MessageDAO(Connection connection) {
		this.connection = connection;
	}

	public void saveMessage(Message message) {
		String sql = "INSERT INTO messages (content, destinationIP) VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, message.getContent());
			statement.setString(2, message.getDestinationIP());
			statement.executeUpdate();
			System.out.println("Message saved successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Message> getMessagesByIP(String ip) {
		List<Message> messages = new ArrayList<>();
		String sql = "SELECT * FROM messages WHERE destinationIP = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, ip);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String content = resultSet.getString("content");
				String destinationIP = resultSet.getString("destinationIP");
				Factory fMessage = new Factory();
				Message message = fMessage.messageFactory(content, destinationIP);
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
}
