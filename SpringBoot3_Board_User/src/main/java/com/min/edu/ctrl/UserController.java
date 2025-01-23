package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.service.IUserService;
import com.min.edu.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final IUserService userService;

	@GetMapping("/loginForm.do")
	public String loginForm() {
		log.info("UserController loginForm.do GET");
		return "loginForm";
	}

	@PostMapping("/login.do")
	public String login(@RequestParam Map<String, Object> map, HttpSession session, HttpServletResponse resp) throws IOException {
		log.info("UserController /login.do 로그인 POST : {} ", map);
		resp.setContentType("text/html;charset=UTF-8");
		UserVo loginVo = userService.getLogin(map);
		
		if (loginVo != null) {
			session.setAttribute("loginVo", loginVo);
			session.setMaxInactiveInterval(60*10*6);
			log.info("{} 님 반갑습니다.", loginVo.getName());
			resp.getWriter().print("<script>alert('"+loginVo.getName()+"님 반갑습니다."+"');location.href='./boardList.do'</script>");
		}else {
			resp.getWriter().print("<script>alert('로그인 정보가 없습니다.');location.href='./loginForm.do';</script>");
		}
		return null;
	}

	@GetMapping("/signupForm.do")
	public String signupForm() {
		log.info("UserController /signupForm.do GET 회원가입 화면");
		return "signupForm";
	}

	@GetMapping("/duplication.do")
	public String duplication() {
		log.info("UserController /duplication.do GET 아이디 중복검사 화면");
		return "duplication";
	}

	@PostMapping("/signUp.do")
	public String signUp(UserVo userVo, HttpServletResponse response) throws IOException {
		log.info("UserController /signUp.do POST 회원가입 : {}", userVo);
		int n = userService.signupMember(userVo);
		log.info("회원가입 여부 : {}", (n > 0) ? "가입성공" : "가입실패");
		if(n==1) {
			return "redirect:loginForm.do";
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("<script>alert('회원가입실패');location.href='./signupForm.do'</script>");
			return null;
		}
	}

	@GetMapping("/findIdWindow.do")
	public String findIdWindow() {
		log.info("UserController /findIdWindow.do GET 아이디 찾기");

		return "findId";
	}

	@GetMapping("/logout.do")
	public String logout(HttpSession session, HttpServletResponse response) {
		log.info("UserController /logout.do 로그아웃");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Expires", "0");
		session.invalidate();
		return "redirect:/loginForm.do";
	}

	@GetMapping("/managementUser.do")
	public String managementUser(Model model) {
		log.info("UserController /managementUser.do 회원관리 이동");
		List<UserVo> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		return "managementUser";
	}

	@GetMapping("/userSelectAll.do")
	public String userSelectAll(Model model) {
		log.info("UserController /managementUser.do [관리자] 회원전체조회 이동");
		List<UserVo> userList = userService.userSelectAll();
		model.addAttribute("userList", userList);
		return "userSelectAll";
	}
}
