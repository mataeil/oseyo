package io.oseyo.service.common.service.code;

import lombok.Getter;

@Getter
public enum  ErrorCode {
	ERR_CMM_001("ERR-CMM-001", "오류입니다. 다시 시도 해주세요."),
	ERR_CMM_002("ERR-CMM-002", "코드를 찾을 수 없습니다."),

	ERR_LGN_001("ERR-LGN-001", "유효하지 않은 토큰입니다."),
	ERR_LGN_002("ERR-LGN-002", "중복 닉네임입니다."),
	ERR_LGN_003("ERR-LGN-003", "상호명 입력을 확인 해주세요."),
	ERR_LGN_004("ERR-LGN-004", "업종 선택을 확인 해주세요."),
	ERR_LGN_005("ERR-LGN-005", "주소 입력을 확인 해주세요."),
	ERR_LGN_006("ERR-LGN-006", "전화번호 입력을 확인 해주세요."),
	ERR_LGN_007("ERR-LGN-007", "인증번호를 요청하세요."),
	ERR_LGN_008("ERR-LGN-008", "인증번호가 일치하지 않습니다."),
	ERR_LGN_009("ERR-LGN-009", "전화번호가 올바르지 않습니다."),
	ERR_LGN_010("ERR-LGN-010", "인증번호 요청 시간이 지났습니다."),
	ERR_LGN_011("ERR-LGN-011", "GPS좌표 데이터 입력을 확인 해주세요."),
	ERR_LGN_012("ERR-LGN-012", "상점타입 선택을 확인해주세요."),
	ERR_LGN_013("ERR-LGN-013", "보건증 날짜 입력이 올바르지 않습니다."),
	ERR_LGN_014("ERR-LGN-014", "닉네임 입력을 확인 해주세요."),
	ERR_LGN_015("ERR-LGN-015", "SMS 인증서버 오류입니다."),

	;

	private String id;
	private String message;

	ErrorCode(String id, String message) {
		this.id = id;
		this.message = message;
	}
}
