package io.oseyo.service.common.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.oseyo.service.common.domain.Account;
import io.oseyo.service.common.domain.repository.AccountRepository;
import io.oseyo.service.common.exception.ApiLoginException;
import io.oseyo.service.common.service.code.ErrorCode;
import io.oseyo.service.common.utils.TokenUtils;

@Service
public class TokenService {

	@Autowired
	AccountRepository accountRepository;

	public String getNewToken() {
		return TokenUtils.makeNewToken();
	}

	public Account getAccountByToken(String token) {
		Optional<Account> acccount = accountRepository.findByToken(token);
		if(!acccount.isPresent() || LocalDateTime.now().isAfter(acccount.get().getValidDatetime())){
			throw new ApiLoginException(ErrorCode.ERR_LGN_001);
		}
		return acccount.get();
	}

}
