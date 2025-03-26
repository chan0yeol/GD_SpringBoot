package com.min.edu.sample;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.min.edu.service.CarRepository;
import com.min.edu.service.OwnerRepository;
import com.min.edu.service.UserRepository;
import com.min.edu.vo.Car;
import com.min.edu.vo.Owner;
import com.min.edu.vo.User;

@Configuration
public class CarSample {
	
	@Bean
	CommandLineRunner commandLineRunner(CarRepository carRepository,
										OwnerRepository ownerRepository,
										UserRepository userRepository) {

		return args -> {
//			Owner owner1 = new Owner("Kim", "Robinson");
//			Owner owner2 = new Owner("Lee", "Lucas");
//			Car ford = new Car("Ford","Mustang", "Red", "ASD-1122", 2022, 590000, owner1);
//			Car hyundai = new Car("Hyundai", "avante", "Green", "SSD-3001", 2019, 300000, owner2);
//			Car kia = new Car("Kia", "ray", "White", "KKO-2243", 2020, 310000, owner2);
//			ownerRepository.saveAll(List.of(owner1,owner2));
//			carRepository.saveAll(List.of(ford, hyundai, kia));
//
//			 TODO 005 Security에서 사용할 bcrypt로 암호화된 password를 가진 Sample 사용자 입력
//
//			User u1 = User.builder()
//							.username("user")
//							.password("$2y$04$Db/LonMuBz2iG474WefoP.a2J.68cdZ5ZqPuUbkG7LEVBfxwm159q")
//							.role("USER")
//							.build();
//
//			User u2 = User.builder()
//					.username("admin")
//					.password("$2y$04$Db/LonMuBz2iG474WefoP.a2J.68cdZ5ZqPuUbkG7LEVBfxwm159q")
//					.role("ADMIN")
//					.build();
//
//			userRepository.saveAll(List.of(u1,u2));

		};
	}
	
}
