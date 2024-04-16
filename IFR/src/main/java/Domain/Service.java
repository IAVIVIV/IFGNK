package Domain;

import java.util.List;

public interface Service {
	public List<Message> fetchData(_IP filterIP);

	public void pushData(Message messagePush);

	public List<String> scanWifi();
}
