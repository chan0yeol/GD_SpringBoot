package com.min.edu.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.min.edu.vo.BoardVo;

@Mapper
public interface IBoardDaoInterface {
	
	@Select("SELECT SEQ, ID, TITLE, CONTENT, REGDATE, DELFLAG FROM BOARD ORDER BY REGDATE DESC")
	public List<BoardVo> selectBoardInterface();
}
