package UI;

import java.io.IOException;
import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Domain.Factory;
import Domain.Message;
import Domain.Service;
import Domain._IP;
import Implement.CloneServer;
import Implement.Imp_Client;
import Implement.Imp_Service;
import Infrastructure.Client;

public class Views {
	public static String valueMessage;

	public static void refreshUserList(View view) {
		view.getUserListModel().clear();
		Service service = new Imp_Service();
		List<String> listIP = service.scanWifi();
		for (String ip : listIP) {
			view.getUserListModel().addElement(ip);
		}
	}

	public static void displayMessage(View view, String message) {
		view.getChatArea().append(message + "\n");
		view.getChatArea().setCaretPosition(view.getChatArea().getDocument().getLength());
	}

	public static void updateChatArea(View view) {
		new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					valueMessage = view.getMessageField().getText();
					if (valueMessage != null && !valueMessage.trim().isEmpty()) {
						displayMessage(view, valueMessage);
						view.getMessageField().setText("");
						JList<String> list = view.getUserList();
						String selectedIP = list.getSelectedValue();
						try {
							Client client = Imp_Client.startClient(CloneServer.IPServer, CloneServer.portServer);
							Imp_Client.sendMessageToDestinationIP(client, valueMessage, selectedIP);
							Imp_Client.waitMessage(client);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Factory fMessage = new Factory();
						Message message = fMessage.messageFactory(valueMessage, selectedIP);
						Service service = new Imp_Service();
						service.pushData(message);
					}
				}
			}
		};
	}

	public static ListSelectionListener listSelection(View view) {
		return new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					JList<String> list = view.getUserList();
					String selectedIP = list.getSelectedValue();
					Factory fIP = new Factory();
					_IP IP = fIP._IPFactory(selectedIP);
					Service service = new Imp_Service();
					List<Message> lMessage = service.fetchData(IP);
					for (Message message : lMessage) {
						displayMessage(view, message.getContent());
					}
					view.getSendButton().setEnabled(view.getUserList().getSelectedValue() != null);
					view.getChatArea().setText("");
				}
			}
		};
	}
}
