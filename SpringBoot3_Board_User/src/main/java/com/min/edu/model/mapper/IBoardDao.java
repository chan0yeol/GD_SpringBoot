package com.min.edu.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.edu.vo.BoardVo;

@Mapper
public interface IBoardDao {
	// 게시글 리스트
	List<BoardVo> userBoardList();           
	// 게시글 삭제하기
	int delflagBoard(List<String> seqs);     
	// 게시글 쓰기
	int writeBoard(BoardVo boardVo);         
	// 게시글 상세보기
	BoardVo getOneBoard(String seq);         
	// 답글 수정
	int replyUpdate(BoardVo boardVo);        
	// 답글 입력
	int replyInsert(BoardVo boardVo);        
	// 삭제된 게시글 조회
	List<BoardVo> restoreBoard();            
	// 게시글 복구
	int restoreDelflag(List<String> seqs);   

}
