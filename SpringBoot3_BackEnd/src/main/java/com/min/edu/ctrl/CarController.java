package com.min.edu.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.service.CarRepository;
import com.min.edu.vo.Car;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CarController {
	
	private final CarRepository carRepository;
	
	@RequestMapping("/cars")
	public Iterable<Car> getCars(){
		log.info("전체 자동차(Car) 테이블 조회");
		return carRepository.findAll();
	}
}
