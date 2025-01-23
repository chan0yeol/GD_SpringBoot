package com.min.edu.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.model.mapper.IBoardDao;
import com.min.edu.vo.BoardVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements IBoardService {
	
	private final IBoardDao dao;
	
	@Override
	public List<BoardVo> userBoardList() {
		log.info("BoardServiceImpl userBoardList");
		return dao.userBoardList();
	}

	@Override
	public int delflagBoard(List<String> seqs) {
		log.info("BoardServiceImpl delflagBoard");
		return dao.delflagBoard(seqs);
	}

	@Override
	public int writeBoard(BoardVo boardVo) {
		log.info("BoardServiceImpl writeBoard");
		return dao.writeBoard(boardVo);
	}

	@Override
	public BoardVo getOneBoard(String seq) {
		log.info("BoardServiceImpl getOneBoard");
		return dao.getOneBoard(seq);
	}

	@Override
	public List<BoardVo> restoreBoard() {
		log.info("BoardServiceImpl restoreBoard");
		return dao.restoreBoard();
	}

	@Override
	public int restoreDelflag(List<String> seqs) {
		log.info("BoardServiceImpl restoreDelflag");
		return dao.restoreDelflag(seqs);
	}

	@Override
	@Transactional(readOnly = true)
	public int reply(BoardVo vo) {
		log.info("BoardServiceImpl reply");
		int n = dao.replyUpdate(vo);
		int m = dao.replyInsert(vo);
		return (n+m)>0?1:0;
	}

}
