package UI;

import javax.swing.JButton;
import javax.swing.JList;

public class Controller {
	protected View viewController;

	public Controller() {
	}

	public Controller(View view) {
		this.viewController = view;
		initView();
	}

	private void initView() {
		JButton refreButton = viewController.getRefreshButton();
		refreButton.addActionListener(e -> Actions.refreshUserListIPConnection(viewController));

		JButton sendButton = viewController.getSendButton();
		sendButton.addActionListener(e -> Actions.updateChatFrame(viewController));

		JList<String> userList = viewController.getUserList();
		userList.addListSelectionListener(Actions.selectionFromListIPConnection(viewController));
	}
}
