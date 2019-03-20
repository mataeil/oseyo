package io.oseyo.service.interfaces.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.oseyo.service.common.exception.ApiLoginException;
import io.oseyo.service.common.service.LoginService;
import io.oseyo.service.common.service.TokenService;
import io.oseyo.service.common.service.request.LoginRequestBundle;
import io.oseyo.service.common.service.response.OseyoResponse;

@RestController
@RequestMapping("v1/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	TokenService tokenService;

	@PostMapping("/auth/phoneNumber")
	public OseyoResponse sendMobileAuthCode(@RequestBody LoginRequestBundle loginRequestBundle) {
		loginService.sendMobileAuthCode(loginRequestBundle);
		return OseyoResponse.success(null);
	}

	@PostMapping("/auth")
	public OseyoResponse getOToken(@RequestBody LoginRequestBundle loginRequestBundle) {
		return OseyoResponse.success(loginService.getToken(loginRequestBundle));
	}

	/**
	 * 로그아웃 - 토큰삭제
	 * @return
	 */
	@DeleteMapping("/auth")
	public OseyoResponse deleteOToken(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("o-token");
		loginService.deleteToken(tokenService.getAccountByToken(token));
		return OseyoResponse.success(null);
	}

	@GetMapping("/user")
	public OseyoResponse getUser(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("o-token");
		return OseyoResponse.success(loginService.getUser(tokenService.getAccountByToken(token)));
	}

	@DeleteMapping("/user")
	public OseyoResponse deleteUser(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("o-token");
		loginService.deleteUser(tokenService.getAccountByToken(token));
		return OseyoResponse.success(null);
	}

	@GetMapping("")
	public OseyoResponse checkNickName(HttpServletRequest httpServletRequest, @RequestParam(value="nickName", defaultValue="") String nickName) {
		String token = httpServletRequest.getHeader("o-token");
		tokenService.getAccountByToken(token);
		loginService.checkNickName(nickName);
		return OseyoResponse.success(null);
	}

	@GetMapping("/jobTypeCode")
	public OseyoResponse getJobCodeList(HttpServletRequest httpServletRequest, @RequestParam(value = "storeTypeCode") String storeTypeCode) {
		String token = httpServletRequest.getHeader("o-token");
		tokenService.getAccountByToken(token);
		return OseyoResponse.success(loginService.getJobCodeList(storeTypeCode));
	}

	@PostMapping("/account")
	public OseyoResponse createAccount(HttpServletRequest httpServletRequest, @RequestBody LoginRequestBundle loginRequestBundle) {
		String token = httpServletRequest.getHeader("o-token");
		loginService.createAccount(tokenService.getAccountByToken(token), loginRequestBundle);
		return OseyoResponse.success(null);
	}

	@ExceptionHandler(ApiLoginException.class)
	public OseyoResponse oseyoApiError(ApiLoginException ex) {
		return new OseyoResponse(ex.getResponseCode().getId(), ex.getErrorCode().getId(), ex.getErrorCode().getMessage(), null);
	}

}
