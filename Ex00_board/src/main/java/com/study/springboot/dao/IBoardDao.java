package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.BoardDto;

@Mapper
public interface IBoardDao {

	public List<BoardDto> listDao(String id, int curNum);
	public String numDao();
	public int plusreviewDao(String num);
	public int minusreviewDao(String num);
	public int plusviewDao(String num);
	public int totalDao(String id);
	public int stotalDao(String sch, String what);
	public BoardDto viewDao(String num);
	public List<BoardDto> searchNameDao(String sch, String what, String id);
	public int rewriteDao(String title, String content, String bimil, String dd, String num);
	public int writeDao(String id, String writer, String title, String content, String bimil, String dd, String fflag);
	public int deleteDao(String num);
	public int updatefflagDao(String num);
	public int emptyfileDao(String num);
}
