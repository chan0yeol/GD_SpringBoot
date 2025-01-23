package com.min.edu.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.service.IBoardService;
import com.min.edu.vo.BoardVo;
import com.min.edu.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final IBoardService service;
	
	@GetMapping("/")
	public String index() {
		log.info("BoardController / GET 요청");
		return "index";
	}
	@GetMapping("/home.do")
	public String home() {
		log.info("BoardController /home.do GET 요청");
		return "home";
	}
	
	/*
	 * 이전의 cache 삭제 response에서 Pragma, Cache-Control, Expires 
	 */
	@GetMapping("/boardList.do")
	public String boardList(Model model, HttpServletResponse response) {
		log.info("BoardController /boardList.do GET ");
		/*
		 * 캐쉬 삭제 코드 작성
		 */
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Expires", "0");
		List<BoardVo> boardList = service.userBoardList();
		model.addAttribute("boardList",boardList);
		return "boardList";
	}
	
	@RequestMapping(value = "/multiDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String multiDelete(@RequestParam List<String> chkVal) {
		log.info("BoardController /multiDelete.do GET or POST ");
		log.info("Param : List {}", chkVal);
		int n = service.delflagBoard(chkVal);
		log.info("다중 삭제 된 Row : {}", n);
		return "redirect:/boardList.do";
	}
	@GetMapping("/insertBoard.do")
	public String insertBoard() {
		log.info("BoardController /insertBoard.do GET");
		return "insertBoard";
	}
	@PostMapping("/insertBoard.do")
	public String insertBoard(BoardVo boardVo, HttpSession session) {
		log.info("BoardController /insertBoard.do POST");
		String id = ((UserVo)session.getAttribute("loginVo")).getId();
		log.info("Session ID 값  : {}", id);
		boardVo.setId(id);		
		int n = service.writeBoard(boardVo);
		log.info("글작성 성공 여부 : {}", (n>0)?"입력성공":"실패");
		return (n>0)?"redirect:/detailBoard.do?seq="+boardVo.getSeq():"";
	}
	
	@GetMapping("/detailBoard.do")
	public String detailBoard(String seq, Model model) {
		log.info("BoardController /detailBoard.do GET");
		BoardVo boardVo = service.getOneBoard(seq);
		model.addAttribute("boardVo",boardVo);
		return "detailBoard";
	}
	
	@GetMapping("/replyBoard.do")
	public String replyBoard(String seq, Model model) {
		log.info("UserController /replyBoard.do GET : {} ", seq);
		BoardVo boardVo = service.getOneBoard(seq);
		model.addAttribute("boardVo",boardVo);
		return "replyInsert";
	}
	
	@PostMapping("/replyBoard.do")
	public String replyBoard(BoardVo vo, HttpSession session) {
		log.info("UserController /replyBoard.do POST : {} ", vo);
		String id = ((UserVo)session.getAttribute("loginVo")).getId();
		vo.setId(id);
		int n = service.reply(vo);
		log.info("답글작성 성공 여부 : {}", (n>0)?"입력성공":"실패");
		return n>0?"redirect:/boardList.do":"redirect:/replyBoard.do?seq="+vo.getSeq();
	}
	
	@GetMapping("/restoreBoard.do")
	public String restoreBoard(Model model) {
		log.info("UserController /restoreBoard.do GET");
		List<BoardVo> restoreList = service.restoreBoard();
		model.addAttribute("restoreList", restoreList);
		return "restoreBoard";
	}
	
}
