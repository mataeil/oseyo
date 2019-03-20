package io.oseyo.service.common.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.oseyo.service.common.domain.Account;
import io.oseyo.service.common.domain.Store;
import io.oseyo.service.common.domain.StoreJob;
import io.oseyo.service.common.domain.User;
import io.oseyo.service.common.domain.code.JobTypeCode;
import io.oseyo.service.common.domain.code.StoreTypeCode;
import io.oseyo.service.common.domain.repository.AccountRepository;
import io.oseyo.service.common.exception.ApiLoginException;
import io.oseyo.service.common.service.code.ErrorCode;
import io.oseyo.service.common.service.mapper.AccountMapper;
import io.oseyo.service.common.service.request.LoginRequestBundle;
import io.oseyo.service.common.service.request.LoginRequestBundle._JobTypeCode;
import io.oseyo.service.common.service.response.LoginAuthResponse;
import io.oseyo.service.common.service.response.LoginJobCodeResponse;
import io.oseyo.service.common.service.response.LoginJobCodeResponse._JobCode;
import io.oseyo.service.common.service.response.LoginUserResponse;
import io.oseyo.service.common.utils.AuthCodeUtils;
import io.oseyo.service.common.utils.ValidationUtils;

@Service
@Slf4j
public class LoginService {

	public static final int TOKEN_VALID_DAYS = 365;
	public static final int NICKNAME_LENGTH_LIMIT = 12;
	public static final int STORENAME_LENGTH_LIMIT = 14;

	@Autowired
	MobileMessageService mobileMessageService;

	@Autowired
	CacheService cacheService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TokenService tokenService;

	@Value("${oseyo.login.authCode:@null}")
	String defaultAuthCode;

	public void sendMobileAuthCode(LoginRequestBundle loginRequestBundle) {
		if(!isValidPhoneNumber(loginRequestBundle)){
			throw new ApiLoginException(ErrorCode.ERR_LGN_009);
		}

		String phoneNumber = loginRequestBundle.getPhoneNumber();
		String authCode = AuthCodeUtils.getMobileAuthCode();
		if (!defaultAuthCode.equals("@null")) {
			authCode = defaultAuthCode;
		}
		mobileMessageService.sendMobileAuthCode(phoneNumber, authCode);
		cacheService.putMobileAuthCode(phoneNumber, authCode);
	}

