package Domain;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String content;
	private String destinationIP;

	public Message(String content, String destinationIP) {
		super();
		this.content = content;
		this.destinationIP = destinationIP;
	}

	public String getContent() {
		return content;
	}

	public String getDestinationIP() {
		return destinationIP;
	}
}