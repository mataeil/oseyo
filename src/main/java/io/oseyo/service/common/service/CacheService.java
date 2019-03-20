package io.oseyo.service.common.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.oseyo.service.common.cache.LocalCache;
import io.oseyo.service.common.exception.ApiLoginException;
import io.oseyo.service.common.service.code.ErrorCode;
import io.oseyo.service.common.service.values.AuthCodeCache;

@Service
public class CacheService {

	public static final long AUTHCODE_REQUEST_TIMEOUT_SECONDS = 180L;

	public Optional<String> getMobileAuthCode(String phoneNumber) {
		if (!LocalCache.getMobileAuthCodeCache().containsKey(phoneNumber)) {
			return Optional.empty();
		}

		AuthCodeCache authCodeCache = (AuthCodeCache)LocalCache.getMobileAuthCodeCache().get(phoneNumber);
		LocalCache.getMobileAuthCodeCache().remove(phoneNumber);

		if (authCodeCache.getCreatedAt().plusSeconds(AUTHCODE_REQUEST_TIMEOUT_SECONDS).isBefore(LocalDateTime.now())) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_010);
		}

		return Optional.of(authCodeCache.getAuthCode());
	}

	public void putMobileAuthCode(String phoneNumber, String authCode) {
		AuthCodeCache authCodeCache = new AuthCodeCache();
		authCodeCache.setAuthCode(authCode);
		authCodeCache.setCreatedAt(LocalDateTime.now());
		LocalCache.getMobileAuthCodeCache().put(phoneNumber, authCodeCache);
	}
}
