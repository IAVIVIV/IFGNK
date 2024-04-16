package Implement;

import java.sql.Connection;
import java.util.List;

import Domain.Message;
import Domain.Service;
import Domain._IP;
import Infrastructure.DatabaseConnector;
import Infrastructure.MessageDAO;

public class Imp_Service implements Service {

	@Override
	public List<Message> fetchData(_IP filterIP) {
		// TODO Auto-generated method stub
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = connector.getConnection();
		MessageDAO messageDAO = new MessageDAO(connection);
		List<Message> messages = messageDAO.getMessagesByIP(filterIP.getIP());
		connector.closeConnection();
		return messages;
	}

	@Override
	public void pushData(Message messagePush) {
		// TODO Auto-generated method stub
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = connector.getConnection();
		MessageDAO messageDAO = new MessageDAO(connection);
		messageDAO.saveMessage(messagePush);
		connector.closeConnection();
	}

	@Override
	public List<String> scanWifi() {
		// TODO Auto-generated method stub
		return Infrastructure.Scan.scanWifiDevicesDefault();
	}
}
