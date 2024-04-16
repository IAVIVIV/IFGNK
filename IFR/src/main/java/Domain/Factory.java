package Domain;

public class Factory {
	public Message messageFactory(String text, String address) {
		Message message = new Message(text, address);
		return message;
	}

	public _IP _IPFactory(String address) {
		_IP IP = new _IP(address);
		return IP;
	}
}
