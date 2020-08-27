package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

public class EventBoard {
	private int EV_NO;			//글 번호
	private String EV_SORT;		//글 말머리
	private String EV_NAME;		//글 작성자
	private String EV_PASS;		//글 비밀번호
	private String EV_TITLE;	//글 제목
	private String EV_CONTENT;	//글 내용
	private String EV_FILE;		//실제 저장된 파일의 이름
	private int EV_RE_REF;		//답변 글 작성 시 참조되는 글의 번호
	private int EV_RE_LEV;		//답변 글의 길이
	private int EV_RE_SEQ;		//답변 글의 순서
	private int EV_READCOUNT;	//글의 조회수
	
	//<input type="file" id="upfile" name="uploadfile"> 확인
	private MultipartFile uploadfile;
	
	private String EV_ORIGINAL; // 첨부된 파일의 이름
	
	private String EV_DATE; //작성날짜

	public int getEV_NO() {
		return EV_NO;
	}

	public void setEV_NO(int eV_NO) {
		EV_NO = eV_NO;
	}

	public String getEV_SORT() {
		return EV_SORT;
	}

	public void setEV_SORT(String eV_SORT) {
		EV_SORT = eV_SORT;
	}

	public String getEV_NAME() {
		return EV_NAME;
	}

	public void setEV_NAME(String eV_NAME) {
		EV_NAME = eV_NAME;
	}

	public String getEV_PASS() {
		return EV_PASS;
	}

	public void setEV_PASS(String eV_PASS) {
		EV_PASS = eV_PASS;
	}

	public String getEV_TITLE() {
		return EV_TITLE;
	}

	public void setEV_TITLE(String eV_TITLE) {
		EV_TITLE = eV_TITLE;
	}

	public String getEV_CONTENT() {
		return EV_CONTENT;
	}

	public void setEV_CONTENT(String eV_CONTENT) {
		EV_CONTENT = eV_CONTENT;
	}

	public String getEV_FILE() {
		return EV_FILE;
	}

	public void setEV_FILE(String eV_FILE) {
		EV_FILE = eV_FILE;
	}

	public int getEV_RE_REF() {
		return EV_RE_REF;
	}

	public void setEV_RE_REF(int eV_RE_REF) {
		EV_RE_REF = eV_RE_REF;
	}

	public int getEV_RE_LEV() {
		return EV_RE_LEV;
	}

	public void setEV_RE_LEV(int eV_RE_LEV) {
		EV_RE_LEV = eV_RE_LEV;
	}

	public int getEV_RE_SEQ() {
		return EV_RE_SEQ;
	}

	public void setEV_RE_SEQ(int eV_RE_SEQ) {
		EV_RE_SEQ = eV_RE_SEQ;
	}

	public int getEV_READCOUNT() {
		return EV_READCOUNT;
	}

	public void setEV_READCOUNT(int eV_READCOUNT) {
		EV_READCOUNT = eV_READCOUNT;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getEV_ORIGINAL() {
		return EV_ORIGINAL;
	}

	public void setEV_ORIGINAL(String eV_ORIGINAL) {
		EV_ORIGINAL = eV_ORIGINAL;
	}

	public String getEV_DATE() {
		return EV_DATE.substring(0,10);
	}

	public void setEV_DATE(String eV_DATE) {
		EV_DATE = eV_DATE;
	}


}
