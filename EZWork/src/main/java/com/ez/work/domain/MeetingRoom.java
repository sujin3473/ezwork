package com.ez.work.domain;

public class MeetingRoom {
	private int MNUM;
	private String RNAME, USER_TEL, MTIME, USER_NAME, MSUBJECT;
	
	public String getMSUBJECT() {
		return MSUBJECT;
	}
	public void setMSUBJECT(String mSUBJECT) {
		MSUBJECT = mSUBJECT;
	}
	public int getMNUM() {
		return MNUM;
	}
	public void setMNUM(int mNUM) {
		MNUM = mNUM;
	}
	public String getRNAME() {
		return RNAME;
	}
	public void setRNAME(String rNAME) {
		RNAME = rNAME;
	}
	public String getUSER_TEL() {
		return USER_TEL;
	}
	public void setUSER_TEL(String uSER_TEL) {
		USER_TEL = uSER_TEL;
	}
	public String getMTIME() {
		return MTIME;
	}
	public void setMTIME(String mTIME) {
		MTIME = mTIME;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
}
