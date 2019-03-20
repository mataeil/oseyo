package io.oseyo.service.common.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.oseyo.service.common.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByPhoneNumber(String phoneNumber);

	Optional<Account> findByToken(String token);

	Optional<Account>  findByUserNickName(String nickName);
}
