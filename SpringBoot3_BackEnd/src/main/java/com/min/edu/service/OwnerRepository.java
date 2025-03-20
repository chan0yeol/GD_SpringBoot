package com.min.edu.service;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.min.edu.vo.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	
	@Query("select o from Owner o join fetch o.cars")
	List<Owner> findAllWithCars();
	
	@EntityGraph(attributePaths = "cars")
	List<Owner> findAll();
	
}
