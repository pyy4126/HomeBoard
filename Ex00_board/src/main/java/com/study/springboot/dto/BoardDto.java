package com.study.springboot.dto;


import lombok.Data;

@Data
public class BoardDto{

	private String id; // 계정과 연결할 외래키
	private String dd;
	private String fflag;
	private int review_cnt;
	private int view;
	private int num;
	private String writer;
	private String title;
	private String content;
	private String bimil;
}
