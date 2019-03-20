package io.oseyo.service.common.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.oseyo.service.common.client.SmsClient;

@Component
@Slf4j
public class MobileMessageService {
	@Autowired
	SmsClient smsClient;

	public void sendMobileAuthCode(String phoneNumber, String authCode) {
		smsClient.requestSendMessage(phoneNumber, "인증코드 ", authCode);
		log.info("phoneNumber:{}, authCode:{}",phoneNumber,authCode);
	}
}
