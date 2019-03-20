package io.oseyo.service.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import io.oseyo.service.common.domain.code.StoreTypeCode;

@Entity
@Getter @Setter
public class Store {
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private StoreTypeCode type;

	@NotNull
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "store")
	private List<StoreJob> jobs = new ArrayList<>();

	@NotNull
	private String address1;

	private String address2;

	private String latitude;

	private String longitude;
}
