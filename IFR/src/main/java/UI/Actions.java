package UI;

import javax.swing.event.ListSelectionListener;

public class Actions {
	public static void refreshUserListIPConnection(View viewAction) {
		Views.refreshUserList(viewAction);
	}

	public static void updateChatFrame(View viewAction) {
		Views.updateChatArea(viewAction);
	}

	public static ListSelectionListener selectionFromListIPConnection(View viewAction) {
		return Views.listSelection(viewAction);
	}
}
