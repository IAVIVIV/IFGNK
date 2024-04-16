package Infrastructure;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

public class Scan {
	public static List<String> scanWifiDevicesDefault() {
		String defaultWifiNetwork = "192.168.1.0/24";
		int defaultMaxDevices = 5;
		return scanWifiDevicesInRange(defaultWifiNetwork, defaultMaxDevices);
	}

	public static List<String> scanWifiDevicesInRange(String wifiNetwork, int maxDevices) {
		SubnetUtils subnetUtils = new SubnetUtils(wifiNetwork);
		String[] allIps = subnetUtils.getInfo().getAllAddresses();
		List<String> connectedIps = new ArrayList<>();
		for (int i = 0; i < maxDevices && i < allIps.length; i++) {
			String ip = allIps[i];
			try {
				InetAddress address = InetAddress.getByName(ip);
				if (address.isReachable(1000)) {
					connectedIps.add(ip);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connectedIps;
	}
}
