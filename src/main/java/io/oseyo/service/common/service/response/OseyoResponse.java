package io.oseyo.service.common.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import io.oseyo.service.common.service.code.ResponseCode;

@Getter@Setter
public class OseyoResponse {
	private String responseCode;
	private String errorCode;
	private String errorMessage;
	private Object data;

	public OseyoResponse(String responseCode, String errorCode, String errorMessage, Object data) {
		this.responseCode = responseCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public static OseyoResponse success(Object data) {
		return new OseyoResponse(ResponseCode.SUCCESS.getId(), "", "", data);
	}
}
