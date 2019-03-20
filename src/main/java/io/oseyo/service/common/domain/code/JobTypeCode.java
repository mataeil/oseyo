package io.oseyo.service.common.domain.code;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum JobTypeCode {

	JB01("JB01", "한식점업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB02("JB02", "중국집음식점업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB03("JB03", "일본음식점업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB04("JB04", "서양음식점업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB05("JB05", "음식출장조달업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB06("JB06", "프랜차이즈 (체인화음식점업)", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB07("JB07", "분식점, 간이음식점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB08("JB08", "구내식당", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB09("JB09", "제과점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB10("JB10", "호프전문점, 소주방", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB11("JB11", "기타서양식주점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB12("JB12", "기타주점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB13("JB13", "다방, 찻집", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB14("JB14", "고속도로휴게소, 간이양식", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB15("JB15", "호텔업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB16("JB16", "여관업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB17("JB17", "청소년수련시설운영업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB18("JB18", "콘도미니업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB19("JB19", "펜션", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB20("JB20", "기타숙박업", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB21("JB21", "룸싸롱", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB22("JB22", "빠, 스탠드빠", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB23("JB23", "카바레, 나이트클럽", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB24("JB24", "요정, 준요정", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB25("JB25", "단란주점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB26("JB26", "관광음식점", null, new StoreTypeCode[]{StoreTypeCode.ST01, StoreTypeCode.ST03}),
	JB27("JB27", "식자재 유통", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB28("JB28", "공산품 유통", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB29("JB29", "주류 유통", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB30("JB30", "주방설비", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB31("JB31", "시설설비", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB32("JB32", "인테리어", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB33("JB33", "포스", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB34("JB34", "보완", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB35("JB35", "대여", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB36("JB36", "렌탈", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB37("JB37", "마케팅", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB38("JB38", "디자인", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB39("JB39", "프랜차이즈", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB40("JB40", "컨설팅", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB41("JB41", "세무", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB42("JB42", "회계", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB43("JB43", "노무", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB44("JB44", "법률", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB45("JB45", "중고", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	JB46("JB46", "폐업", null, new StoreTypeCode[]{StoreTypeCode.ST02}),
	;

	private String id;
	private String name;
	private String comment;
	private StoreTypeCode[] storeTypeCodes;

	JobTypeCode(String id, String name, String comment, StoreTypeCode[] storeTypeCodes) {
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.storeTypeCodes = storeTypeCodes;
	}

	public static List<JobTypeCode> getValuesByStoreTypeCode(StoreTypeCode storeTypeCode) {
		return Arrays.asList(JobTypeCode.values()).stream()
		             .filter(code -> Arrays.asList(code.storeTypeCodes).contains(storeTypeCode))
		             .collect(Collectors.toList());
	}

	public static JobTypeCode getValueById(String id) {
		for (JobTypeCode jobTypeCode : JobTypeCode.values()) {
			if (jobTypeCode.getId().equals(id)) {
				return jobTypeCode;
			}
		}
		return null;
	}
}
