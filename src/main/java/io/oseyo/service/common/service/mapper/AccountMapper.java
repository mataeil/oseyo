package io.oseyo.service.common.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.oseyo.service.common.domain.Account;
import io.oseyo.service.common.domain.Store;
import io.oseyo.service.common.domain.StoreJob;
import io.oseyo.service.common.domain.User;
import io.oseyo.service.common.domain.code.JobTypeCode;
import io.oseyo.service.common.service.response.LoginUserResponse;
import io.oseyo.service.common.service.response.LoginUserResponse._JobTypeCode;

public class AccountMapper {

	public static LoginUserResponse getLoginUserResponse(Account account) {
		LoginUserResponse response = new LoginUserResponse();

		//유저상세정보 없음
		if (Objects.isNull(account.getUser())) {
			response.setIsUserDetail(Boolean.FALSE);
			return response;
		}
		response.setIsUserDetail(Boolean.TRUE);
		response.getUserDetail().getUser().setPhoneNumber(account.getPhoneNumber());

		//가게정보 없음
		User user = account.getUser();
		if(Objects.isNull(user.getStore())){
			return response;
		}

		//가게정보 세팅
		Store store = user.getStore();
		response.getUserDetail().setStoreTypeCode(store.getType().getId());
		response.getUserDetail().getStore().setName(store.getName());
		response.getUserDetail().getStore().setAddress1(store.getAddress1());
		response.getUserDetail().getStore().setAddress2(store.getAddress2());
		response.getUserDetail().getStore().setJobTypeCodeListSize(store.getJobs().size());
		if (store.getJobs().size() > 0) {
			List<_JobTypeCode> jbList = new ArrayList<>();
			for (StoreJob storeJob : store.getJobs()) {
				_JobTypeCode jobTypeCode = new _JobTypeCode();
				jobTypeCode.setJobTypeCode(storeJob.getJobTypeCode().getId());
				jobTypeCode.setJobTypeCodeName(JobTypeCode.getValueById(storeJob.getJobTypeCode().getId()).getName());
				jbList.add(jobTypeCode);
			}
			response.getUserDetail().getStore().setJobTypeCodeList(jbList);
		}

		return response;
	}
}
