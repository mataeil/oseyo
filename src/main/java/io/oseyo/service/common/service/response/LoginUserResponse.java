package io.oseyo.service.common.service.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginUserResponse {
	private Boolean isUserDetail;
	private _UserDetail userDetail = new _UserDetail();

	@Getter@Setter
	public static class _UserDetail {
		private String storeTypeCode;
		private _Store store = new _Store();
		private _User user = new _User();
	}

	@Getter@Setter
	public static class _Store {
		private String name;
		private Integer jobTypeCodeListSize;
		private List<_JobTypeCode> jobTypeCodeList;
		private String address1;
		private String address2;
	}

	@Getter@Setter
	public static class _User {
		private String phoneNumber;
	}

	@Getter@Setter
	public static class _JobTypeCode {
		private String jobTypeCode;
		private String jobTypeCodeName;
	}
}
