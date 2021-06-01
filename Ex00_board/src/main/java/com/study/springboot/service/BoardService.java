package com.study.springboot.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.IBoardDao;
import com.study.springboot.dto.BoardDto;

@Service
public class BoardService implements IBoardService {

	@Autowired
	IBoardDao dao;
	
	@Autowired
	TransactionTemplate tcTm;
	
	@Override
	public List<BoardDto> list(String id, int curNum) {
		return dao.listDao(id, curNum);
	}

	@Override
	public BoardDto view(String num) {
		return dao.viewDao(num);
	}

	@Override
	public int write(String id, String writer, String title, String content, String bimil, String dd, String fflag) {
		//int rst = dao.writeDao(id, writer, title, content, bimil, dd);
		
		try {
			tcTm.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {

					if(title.trim().length() <= 0 || content.trim().length() <= 0 || dao.writeDao(id, writer, title, content, bimil, dd, fflag) != 1) {
						int n = 10 / 0;
					}
				}
			});
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String num) {
		int rst = dao.deleteDao(num);
		
		return rst;
	}

	@Override
	public List<BoardDto> searchName(String sch, String what, String id) {
		return dao.searchNameDao(sch, what, id);
	}

	@Override
	public int rewrite(String title, String content, String bimil, String dd, String num) {
		//return dao.rewriteDao(title, content, bimil, dd, num);
		
		try {
			tcTm.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {

					if(title.trim().length() <= 0 || content.trim().length() <= 0 || dao.rewriteDao(title, content, bimil, dd, num) != 1) {
						int n = 10 / 0;
					}
				}
			});
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int total(String id) {
		return dao.totalDao(id);
	}

	@Override
	public int plusview(String num) {
		return dao.plusviewDao(num);
	}

	@Override
	public int plusreview(String num) {
		return dao.plusreviewDao(num);
	}

	@Override
	public int minusreview(String num) {
		return dao.minusreviewDao(num);
	}

	@Override
	public String num() {
		return dao.numDao();
	}

	@Override
	public int updatefflag(String num) {
		return dao.updatefflagDao(num);
	}

	@Override
	public int emptyfile(String num) {
		return dao.emptyfileDao(num);
	}


}
