package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.UserDto;

@Mapper
public interface IUserDao {

	public int gaipDao(String name, String id, String pw, String chk);
	public UserDto userLoginDao(String id, String pw);
}
