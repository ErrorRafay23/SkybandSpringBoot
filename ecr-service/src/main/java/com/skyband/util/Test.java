package com.skyband.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.skyband.base.ECRCore;
import com.skyband.impl.ECRImpl;

public class Test {

	public static void main(String[] args) {

		/*
		 * ECRCore ecr = ECRImpl.getConnectInstance(); try {
		 * 
		 * int i = ecr.doCOMConnection(0, "2400", 1, 5, 1);
		 * System.out.println("dsfsd"+i); String pay = ecr.doCOMTransaction(0, "2400",
		 * 1, 5, 1, "180321124033;12345678!", 17, ""); // String pay =
		 * ecr.doTCPIPTransaction("192.168.6.147", 16535,"180321124033;12345678!", 17,
		 * ""); System.out.println(pay); } catch(Exception e) { e.printStackTrace(); }
		 */
		/*String s =customFormat("#,##,###.###",Double.parseDouble("123456") / 100);
		String s1 = String.format("%.2f",Double.parseDouble(s));
		System.out.println(s1);*/
		
		String respDateTime = "070421080723";
		String currentTime = respDateTime.substring(6, 8) + ":" + respDateTime.substring(8,10) + ":"
				+ respDateTime.substring(10,12);
		System.out.println(currentTime);
	}

	static public void customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		System.out.println(value + "  " + pattern + "  " + output);
	}

	public static String getIndianCurrencyFormat(String amount) {
		StringBuilder stringBuilder = new StringBuilder();
		char amountArray[] = amount.toCharArray();
		int a = 0, b = 0;
		for (int i = amountArray.length - 1; i >= 0; i--) {
			if (a < 3) {
				stringBuilder.append(amountArray[i]);
				a++;
			} else if (b < 2) {
				if (b == 0) {
					stringBuilder.append(",");
					stringBuilder.append(amountArray[i]);
					b++;
				} else {
					stringBuilder.append(amountArray[i]);
					b = 0;
				}
			}
		}
		return stringBuilder.reverse().toString();
	}

}
