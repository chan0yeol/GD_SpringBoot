package com.min.edu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVo {
	private String id, title, content, regdate, delflag;
	private int seq, ref, step, depth;

}