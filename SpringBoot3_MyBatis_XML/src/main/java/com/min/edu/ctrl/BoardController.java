package com.min.edu.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.min.edu.model.IBoardDao;
import com.min.edu.model.IBoardDaoInterface;
import com.min.edu.vo.BoardVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO 009 요청 처리를 위한 Spring Controller 작성
@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final IBoardDao dao;
	private final IBoardDaoInterface boardDaoInterface;
	
	@GetMapping("/")
	public String home() {
		log.info("처음 호출되는 GET 요청");
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		log.info("Board XML Mapper 게시판 전체 조회");
		List<BoardVo> lists =  dao.selectBoard();
		model.addAttribute("lists",lists);
		return "boardList";
	}
	
	@GetMapping("/boardListInterface.do")
	public String boardListInterface(Model model) {
		log.info("Board Interface Mapper 게시판 전체 조회");
		List<BoardVo> lists =  boardDaoInterface.selectBoardInterface();
		model.addAttribute("lists",lists);
		return "boardList";
	}
}
