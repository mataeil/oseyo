package io.oseyo.service.common.domain.code;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StoreTypeCode {

	ST01("ST01", "사장님", null),
	ST02("ST02", "전문업체", null),
	ST03("ST03", "예비창업자", null);

	private String id;
	private String name;
	private String description;

	StoreTypeCode(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public static StoreTypeCode getValueById(String id) {
		for (StoreTypeCode storeTypeCode : StoreTypeCode.values()) {
			if (storeTypeCode.getId().equals(id)) {
				return storeTypeCode;
			}
		}
		return null;
	}
}
