package com.company.biblioteka;

import java.util.regex.Pattern;

public class Patterns {

	private static Pattern pNumeric = Pattern.compile("-?\\d+(\\.\\d+)?");

	public static boolean isNumeric(String strNum) {
		return pNumeric.matcher(strNum).matches();
	}

}
