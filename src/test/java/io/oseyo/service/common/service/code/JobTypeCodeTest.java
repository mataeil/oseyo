package io.oseyo.service.common.service.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import io.oseyo.service.common.domain.code.JobTypeCode;
import io.oseyo.service.common.domain.code.StoreTypeCode;

public class JobTypeCodeTest {
	@Test
	public void getValues() {
		JobTypeCode[] values = JobTypeCode.values();
		print(values);
		assertThat(values).isNotEmpty();

		JobTypeCode code = JobTypeCode.valueOf("JB01");
		System.out.println(code);

		List<JobTypeCode> jobTypeCodesByStoreTypeCode = JobTypeCode.getValuesByStoreTypeCode(StoreTypeCode.ST01);
		System.out.println(jobTypeCodesByStoreTypeCode.toString());
	}

	public void print(JobTypeCode[] array) {
		for (JobTypeCode code : array) {
			System.out.println(code);
		}
	}
}