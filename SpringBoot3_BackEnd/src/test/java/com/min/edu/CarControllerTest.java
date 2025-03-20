package com.min.edu;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Spring Data rest api 실행")
	void contextLoads() throws Exception {
		this.mockMvc.perform(get("http://localhost:9099/api/vehicles")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("vechicle"))
				.andDo(print());
	}
	
	
//	// 한개의 값을 입력할 때 TEST는 @ValueSource를 사용한다. 
//	// 여러개의 값을 입력할 때 @CsvSource({"2019, SM73", "2020, ray"})
//	@ParameterizedTest
//	@ValueSource(strings = {"2022"})
//	@DisplayName("자동차 생산년도로 조회")
//	void testGetCarByProductYear(String productYear) throws Exception {
//		mockMvc.perform(get("http://localhost:9099/api/vehicles/search/findByProductYear?productYear="+productYear))
//			.andExpect(status().isOk())
//			.andDo(document("findByProductYear", queryParameters(
//							parameterWithName("productYear")
//							.description("생산년도")),
//							responseFields(
//								fieldWithPath("_embedded.cars[].id").description("자동차의 아이디"),
//								fieldWithPath("_embedded.cars[].brand").description("자동차의 제조사"),
//								fieldWithPath("_embedded.cars[].model").description("자동차의 모델명"),
//								fieldWithPath("_embedded.cars[].color").description("자동차의 색상"),
//								fieldWithPath("_embedded.cars[].registerNumber").description("자동차의 등록번호"),
//								fieldWithPath("_embedded.cars[].productYear").description("자동차의 생산년도"),
//								fieldWithPath("_embedded.cars[].price").description("자동차의 가격"),
//								fieldWithPath("_embedded.cars[]._links.self.href").description("자동차 자체링크"),
//								fieldWithPath("_embedded.cars[]._links.car.href").description("자동차의 상세링크"),
//								fieldWithPath("_embedded.cars[]._links.owner.href").description("자동차의 소유자링크"),
//								fieldWithPath("_links.self.href").description("현재요청링크")
//							)
//						)
//				  );
//	}
//	
//	@ParameterizedTest
//	@ValueSource(strings = {"1"})
//	@DisplayName("자동차의 소유자 정보 조회")
//	void testGetOwnerFromCar(String carOwner) throws Exception{
//		mockMvc.perform(get("http://localhost:9099/api/vehicles/"+carOwner+"/owner"))
//			.andExpect(status().isOk())
//			.andDo(document("findByOwner", 
//					    responseFields(
//							fieldWithPath("ownerid").description("소유자 아이디"),
//							fieldWithPath("firstname").description("소유자 이름"),
//							fieldWithPath("lastname").description("소유자 성"),
//							fieldWithPath("_links.self.href").description("소유자 정보 링크"),
//							fieldWithPath("_links.owner.href").description("소유자 자체 링크"),
//							fieldWithPath("_links.cars.href").description("소유자가 보유한 자동차 목록 링크")
//						)
//					)
//				);
//	}
//	
//	@Test
//	@DisplayName("새로운 자동차 정보 입력")
//	void createVehicle() throws Exception{
//		String vehicleJson = "{"
//				+ "    \"brand\": \"samsung\","
//				+ "    \"model\": \"sm3\","
//				+ "    \"color\": \"Brown\","
//				+ "    \"registerNumber\": \"SAM0018\","
//				+ "    \"productYear\": 2016,"
//				+ "    \"price\": 1140350,"
//				+ "    \"owner\":{"
//				+ "        \"owner\":\"http://localhost:9099/api/owners/1\""
//				+ "    }"
//				+ "}"
//				+ "";
//		mockMvc.perform(post("http://localhost:9099/api/vehicles").accept(MediaType.APPLICATION_JSON).content(vehicleJson))
//			.andExpect(status().isCreated()) // HTTP 201 Create
//			.andDo(document("create-vehicle", 
//						requestFields(
//								fieldWithPath("brand").description("자동차의 브랜드"),
//								fieldWithPath("model").description("자동차의 모델명"),
//								fieldWithPath("color").description("자동차의 색상"),
//								fieldWithPath("registerNumber").description("자동차의 등록번호"),
//								fieldWithPath("productYear").description("자동차의 생산년도"),
//								fieldWithPath("price").description("자동차의 가격"),
//								fieldWithPath("owner.owner").description("자동차 소유자 링크")
//						), 
//						responseFields(
//								fieldWithPath("id").description("자동차의 아이디"),
//								fieldWithPath("brand").description("자동차의 브랜드"),
//								fieldWithPath("model").description("자동차의 모델명"),
//								fieldWithPath("color").description("자동차의 색상"),
//								fieldWithPath("registerNumber").description("자동차의 등록번호"),
//								fieldWithPath("productYear").description("자동차의 생산년도"),
//								fieldWithPath("price").description("자동차의 가격"),
//								fieldWithPath("_links.self.href").description("자동차 자체 링크"),
//								fieldWithPath("_links.car.href").description("자동차 정보 링크"),
//								fieldWithPath("_links.owner.href").description("자동차의 소유자 정보 링크")
//						)		
//					)
//				);
//	}
//	
//	@Test
//	@DisplayName("자동차 소유자(Owner) 변경 JSON / PATCH")
//	void patchVehicleUserJson() throws Exception {
//		String userJson = "http://localhost:9099/api/owner/2";
//		
//		mockMvc.perform(put("/api/vehicles/2/owner")
//				.contentType("text/uri-list")
//				.content(userJson)
//			)
//			.andExpect(status().is(204))// HTTP 204 No Content PUT은 어떠한 값도 반환하지 않음 
//			.andDo(print())
//			.andDo(document("vehicle-owner-update")
//		);
//	}
//	
//	@ParameterizedTest
//	@ValueSource(strings = {"502"})
//	@DisplayName("자동차 ID를 통한 삭제")
//	void deleteVehicle(String delCarId) throws Exception {
//		mockMvc.perform(delete("http://localhost:9099/api/vehicles/{id}",delCarId))
//		.andExpect(status().isNoContent()) // HTTP 204
//		.andDo(document("delete-vehicle",
//				pathParameters(
//						parameterWithName("id").description("삭제할 자동차의 아이디")
//					)
//			   )
//		);
//	}
//	
	
}
