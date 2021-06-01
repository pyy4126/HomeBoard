package com.study.springboot.service;

import java.util.List;

import com.study.springboot.dto.FileDto;

public interface IFileService {

	public int addfile(String num, String id, String name, String simple_name);
	public List<FileDto> filelist(String num, String id);
	public List<FileDto> deletename(String num);
	public int deletefile(String idx);
	public int deleteall(String num);
	public FileDto fileview(String idx);
	public int countfile(String num);
}
