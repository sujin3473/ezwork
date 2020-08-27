package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

public class NoticeBoard {
	private int NO_NO;			//글 번호
	private String NO_SORT;		//글 말머리
	private String NO_NAME;		//글 작성자
	private String NO_PASS;		//글 비밀번호
	private String NO_TITLE;	//글 제목
	private String NO_CONTENT;	//글 내용
	private String NO_FILE;		//실제 저장된 파일의 이름
	private int NO_RE_REF;		//답변 글 작성 시 참조되는 글의 번호
	private int NO_RE_LEV;		//답변 글의 길이
	private int NO_RE_SEQ;		//답변 글의 순서
	private int NO_READCOUNT;	//글의 조회수
	
	//<input type="file" id="upfile" name="uploadfile"> 확인
	private MultipartFile uploadfile;
	
	private String NO_ORIGINAL; // 첨부된 파일의 이름
	
	private String NO_DATE; //작성날짜

	public int getNO_NO() {
		return NO_NO;
	}

	public void setNO_NO(int nO_NO) {
		NO_NO = nO_NO;
	}

	public String getNO_SORT() {
		return NO_SORT;
	}

	public void setNO_SORT(String nO_SORT) {
		NO_SORT = nO_SORT;
	}

	public String getNO_NAME() {
		return NO_NAME;
	}

	public void setNO_NAME(String nO_NAME) {
		NO_NAME = nO_NAME;
	}

	public String getNO_PASS() {
		return NO_PASS;
	}

	public void setNO_PASS(String nO_PASS) {
		NO_PASS = nO_PASS;
	}

	public String getNO_TITLE() {
		return NO_TITLE;
	}

	public void setNO_TITLE(String nO_TITLE) {
		NO_TITLE = nO_TITLE;
	}

	public String getNO_CONTENT() {
		return NO_CONTENT;
	}

	public void setNO_CONTENT(String nO_CONTENT) {
		NO_CONTENT = nO_CONTENT;
	}

	public String getNO_FILE() {
		return NO_FILE;
	}

	public void setNO_FILE(String nO_FILE) {
		NO_FILE = nO_FILE;
	}

	public int getNO_RE_REF() {
		return NO_RE_REF;
	}

	public void setNO_RE_REF(int nO_RE_REF) {
		NO_RE_REF = nO_RE_REF;
	}

	public int getNO_RE_LEV() {
		return NO_RE_LEV;
	}

	public void setNO_RE_LEV(int nO_RE_LEV) {
		NO_RE_LEV = nO_RE_LEV;
	}

	public int getNO_RE_SEQ() {
		return NO_RE_SEQ;
	}

	public void setNO_RE_SEQ(int nO_RE_SEQ) {
		NO_RE_SEQ = nO_RE_SEQ;
	}

	public int getNO_READCOUNT() {
		return NO_READCOUNT;
	}

	public void setNO_READCOUNT(int nO_READCOUNT) {
		NO_READCOUNT = nO_READCOUNT;
	}

	public String getNO_ORIGINAL() {
		return NO_ORIGINAL;
	}

	public void setNO_ORIGINAL(String nO_ORIGINAL) {
		NO_ORIGINAL = nO_ORIGINAL;
	}

	public String getNO_DATE() {
		return NO_DATE;
	}

	public void setNO_DATE(String nO_DATE) {
		NO_DATE = nO_DATE;
	}
	

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}


}
