package io.oseyo.service.common.utils;


import java.util.regex.Pattern;

public class ValidationUtils {
	private static final Pattern PHONE_NUMBER = Pattern.compile("^010[1-9]\\d{2,3}\\d{4}");
	private static final int PHONE_NUMBER_MAX_LENGTH = 11;

	public static Boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber.length() > PHONE_NUMBER_MAX_LENGTH) { return false; }
		return PHONE_NUMBER.matcher(phoneNumber).matches();
	}

}
