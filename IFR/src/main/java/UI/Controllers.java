package UI;

public class Controllers extends Controller {
	public void updateChatArea(String text) {
		Views.displayMessage(viewController, text);
	}
}
