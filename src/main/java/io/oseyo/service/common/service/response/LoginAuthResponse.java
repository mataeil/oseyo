package io.oseyo.service.common.service.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginAuthResponse {
	private String token;
	private Boolean isUserDetail;
}
