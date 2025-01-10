package com.min.edu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.min.edu.model.IBoardDao;
import com.min.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringBoot3MyBatisXmlApplicationTests {

	// TODO 008 작성된 myBatis를 실행하여 결과 확인
	@Autowired
	private IBoardDao dao;
	
	@Test
	void contextLoads() {
		List<BoardVo> lists = dao.selectBoard();
		log.info("lists  : {}", lists);
		assertThat(lists.size()).isNotEqualTo(0);
	}

}
