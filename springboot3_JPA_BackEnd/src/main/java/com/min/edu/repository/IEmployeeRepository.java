package com.min.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.edu.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {


}
