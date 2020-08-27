package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

public class Pds {
	private int PDS_NO;			//글 번호
	private String PDS_SORT;		//글 말머리
	private String PDS_NAME;		//글 작성자
	private String PDS_PASS;		//글 비밀번호
	private String PDS_TITLE;	//글 제목
	private String PDS_CONTENT;	//글 내용
	private String PDS_FILE;		//실제 저장된 파일의 이름
	private int PDS_RE_REF;		//답변 글 작성 시 참조되는 글의 번호
	private int PDS_RE_LEV;		//답변 글의 길이
	private int PDS_RE_SEQ;		//답변 글의 순서
	private int PDS_READCOUNT;	//글의 조회수
	//<input type="file" id="upfile" name="uploadfile"> 확인
	private MultipartFile uploadfile;
	private String PDS_ORIGINAL; // 첨부된 파일의 이름
	private String PDS_DATE; //작성날짜
	
	public int getPDS_NO() {
		return PDS_NO;
	}
	public void setPDS_NO(int pDS_NO) {
		PDS_NO = pDS_NO;
	}
	public String getPDS_SORT() {
		return PDS_SORT;
	}
	public void setPDS_SORT(String pDS_SORT) {
		PDS_SORT = pDS_SORT;
	}
	public String getPDS_NAME() {
		return PDS_NAME;
	}
	public void setPDS_NAME(String pDS_NAME) {
		PDS_NAME = pDS_NAME;
	}
	public String getPDS_PASS() {
		return PDS_PASS;
	}
	public void setPDS_PASS(String pDS_PASS) {
		PDS_PASS = pDS_PASS;
	}
	public String getPDS_TITLE() {
		return PDS_TITLE;
	}
	public void setPDS_TITLE(String pDS_TITLE) {
		PDS_TITLE = pDS_TITLE;
	}
	public String getPDS_CONTENT() {
		return PDS_CONTENT;
	}
	public void setPDS_CONTENT(String pDS_CONTENT) {
		PDS_CONTENT = pDS_CONTENT;
	}
	public String getPDS_FILE() {
		return PDS_FILE;
	}
	public void setPDS_FILE(String pDS_FILE) {
		PDS_FILE = pDS_FILE;
	}
	public int getPDS_RE_REF() {
		return PDS_RE_REF;
	}
	public void setPDS_RE_REF(int pDS_RE_REF) {
		PDS_RE_REF = pDS_RE_REF;
	}
	public int getPDS_RE_LEV() {
		return PDS_RE_LEV;
	}
	public void setPDS_RE_LEV(int pDS_RE_LEV) {
		PDS_RE_LEV = pDS_RE_LEV;
	}
	public int getPDS_RE_SEQ() {
		return PDS_RE_SEQ;
	}
	public void setPDS_RE_SEQ(int pDS_RE_SEQ) {
		PDS_RE_SEQ = pDS_RE_SEQ;
	}
	public int getPDS_READCOUNT() {
		return PDS_READCOUNT;
	}
	public void setPDS_READCOUNT(int pDS_READCOUNT) {
		PDS_READCOUNT = pDS_READCOUNT;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getPDS_ORIGINAL() {
		return PDS_ORIGINAL;
	}
	public void setPDS_ORIGINAL(String pDS_ORIGINAL) {
		PDS_ORIGINAL = pDS_ORIGINAL;
	}
	public String getPDS_DATE() {
		return PDS_DATE.substring(0,10);
	}
	public void setPDS_DATE(String pDS_DATE) {
		PDS_DATE = pDS_DATE;
	}
}
