package io.oseyo.service.common.utils;

import java.util.Random;

public class AuthCodeUtils {
	private final static int mobileAuthCodeLength = 6;
	private final static Random random = new Random();

	private AuthCodeUtils() {};

	public static String getMobileAuthCode() {
		StringBuilder authCode = new StringBuilder();
		for (int i = 0; i < mobileAuthCodeLength; i++) {
			authCode.append(Math.abs(random.nextInt()%10));
		}
		return authCode.toString();
	}
}
