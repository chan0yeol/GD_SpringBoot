package com.min.edu.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.edu.vo.BoardVo;

// TODO 005 인터페이스로 선언되어 실행되어 지는 DAO
//		Board_Statement.xml의 namespace의 설정에 따라서 위치와 클래스를 작성
//		@Mapper로 선언하여 작성된 interface가 실행되어 지는 DAO메소드를 선언
@Mapper
public interface IBoardDao {
	
	// TODO 007 작성된 method명(selectBoard)는 Board_Statement.xml의 id로 사용한다.
	List<BoardVo> selectBoard();
}
