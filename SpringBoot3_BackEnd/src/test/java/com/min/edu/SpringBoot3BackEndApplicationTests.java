package com.min.edu;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.min.edu.service.CarRepository;
import com.min.edu.vo.Car;

@SpringBootTest
class SpringBoot3BackEndApplicationTests {

	@Autowired
	private CarRepository carRepository;
	
	@Test
	void carRepository_test() {
		List<Car> find = carRepository.findByBrand("Ford");
		System.out.println(find);
		assertNotEquals(find.size(), 0);
	}

}
