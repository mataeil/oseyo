package io.oseyo.service.common.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.oseyo.service.common.exception.ApiLoginException;
import io.oseyo.service.common.service.code.ErrorCode;
import io.oseyo.service.common.service.response.OseyoResponse;

@Component
public class SmsClient {
	@Value("${sens.sms.serviceId}")
	String serviceId;

	@Value("${sens.sms.accessKeyId}")
	String accessKeyId;

	@Value("${sens.sms.serviceSecret}")
	String serviceSecret;

	@Value("${sens.sms.host}")
	String host;

	@Value("${sens.sms.from}")
	String from;

	RestTemplate restTemplate = new RestTemplate();

	public void requestSendMessage(String to, String subject, String content) {

		URI uri = UriComponentsBuilder.newInstance()
		                                                  .scheme("https")
		                                                  .host(host)
		                                                  .path("/v1/sms/services/"+serviceId+"/messages")
		                                                  .build().encode().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		headers.add("x-ncp-auth-key", accessKeyId);
		headers.add("x-ncp-service-secret", serviceSecret);

		SmsRequestParam smsRequestParam = new SmsRequestParam();
		smsRequestParam.setFrom(from);
		smsRequestParam.setTo(Arrays.asList(to));
		smsRequestParam.setSubject(subject);
		smsRequestParam.setContent(content);

		HttpEntity entity = new HttpEntity(smsRequestParam, headers);

		try {
			ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
			if (responseEntity.getStatusCode() != HttpStatus.ACCEPTED) {
				throw new ApiLoginException(ErrorCode.ERR_LGN_015);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiLoginException(ErrorCode.ERR_LGN_015);
		}
	}

	@Getter@Setter
	@NoArgsConstructor
	static class SmsRequestParam{
		final String type = "SMS";
		String from;
		List<String> to;
		String subject;
		String content;
	}

}
