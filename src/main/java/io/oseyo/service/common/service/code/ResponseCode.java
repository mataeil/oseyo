package io.oseyo.service.common.service.code;

import lombok.Getter;

@Getter
public enum ResponseCode {
	SUCCESS("SUCCESS"),
	FAIL("FAIL");

	private String id;

	ResponseCode(String id) {
		this.id = id;
	}
}
