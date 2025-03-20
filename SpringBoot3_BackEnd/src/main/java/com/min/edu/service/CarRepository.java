package com.min.edu.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.min.edu.vo.Car;

@RepositoryRestResource(path = "vehicles")
public interface CarRepository extends JpaRepository<Car, Long> {

	// findBy, And/Or, OrderBy, @Query
//	List<Car> findByBrand(String brand);
//	List<Car> findByColor(String color);
	List<Car> findByProductYear(Integer productYear);
	
	// And/Or
	List<Car> findByBrandAndModel(String brand, String model);
	List<Car> findByBrandOrModel(String brand, String model);
	
	//OrderBy
	List<Car> findByBrandOrderByProductYearAsc(String brand);
	
	//@Query
	@Query("select c from Car c where c.brand=?1")
	List<Car> findByBrandQuery(String findBrand);
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndsWith(String brand);
	
	// 필요한 QueryString을 추가하여 요청
	// Bind해주는 @Param을 작성
	// 명시적으로 필요한 쿼리를 @Param을 통해서 Query의 :파라미터명에 매칭된다.
	@Query("select c from Car c where c.brand= :brand")
	List<Car> findByBrand(@Param("brand") String brand);
	@Query("select c from Car c where c.color= :color")
	List<Car> findByColor(@Param("color")String color);
	
}
