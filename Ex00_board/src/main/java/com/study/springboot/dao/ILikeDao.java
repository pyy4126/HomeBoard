package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.LikeDto;

@Mapper
public interface ILikeDao {

	public int lcreateDao(String id, String num, String name); // db에 등록되어 있지 않으면 생성
	public LikeDto lchkDao(String id, String num); // db에 한번 등록된 값인지 check
	public int ldeleteDao(String id, String num); // chk값을 0으로
	public int lwriteDao(String id, String num); // chk값을 1으로
	public List<LikeDto> lcntDao(String num); // 현 게시물의 좋아요 개수
	public List<String> llistDao(String num);
	public int deleteallDao(String num); // 게시물 삭제 시 좋아요도 일괄제거
}
