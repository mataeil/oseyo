package io.oseyo.service.common.exception;

import lombok.Getter;

import io.oseyo.service.common.service.code.ErrorCode;
import io.oseyo.service.common.service.code.ResponseCode;

@Getter
public class ApiLoginException extends RuntimeException{
	private ResponseCode responseCode;
	private ErrorCode errorCode;

	public ApiLoginException(ErrorCode errorCode) {
		this.responseCode = ResponseCode.FAIL;
		this.errorCode = errorCode;
	}
}
