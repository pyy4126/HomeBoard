package com.study.springboot.service;

import java.util.List;

import com.study.springboot.dto.BoardDto;

public interface IBoardService {

	public List<BoardDto> list(String id, int curNum);
	public String num();
	public int plusview(String num);
	public int plusreview(String num);
	public int minusreview(String num);
	//public int pagenum(int cur, int last, String id);
	public int total(String id);
	public BoardDto view(String num);
	public List<BoardDto> searchName(String sch, String what, String id);
	public int rewrite(String title, String content, String bimil, String dd, String num);
	public int write(String id, String writer, String title, String content, String bimil, String dd, String fflag);
	public int delete(String num);
	public int updatefflag(String num);
	public int emptyfile(String num);

}
