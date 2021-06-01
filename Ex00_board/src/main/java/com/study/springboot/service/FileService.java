package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.IFileDao;
import com.study.springboot.dto.FileDto;

@Service
public class FileService implements IFileService {

	@Autowired
	IFileDao dao;
	
	@Override
	public int addfile(String num, String id, String name, String simple_name) {
		return dao.addfileDao(num, id, name, simple_name);
	}

	@Override
	public List<FileDto> filelist(String num, String id) {
		return dao.filelistDao(num, id);
	}

	@Override
	public int deletefile(String idx) {
		return dao.deletefileDao(idx);
	}

	@Override
	public FileDto fileview(String idx) {
		return dao.fileviewDao(idx);
	}

	@Override
	public int countfile(String num) {
		return dao.countfileDao(num);
	}

	@Override
	public int deleteall(String num) {
		return dao.deleteallDao(num);
	}

	@Override
	public List<FileDto> deletename(String num) {
		return dao.deletenameDao(num);
	}

}
