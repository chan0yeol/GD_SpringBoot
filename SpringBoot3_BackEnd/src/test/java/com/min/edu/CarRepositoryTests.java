package com.min.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.min.edu.service.CarRepository;
import com.min.edu.service.OwnerRepository;
import com.min.edu.vo.Car;
import com.min.edu.vo.Owner;

import jakarta.transaction.Transactional;

@SpringBootTest
class CarRepositoryTests {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	
	/**
	 * Test를 위한 자동자 청보 등록 
	 * <br>
	 * @BeforeAll을 통해서 클래스 단위에서 한번만 실행되도록 한다.
	 */
//	@BeforeAll
//	@DisplayName("JUnit Test를 위한 Sample Data 입력")
//	public static void setUp(@Autowired CarRepository carRepository,
//							@Autowired OwnerRepository ownerRepository) {
//		Owner owner = new Owner("testuser","GoodYear");
//		ownerRepository.save(owner);
//
//		carRepository.save(new Car("tesla", "Model X", "Red", "TESLA000", 2020, 200000, owner));
//		carRepository.save(new Car("tesla", "Model Y", "Blue", "TESLA111", 2021, 200000, owner));
//		carRepository.save(new Car("tesla", "Model Z", "Red", "TESLA222", 2022, 200000, owner));
//		carRepository.save(new Car("kia", "k5", "Blue", "KIA1234", 2020, 300000, owner));
//		carRepository.save(new Car("hyundai", "sonata", "Black", "HY01245", 2023, 200000, owner));
//	}
	
	@Transactional
//	@Test
	@DisplayName("Brand 를 통한 자동차 검색")
	public void findByBrand() {
		// "kia" 브랜드로 자동차 검색
		List<Car> carList = carRepository.findByBrand("kia");
		// 결과가 null이 아닌지 확인
		assertNotNull(carList,"자동차 목록이 null은 안됩니다.");
		assertFalse(carList.isEmpty(),"kia 브랜드 자동차가 하나도 반환되지 않았습니다.");
		
		// 반환된 자동차 목록 출력(옵션)
		carList.forEach(car ->{
			System.out.println("검색된 자동차 모델 : "+ car.getModel());
			System.out.println("해당 자동차의 소유자 : " + car.getOwner());
			System.out.println("--------------------------------------------");
		});
	} // brand 검색 영속성 @Transactional | EAGER 설정, Entity에 toString() 생성 유의 
	
	@Transactional
//	@Test
	@DisplayName("Color를 통한 자동차 검색")
	public void testFindByColor() {
		List<Car> carList = carRepository.findByColor("Blue");
		assertNotNull(carList); 
		assertTrue(carList.size() > 0);
		carList.forEach(car -> assertEquals("Blue", car.getColor()));
	}
	
//	@Test
	@DisplayName("ProductYear를 통한 자동차 검색")
	public void testFindByProductYear() {
		List<Car> carList = carRepository.findByProductYear(2020);
		assertNotNull(carList);
		assertTrue(carList.size()>0);
		carList.forEach(car -> assertEquals(2020, car.getProductYear()));
	}
	
//	@Test
	@DisplayName("brand와 Model을 통한 자동차 검색")
	public void testFindByBrandAndModel() {
		List<Car> carList = carRepository.findByBrandAndModel("Kia", "ray");
		assertEquals("Kia", carList.get(0).getBrand());
		assertEquals("ray", carList.get(0).getModel());
	}
	
//	@Test
	@DisplayName("Brand로 검색하고 productYear로 오름차순 정렬")
	public void testFindByBrandOrderByProductYearAsc() {
		List<Car> carList = carRepository.findByBrandOrderByProductYearAsc("tesla");
		assertTrue(carList.size()>0);
		carList.forEach(car -> {
			System.out.println("검색된 모델 : " + car.getModel().concat("-") + car.getProductYear());
		});
	}
	
//	@Test
	@DisplayName("@Query Annotation을 통한 brand 검색 ")
	public void testFindByBrandQuery() {
		List<Car> carList = carRepository.findByBrandQuery("Hyundai");
		assertTrue(carList.size()>0);
		carList.forEach(car -> {
			System.out.println("Hyundai : " + car.getBrand().concat("-").concat(car.getModel()));
		});
	}
	
//	@Test
	@DisplayName("@Query Annotation을 통한 Like문 Brand 검색")
	public void testFindByBrandEndWith() {
		List<Car> carList = carRepository.findByBrandEndsWith("Hyundai");
		assertTrue(carList.size()>0);
		carList.forEach(car -> {
			System.out.println("H% : " + car.getBrand().concat("-").concat(car.getModel()));
		});
	}
	
//	@ParameterizedTest
//	@Transactional
//	@ValueSource(longs = {1204})
//	@DisplayName("Car ID를 통한 Parameter 테스트 ")
//	@Rollback(false)
	public void testCarDeleteById(Long id) {
		System.out.println(id);
		// ID가 1204인 car가 데이터베이스에 존재하는지 확인
		Car car = carRepository.findById(id).orElse(null);
		System.out.println(car);
		assertNotNull(car, "Car를 삭제하기 위한 정보가 없음");
		
		// ID가 1024인 car를 삭제
		carRepository.deleteById(id);
//		carRepository.delete(car);
		
		// 삭제 후 ID가 106인 car가 데이터베이스에 존재하는지 확인
		assertFalse(carRepository.findById(id).isPresent(), "car 정보가 삭제되어 존재하지 않음");
	}
	
//	@Test
	@DisplayName("존재하는 소유자에게 Car 정보를 입력 테스트")
	public void testCarInsert() {
		// ownerid 1인 Owner를 조회
		Owner owner = ownerRepository.findById(1L).orElseThrow(); 
		
		// Car 객체를 저장
		Car saveCar = carRepository.save(new Car("Kia", "Model K", "Orange", "KIA0901", 2023, 345643, owner));
		assertNotNull(saveCar.getId()); // 저장후 ID 가 Null 이 아니어야함
		assertEquals(saveCar.getBrand(), "Kia");
		assertEquals(saveCar.getModel(), "Model K");
		assertEquals(saveCar.getColor(), "Orange");
		assertEquals(saveCar.getRegisterNumber(), "KIA0901");
		assertEquals(saveCar.getProductYear(), 2023);
		assertNotNull(saveCar.getOwner());
		assertEquals(saveCar.getOwner().getFirstname(), owner.getFirstname());
	}
	
//	@ParameterizedTest
	@ValueSource(longs = {1205})
	@DisplayName("Car정보의 소유자를 변경")
	public void testUpdateCarOwner(Long carId) { // 변경할 Car의 ID
		Long newOwnerId = 1L; // 새로운 소유자의 ID
		Car oldCar = carRepository.findById(carId)
				.orElseThrow(()-> new RuntimeException("조회 결과 없음"));
		Owner newOwner = ownerRepository.findById(newOwnerId).orElseThrow(()-> new RuntimeException("조회 결과 없음"));
		
		oldCar.setOwner(newOwner);		
		
		// 자동차 정보를 저장하여 소유자 변경을 DB 반영
		// 더티 캐싱 (dirty Cache)를 통해서 처리하지 않고 명시적으로 save() 메소드를 명시적으로 flush를 호출하여 즉시 DB반영
		carRepository.save(oldCar);
		
		Car updateCar = carRepository.findById(carId).orElseThrow();
		assertEquals(newOwnerId, updateCar.getOwner().getOwnerid());
		
		
	}
	

}

