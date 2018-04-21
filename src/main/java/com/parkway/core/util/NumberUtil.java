package com.parkway.core.util;

public class NumberUtil {
	
	public static String unformatAmount(String amount) {

		if (amount != null) {
			amount = amount.replace(",", "");
		}

		return amount;
	}
	
	

}