	@Transactional
	public LoginAuthResponse getToken(LoginRequestBundle loginRequestBundle) {
		if (!isValidPhoneNumber(loginRequestBundle)) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_009);
		}

		LoginAuthResponse response = new LoginAuthResponse();
		response.setIsUserDetail(false);

		Optional<String> authCode = cacheService.getMobileAuthCode(loginRequestBundle.getPhoneNumber());

		if (!authCode.isPresent()) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_007);
		} else if (!authCode.get().equals(loginRequestBundle.getAuthCode())) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_008);
		}

		String token = tokenService.getNewToken();
		response.setToken(token);

		Optional<Account> account = accountRepository.findByPhoneNumber(loginRequestBundle.getPhoneNumber());
		if (account.isPresent()) {
			if (account.get().getUser() != null) {
				response.setIsUserDetail(true);
			}
			account.get().setToken(token);
			accountRepository.save(account.get());
		} else {
			Account newAccount = new Account();
			newAccount.setPhoneNumber(loginRequestBundle.getPhoneNumber());
			newAccount.setToken(token);
			newAccount.setValidDatetime(LocalDateTime.now().plusDays(TOKEN_VALID_DAYS));
			accountRepository.save(newAccount);
		}

		return response;
	}

	private static Boolean isValidPhoneNumber(LoginRequestBundle loginRequestBundle) {
		return loginRequestBundle.getPhoneNumber() != null && ValidationUtils.isValidPhoneNumber(loginRequestBundle.getPhoneNumber());
	}

	public LoginUserResponse getUser(Account account) {
		return AccountMapper.getLoginUserResponse(account);
	}

	@Transactional
	public void checkNickName(String nickName) {
		if(StringUtils.isEmpty(nickName) || nickName.length() > NICKNAME_LENGTH_LIMIT){
			throw new ApiLoginException(ErrorCode.ERR_LGN_014);
		}
		if(accountRepository.findByUserNickName(nickName).isPresent()){
			throw new ApiLoginException(ErrorCode.ERR_LGN_002);
		}
	}

	public LoginJobCodeResponse getJobCodeList(String storeTypeCode) {
		if (StoreTypeCode.getValueById(storeTypeCode) == null) {
			throw new ApiLoginException(ErrorCode.ERR_CMM_002);
		}

		LoginJobCodeResponse response = new LoginJobCodeResponse();
		response.setStoreTypeCode(storeTypeCode);

		List<JobTypeCode> jobTypeCodeList = JobTypeCode.getValuesByStoreTypeCode(StoreTypeCode.getValueById(storeTypeCode));
		response.setListSize(jobTypeCodeList.size());

		for (int i = 0; i < jobTypeCodeList.size(); i++) {
			_JobCode jobCode = new _JobCode();
			jobCode.setCode(jobTypeCodeList.get(i).getId());
			jobCode.setName(jobTypeCodeList.get(i).getName());
			jobCode.setOrder(i);
			response.getJobTypeCodeList().add(jobCode);
		}

		return response;
	}

	@Transactional
	public void createAccount(Account account, LoginRequestBundle loginRequestBundle) {
		//닉네임 체크
		checkNickName(loginRequestBundle.getNickName());
		//상호명 체크
		if (StringUtils.isEmpty(loginRequestBundle.getStoreName()) || loginRequestBundle.getStoreName().length() > STORENAME_LENGTH_LIMIT){
			throw new ApiLoginException(ErrorCode.ERR_LGN_003);
		}
		//상점타입 체크
		if (StringUtils.isEmpty(loginRequestBundle.getStoreTypeCode()) || StoreTypeCode.getValueById(loginRequestBundle.getStoreTypeCode()) == null) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_012);
		}
		//주소체크
		if (StringUtils.isEmpty(loginRequestBundle.getAddress1())) {throw new ApiLoginException(ErrorCode.ERR_LGN_005);}
		//업종체크
		if (loginRequestBundle.getJobTypeCodeList()==null || loginRequestBundle.getJobTypeCodeList().size() < 1 || loginRequestBundle.getJobTypeCodeList().size() > 3) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_004);
		}
		List<JobTypeCode> valuesByStoreTypeCode = JobTypeCode.getValuesByStoreTypeCode(StoreTypeCode.getValueById(loginRequestBundle.getStoreTypeCode()));
		for (_JobTypeCode _jobTypeCode : loginRequestBundle.getJobTypeCodeList()) {
			if(JobTypeCode.getValueById(_jobTypeCode.getJobTypeCode()) == null){
				throw new ApiLoginException(ErrorCode.ERR_LGN_004);
			}
			for (JobTypeCode jobTypeCode : valuesByStoreTypeCode) {
				if(JobTypeCode.getValueById(_jobTypeCode.getJobTypeCode()).equals(jobTypeCode))
					continue;
			}
			Optional<JobTypeCode> any = valuesByStoreTypeCode.stream().filter(JobTypeCode.getValueById(_jobTypeCode.getJobTypeCode())::equals).findAny();
			if(!any.isPresent()) throw new ApiLoginException(ErrorCode.ERR_LGN_004);
		}
		//GPS좌표 체크
		if (StringUtils.isEmpty(loginRequestBundle.getLatitude()) || StringUtils.isEmpty(loginRequestBundle.getLongitude())) {
			throw new ApiLoginException(ErrorCode.ERR_LGN_011);
		}
		//보건증 체크
		if(!StringUtils.isEmpty(loginRequestBundle.getHygieneRegisteredDate())){
			try {
				LocalDate hygiene = LocalDate.parse(loginRequestBundle.getHygieneRegisteredDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
				if (hygiene.isAfter(LocalDate.now())) {
					throw new ApiLoginException(ErrorCode.ERR_LGN_013);
				}
			} catch (Exception e) {
				throw new ApiLoginException(ErrorCode.ERR_LGN_013);
			}
		}

		Store store = new Store();
		store.setName(loginRequestBundle.getStoreName());
		store.setType(StoreTypeCode.getValueById(loginRequestBundle.getStoreTypeCode()));
		store.setAddress1(loginRequestBundle.getAddress1());
		store.setAddress2(loginRequestBundle.getAddress2());
		for (_JobTypeCode jobTypeCode : loginRequestBundle.getJobTypeCodeList()) {
			StoreJob storeJob = new StoreJob();
			storeJob.setStore(store);
			storeJob.setJobTypeCode(JobTypeCode.getValueById(jobTypeCode.getJobTypeCode()));
			store.getJobs().add(storeJob);
		}
		store.setLatitude(loginRequestBundle.getLatitude());
		store.setLongitude(loginRequestBundle.getLongitude());

		User user = new User();
		user.setNickName(loginRequestBundle.getNickName());
		user.setHygieneRegisteredDate(loginRequestBundle.getHygieneRegisteredDate());
		user.setStore(store);

		account.setUser(user);

		accountRepository.save(account);
	}

	@Transactional
	public void deleteToken(Account account) {
		account.setToken(null);
		accountRepository.save(account);
	}

	@Transactional
	public void deleteUser(Account account) {
		accountRepository.delete(account);
	}

	@Transactional
	public List<Account> getAllUsers() {
		return accountRepository.findAll();
	}

	@Transactional
	public void deleteAllUsers() {
		accountRepository.deleteAll();
	}
}
