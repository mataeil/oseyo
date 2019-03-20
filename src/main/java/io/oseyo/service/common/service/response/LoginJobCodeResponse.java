package io.oseyo.service.common.service.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginJobCodeResponse {
	private String storeTypeCode;
	private Integer listSize;
	private List<_JobCode> jobTypeCodeList = new ArrayList<>();

	@Getter @Setter
	public static class _JobCode {
		private String code;
		private String name;
		private Integer order;
	}

}
