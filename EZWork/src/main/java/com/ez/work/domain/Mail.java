package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

public class Mail {
	private int MAIL_NUM, MAIL_RCHECK;
	private String MAIL_SENDER, MAIL_RECIPIENT, M_FILE;
	private String MAIL_SUBJECT, MAIL_CONTENT, MAIL_FILE, MAIL_DATE, MAIL_TYPE;
	
	private MultipartFile uploadfile;
	private String MAIL_ORIGINAL;	//÷�ε� ������ �̸�
	
	
	public String getMAIL_TYPE() {
		return MAIL_TYPE;
	}
	public void setMAIL_TYPE(String mAIL_TYPE) {
		MAIL_TYPE = mAIL_TYPE;
	}
	public int getMAIL_NUM() {
		return MAIL_NUM;
	}
	public void setMAIL_NUM(int mAIL_NUM) {
		MAIL_NUM = mAIL_NUM;
	}
	public int getMAIL_RCHECK() {
		return MAIL_RCHECK;
	}
	public void setMAIL_RCHECK(int mAIL_RCHECK) {
		MAIL_RCHECK = mAIL_RCHECK;
	}
	public String getMAIL_SENDER() {
		return MAIL_SENDER;
	}
	public void setMAIL_SENDER(String mAIL_SENDER) {
		MAIL_SENDER = mAIL_SENDER;
	}
	public String getMAIL_RECIPIENT() {
		return MAIL_RECIPIENT;
	}
	public void setMAIL_RECIPIENT(String mAIL_RECIPIENT) {
		MAIL_RECIPIENT = mAIL_RECIPIENT;
	}
	public String getMAIL_SUBJECT() {
		return MAIL_SUBJECT;
	}
	public void setMAIL_SUBJECT(String mAIL_SUBJECT) {
		MAIL_SUBJECT = mAIL_SUBJECT;
	}
	public String getMAIL_CONTENT() {
		return MAIL_CONTENT;
	}
	public void setMAIL_CONTENT(String mAIL_CONTENT) {
		MAIL_CONTENT = mAIL_CONTENT;
	}
	public String getMAIL_FILE() {
		return MAIL_FILE;
	}
	public void setMAIL_FILE(String mAIL_FILE) {
		MAIL_FILE = mAIL_FILE;
	}
	public String getMAIL_DATE() {
		return MAIL_DATE;
	}
	public void setMAIL_DATE(String mAIL_DATE) {
		MAIL_DATE = mAIL_DATE;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getMAIL_ORIGINAL() {
		return MAIL_ORIGINAL;
	}
	public void setMAIL_ORIGINAL(String mAIL_ORIGINAL) {
		MAIL_ORIGINAL = mAIL_ORIGINAL;
	}
	public String getM_FILE() {
		return M_FILE;
	}
	public void setM_FILE(String m_FILE) {
		M_FILE = m_FILE;
	}	
}
