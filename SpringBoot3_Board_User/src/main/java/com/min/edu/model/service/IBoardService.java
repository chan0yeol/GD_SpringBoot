package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.BoardVo;

public interface IBoardService {
	// 게시글 리스트
	List<BoardVo> userBoardList();           
	// 게시글 삭제하기
	int delflagBoard(List<String> seqs);     
	// 게시글 쓰기
	int writeBoard(BoardVo boardVo);         
	// 게시글 상세보기
	BoardVo getOneBoard(String seq);         
        
	// 삭제된 게시글 조회
	List<BoardVo> restoreBoard();            
	// 게시글 복구
	int restoreDelflag(List<String> seqs);  
	
	// 답글 작성 Transaction
	int reply(BoardVo vo);
}
