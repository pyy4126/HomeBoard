package com.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.IReviewDao;
import com.study.springboot.dto.ReviewDto;


@Service
public class ReviewService implements IReviewService {

	@Autowired
	IReviewDao dao;
	
	@Autowired
	TransactionTemplate tcTm;
	
	@Override
	public List<ReviewDto> review(String num) {
		return dao.reviewDao(num);
	}

	@Override
	public int reviewWrite(String num, String id, String writer, String coment, String dd) {
		try {
			tcTm.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {

					if(coment.trim().length() == 0 || dao.reviewWriteDao(num, id, writer, coment, dd) != 1) {
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
	public int reviewdelete(String idx) {
		return dao.reviewdeleteDao(idx);
	}

	@Override
	public int deleteall(String num) {
		return dao.deleteallDao(num);
	}


}
