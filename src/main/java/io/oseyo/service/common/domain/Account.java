package io.oseyo.service.common.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Account {
	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	private String phoneNumber;

	private String token;

	private LocalDateTime validDatetime;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	private User user;
}
