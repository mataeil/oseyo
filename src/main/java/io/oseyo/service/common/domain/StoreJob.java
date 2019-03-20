package io.oseyo.service.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.oseyo.service.common.domain.code.JobTypeCode;

@Entity
@Getter @Setter
@NoArgsConstructor
public class StoreJob {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "store_id")
	private Store store;

	@Enumerated(EnumType.STRING)
	private JobTypeCode jobTypeCode;
}
