package io.oseyo.service.common.cache;

import java.util.HashMap;
import java.util.Map;

import io.oseyo.service.common.service.values.AuthCodeCache;

public class LocalCache {
	private static LocalCache localCache = new LocalCache();
	private static Map<String, Map<String, Object>> cache;

	private LocalCache() {
		cache = new HashMap<>();
		//휴대전화번호인증 캐쉬
		cache.put("mobileAuthCode", new HashMap<String, Object>());
	}

	public static Map<String, Object> getMobileAuthCodeCache() {
		return cache.get("mobileAuthCode");
	}

}
