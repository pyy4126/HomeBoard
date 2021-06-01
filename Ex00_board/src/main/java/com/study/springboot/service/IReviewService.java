package com.study.springboot.service;

import java.util.List;

import com.study.springboot.dto.ReviewDto;

public interface IReviewService {

	public List<ReviewDto> review(String num);
	public int reviewWrite(String num, String id, String writer, String coment, String dd);
	public int reviewdelete(String idx);
	public int deleteall(String num);
}
