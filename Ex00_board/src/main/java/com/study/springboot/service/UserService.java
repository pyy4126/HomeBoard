package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.IUserDao;
import com.study.springboot.dto.UserDto;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDao dao;
	
	@Autowired
	TransactionTemplate tcTm;
	
	@Override
	public int gaip(String name, String id, String pw, String chk) {
		
		try {
			tcTm.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {

					if(!pw.equals(chk)) {
						int g[] = {0};
						int s = g[2];
					}
					if(name.trim().length() <= 1 || id.trim().length() <= 3 || pw.trim().length() <= 3 || dao.gaipDao(name, id, pw, chk) != 1) {
						int n = 10 / 0;
					}
				}
			});
			return 1;
		}catch(DuplicateKeyException e) {
			return -1;
		}catch(ArithmeticException e) {
			return 0;
		}catch(IndexOutOfBoundsException e) {
			return -2;
		}

	}

	@Override
	public UserDto userLogin(String id, String pw) {
		return dao.userLoginDao(id, pw);
	}

}
