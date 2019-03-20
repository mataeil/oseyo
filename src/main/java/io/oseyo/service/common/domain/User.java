package io.oseyo.service.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	private String nickName;

	private String hygieneRegisteredDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private Store store;
}
