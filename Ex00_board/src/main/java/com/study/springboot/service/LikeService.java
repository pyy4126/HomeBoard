package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ILikeDao;
import com.study.springboot.dto.LikeDto;


@Service
public class LikeService implements ILikeService {

	@Autowired
	ILikeDao dao;
	
	@Override
	public int lcreate(String id, String num, String name) {
		return dao.lcreateDao(id, num, name);
	}

	@Override
	public LikeDto lchk(String id, String num) {
		return dao.lchkDao(id, num);
	}

	@Override
	public int ldelete(String id, String num) {
		return dao.ldeleteDao(id, num);
	}

	@Override
	public int lwrite(String id, String num) {
		return dao.lwriteDao(id, num);
	}

	@Override
	public List<LikeDto> lcnt(String num) {
		return dao.lcntDao(num);
	}

	@Override
	public List<String> llist(String num) {
		return dao.llistDao(num);
	}

	@Override
	public int deleteall(String num) {
		return dao.deleteallDao(num);
	}

}
