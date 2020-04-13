package br.com.pirone.adliz.util;

import java.text.DecimalFormat;

public class NumberUtils {
	
	public static String doubleToDecimalString(Double number) {
		if (number != null) {
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			String formated = decimalFormat.format(number);
			return formated;
		} else {
			return "";
		}
		
	}

}
