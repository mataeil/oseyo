package io.oseyo.service.common.utils;

import java.util.Random;

public class TokenUtils {

	private final static int tokenLength = 28;
	private final static int tokenInterval = 7;
	private final static Random random = new Random();

	private TokenUtils() {

	}

	public static String makeNewToken() {
		StringBuilder token = new StringBuilder();
		for (int i = 1; i <= tokenLength; i++) {
			token.append(Math.abs(random.nextInt() % 10));
			if (i % tokenInterval == 0 && i < tokenLength) {
				token.append('-');
			}
		}

		return token.toString();
	}

}
