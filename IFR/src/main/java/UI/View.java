package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<String> userList;
	private DefaultListModel<String> userListModel;
	private JButton refreshButton;
	private JTextField messageField;
	private JButton sendButton;
	private JTextArea chatArea;

	public View() {
		setTitle("Simple Chat");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());

		userListModel = new DefaultListModel<>();
		userList = new JList<>(userListModel);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane userListScrollPane = new JScrollPane(userList);
		userListScrollPane.setPreferredSize(new Dimension(150, 200));
		mainPanel.add(userListScrollPane, BorderLayout.WEST);

		refreshButton = new JButton("Refresh");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(refreshButton);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		JScrollPane chatScrollPane = new JScrollPane(chatArea);
		mainPanel.add(chatScrollPane, BorderLayout.CENTER);

		JPanel messagePanel = new JPanel(new BorderLayout());
		messageField = new JTextField();
		messagePanel.add(messageField, BorderLayout.CENTER);
		sendButton = new JButton("Send");
		messagePanel.add(sendButton, BorderLayout.EAST);
		mainPanel.add(messagePanel, BorderLayout.SOUTH);

		add(mainPanel);

		setVisible(true);
	}

	public DefaultListModel<String> getUserListModel() {
		return userListModel;
	}

	public JList<String> getUserList() {
		return userList;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public JTextField getMessageField() {
		return messageField;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}
}
