package com.skyband.util;

public class CommonUtils {

	public static String byteArrayToHexString(byte[] data) {
		StringBuilder buff = new StringBuilder();

		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				byte b = data[i];
				int j = ((char) b) & 0xFF;
				buff.append(nibbleToChar[j >>> 4]);
				buff.append(nibbleToChar[j & 0x0F]);
			}
		}

		return buff.toString();
	}// byteArrayToHexString

	static char[] nibbleToChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String hexToString(String hex) {

		StringBuilder sb = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);
		}

		return sb.toString();
	}

	private CommonUtils() {
	}

}
