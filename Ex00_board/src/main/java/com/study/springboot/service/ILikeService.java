package com.study.springboot.service;

import java.util.List;

import com.study.springboot.dto.LikeDto;

public interface ILikeService {

	public int lcreate(String id, String num, String name); // db에 등록되어 있지 않으면 생성
	public LikeDto lchk(String id, String num); // db에 한번 등록된 값인지 check
	public int ldelete(String id, String num); // chk값을 0으로
	public int lwrite(String id, String num); // chk값을 1으로
	public List<LikeDto> lcnt(String num);
	public List<String> llist(String num);
	public int deleteall(String num);
}
