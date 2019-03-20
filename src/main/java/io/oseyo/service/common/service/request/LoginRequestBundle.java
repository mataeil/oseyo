package io.oseyo.service.common.service.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginRequestBundle {
	private String phoneNumber;
	private String authCode;
	private String nickName;
	private String storeTypeCode;
	private String storeName;
	private List<_JobTypeCode> jobTypeCodeList;
	private String address1;
	private String address2;
	private String latitude;
	private String longitude;
	private String hygieneRegisteredDate;

	@Getter@Setter
	public static class _JobTypeCode {
		private String jobTypeCode;
	}
}
