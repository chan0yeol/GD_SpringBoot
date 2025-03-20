package com.min.edu.vo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "CAR")
@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Car {

	// 해당 프로퍼티가 테이블의 주키(primary key)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "BRAND")
	private String brand;
	@Column(name = "MODEL")
	private String model;
	@Column(name = "COLOR")
	private String color;
	@Column(name = "REGISTERNUMBER")
	private String registerNumber;

	@Column(name = "PRODUCTYEAR")
	private Integer productYear;
	@Column(name = "PRICE")
	private Integer price;

	// TODO FetchType.EAGER -> 즉시실행
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ownerid")
	private Owner owner;
	
	public Car() {
	}

	public Car(String brand, String model, String color, String registerNumber, Integer productYear, Integer price) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.productYear = productYear;
		this.price = price;
	}

	public Car(String brand, String model, String color, String registerNumber, Integer productYear, Integer price,
			Owner owner) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.productYear = productYear;
		this.price = price;
		this.owner = owner;
	}

	

}
