package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.ReviewDto;

@Mapper
public interface IReviewDao {

	public List<ReviewDto> reviewDao(String num);
	public int deleteallDao(String num); // 게시물 제거시 댓글 일괄제거
	public int reviewdeleteDao(String idx);
	public int reviewWriteDao(String num, String id, String writer, String coment, String dd);
}
