package com.min.edu.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 테이블간의 관계 Car - Owner 테이블 설정
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ownerid;

	private String firstname, lastname;
	
	// TODO FetchType.LAZY -> mappedBy를 통해 지연로딩
	// TODO 직렬화와 역직렬화에서 특정 필드를 무시할 때 사용된다.
	// Car와 Owner간의 관계가 1:N관계의 문제점을 해결해야한다.
	// Car소유자와 연결 후 다시 Owner 테이블이 Car 테이블을 연결되는 직렬화가 반복됨
	@JsonIgnore // 개별필드에 직렬화/역직렬화에서 무시
 	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
//	@JsonBackReference // 부모쪽에서 직렬화가 가능하도록 설정 // 클래스 수준
	private List<Car> cars;

	public Owner(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}
}
