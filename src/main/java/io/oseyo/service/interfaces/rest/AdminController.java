package io.oseyo.service.interfaces.rest;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.oseyo.service.common.service.LoginService;
import io.oseyo.service.common.service.mapper.AccountMapper;
import io.oseyo.service.common.service.response.OseyoResponse;

@RestController
@RequestMapping("v1/admin")
public class AdminController {

	@Autowired
	LoginService loginService;

	@GetMapping("/user")
	public OseyoResponse getAllUsers() {
		return OseyoResponse.success(loginService.getAllUsers().stream()
		                    .map(AccountMapper::getLoginUserResponse)
		                    .collect(Collectors.toList()));
	}

	@DeleteMapping("/user")
	public OseyoResponse deleteAllUsers() {
		loginService.deleteAllUsers();
		return OseyoResponse.success(null);
	}
}
