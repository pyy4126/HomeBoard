package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.FileDto;

@Mapper
public interface IFileDao {

	public int addfileDao(String num, String id, String name, String simple_name);
	public List<FileDto> filelistDao(String num, String id);
	public List<FileDto> deletenameDao(String num);
	public int deletefileDao(String idx);
	public int deleteallDao(String num);
	public FileDto fileviewDao(String idx);
	public int countfileDao(String num);
}
