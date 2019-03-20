# oseyo

오세요 백엔드 서비스 입니다.

## 주요 라이브러리 버전
- org.springframework.boot.spring-boot-starter-parent 2.1.1.RELEASE
- org.springframework.boot.spring-boot-starter-data-jpa 2.1.1.RELEASE
- org.springframework.boot.spring-boot-starter-web 2.1.1.RELEASE
- com.h2database.h2 1.4.197

## DB
테스트 과정이기 때문에 h2메모리 DB를 사용 했습니다.
production 환경에서는 MySql or MariaDB를 사용합니다.

## 외부 API 연동
1. 문자전송
- SmsClient.java 구현
- SmsClient를 Inject 해서 사용 (예:MobileMessageService.java)
- 현재 구현체는 네이버클라우드의 SENS 시스템에 API를 콜 (https://www.ncloud.com/product/applicationService/sens)
