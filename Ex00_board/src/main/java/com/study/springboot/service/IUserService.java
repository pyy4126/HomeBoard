package com.study.springboot.service;

import com.study.springboot.dto.UserDto;

public interface IUserService {

	public int gaip(String name, String id, String pw, String chk);
	public UserDto userLogin(String id, String pw);
}
