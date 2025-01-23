package com.min.edu.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.min.edu.model.service.IBoardService;
import com.min.edu.model.service.IUserService;
import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
	
	private final IUserService userService;
	private final IBoardService boardService;
	
	@PostMapping("/duplicationAjax.do")
	public String duplicationAjax(String checkId) {

		log.info("UserRestController /duplicationAjax.do POST 아이디 중복검사 : {}",checkId);
		int check = userService.isDuplicateCheck(checkId);
		Map<String, String> map = new HashMap<String, String>();
		String chk = (check == 0)?"true":"false";
		map.put("isc", chk);
		// TODO GSON		
		Gson gson = new GsonBuilder().create();
		String checkJson = gson.toJson(map);
		log.info("완료된 판단 결과의 JSON toString : {}", checkJson);
		return checkJson;
	}
	@PostMapping("/duplicationFetch.do")
	public String duplicationFetch(String checkId) {
		log.info("UserRestController /duplicationFetch.do POST 아이디 중복검사 : {}",checkId);
		int check = userService.isDuplicateCheck(checkId);
		return check==0?"true":"false";
	}
	@PostMapping("/findId.do")
	public String findId(@RequestParam Map<String, Object> map) {
		log.info("UserRestController /findId.do POST 아이디 찾기 : {}",map);
		String id = userService.findId(map);
		return StringUtils.defaultIfEmpty(id, "");
	}
	@PostMapping("/convertForm.do")
	public String convertForm(@RequestBody List<Map<String, String>> data) {
		for (Map<String, String> map : data) {
			String fieldName = map.get("name");
			String fieldValue = map.get("value");
			System.out.printf("\n\n \t {%s:%s} \n\n", fieldName, fieldValue);
		}
		return "check";
	}
	
	
	// 같은 경로의 depth이기 때문에 처리 불가
	// /{toAuth}.do와 /toAuth.do 가 매핑되어 있을 경우 
	@PostMapping(value = {"/{toAuth}.do"})
	public String convertForm(@RequestParam List<String> chkId
			,@PathVariable(value = "toAuth",required = false) String toAuth) {
		log.info("UserRestController /{toAuth}.do POST 회원권한수정");
			System.out.println("\n\ntoAuth 값 :" + toAuth);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("checkid", chkId);
			map.put("authvalue", toAuth.equals("toAuth")?"A":"U");
			int n = userService.setChangeAuth(map);
		return n>0?"true":"false";
//			return "chk";
	}
	
//	@PostMapping(value = {"/toAuth.do"})
//	public String convertForsm(@RequestParam List<String> chkId) {
//		System.out.println("\n\n /toAuth.do \n");
//		return "chk";
//	}
	
	@PostMapping("/getSearchUser.do")
	public String getSearchUser(@RequestParam Map<String, Object> map) {
		log.info("UserRestController getSearchUser Post 회원 조회 : {}",map);
		// TODO JSON Object 작성법
		// 1) JSON Object 작성 법
		JSONObject obj = new JSONObject();
		obj.put("연필", "모나미");
		// 2) JSON Array 작성법
		JSONArray jarr = new JSONArray();
		JSONObject obj01 = new JSONObject();
		System.out.println("SimpleJson을 통한 Json String 생성 : " + obj.toString());
		obj01.put("opt", map.get("opt"));
		JSONObject obj02 = new JSONObject();
		obj02.put("keyword", map.get("keyword"));
		jarr.add(obj01);
		jarr.add(obj02);
		System.out.println(jarr.toString());
		
		JSONObject totalObj = new JSONObject();
		totalObj.put("토탈", jarr);
		System.out.println("Simple JSON Array : totalObj : " + totalObj.toString());
		// 3) GSON을 통한 JCF 결과를 변환
		List<UserVo> searchList = userService.getSearcherUser(map);
		Gson gson = new GsonBuilder().create();
		gson.toJson(searchList);
		log.info("\n\n userService.getSearcherUser(map) 결과 {}\n",gson.toJson(searchList));
		return gson.toJson(searchList);
	}
	
	@GetMapping("/restore.do")
	public String restore(@RequestParam List<String> seqs) {
		log.info("UserRestController /restore Post 삭제글 복구 : {}",seqs);
		int n = boardService.restoreDelflag(seqs);
		
		return n>0?"true":"false";
	}
	
	
	
	
	@PostMapping("/test.do")
	public String test(Model model){
		log.info("testt Post");
		model.addAttribute("test","testtt");
		return "<script>alert('test'); location.href='./home.do'</script>";
	}
	
}
