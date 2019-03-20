package io.oseyo.service.common.service.values;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AuthCodeCache {
	private String authCode;
	private LocalDateTime createdAt;

}
