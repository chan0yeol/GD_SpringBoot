package com.min.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class UserRepositoryTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void loginSessionTest() throws Exception {
		Map<String, String> loginRequest = new HashMap<String, String>();
		loginRequest.put("username", "user");
		loginRequest.put("password", "testuser");

		MvcResult result = mockMvc.perform(post("/login").with(csrf())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) // x-www-form-urlencoded 형식으로 요청
				.content("username=" + loginRequest.get("username") + "&password=" + loginRequest.get("password"))
				.with(httpBasic(loginRequest.get("username"), loginRequest.get("password"))) // basic auth 추가
		).andExpect(status().is3xxRedirection()) // Redirect 응답 확인 302
				.andReturn();

		// Redirection 된 url을 확인
		String redirectedUrl = result.getResponse().getHeader("Location");
		System.out.println("redirected to " + redirectedUrl);

		// 세션에서 인증 정보를 확인
		MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
		SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		System.out.println(securityContext);
		System.out.println(securityContext.getAuthentication()); // 시큐리티 토큰
		System.out.println(securityContext.getAuthentication().getName());
		assertNotNull(securityContext);
		assertTrue(securityContext.getAuthentication() != null);
		assertEquals("user", securityContext.getAuthentication().getName());

		mockMvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON).session(session)
				.accept(MediaType.APPLICATION_JSON).with(csrf())).andExpect(status().isOk())
				.andDo(document("after_login_api", responseFields( // 응답 필드 확인
						fieldWithPath("_links").description("전달 받은 resource"),
						fieldWithPath("_links.cars.href").description("사용자 링크"),
						fieldWithPath("_links.cars.templated").description("사용자 링크 templated"),
						fieldWithPath("_links.owners.href").description("자동차 링크"),
						fieldWithPath("_links.owners.templated").description("자동차 링크 templated"),
						fieldWithPath("_links.profile.href").description("프로파일 링크"))))
				.andDo(print());
	}

}
