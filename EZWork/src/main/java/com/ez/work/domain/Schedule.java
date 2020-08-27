package com.ez.work.domain;

public class Schedule {
	private String SCH_TITLE, SCH_NAME, SCH_STARTDATE, SCH_ENDDATE,
	SCH_PARTICIPANT, SCH_PLACE, SCH_COLOR, SCH_CONTENT;
	
	private int SCH_NO;

	public String getSCH_NAME() {
		return SCH_NAME;
	}

	public int getSCH_NO() {
		return SCH_NO;
	}

	public void setSCH_NO(int sCH_NO) {
		SCH_NO = sCH_NO;
	}

	public void setSCH_NAME(String sCH_NAME) {
		SCH_NAME = sCH_NAME;
	}

	public String getSCH_TITLE() {
		return SCH_TITLE;
	}

	public void setSCH_TITLE(String sCH_TITLE) {
		SCH_TITLE = sCH_TITLE;
	}

	public String getSCH_STARTDATE() {
		return SCH_STARTDATE;
	}

	public void setSCH_STARTDATE(String sCH_STARTDATE) {
		SCH_STARTDATE = sCH_STARTDATE;
	}

	public String getSCH_ENDDATE() {
		return SCH_ENDDATE;
	}

	public void setSCH_ENDDATE(String sCH_ENDDATE) {
		SCH_ENDDATE = sCH_ENDDATE;
	}

	public String getSCH_PARTICIPANT() {
		return SCH_PARTICIPANT;
	}

	public void setSCH_PARTICIPANT(String sCH_PARTICIPANT) {
		SCH_PARTICIPANT = sCH_PARTICIPANT;
	}

	public String getSCH_PLACE() {
		return SCH_PLACE;
	}

	public void setSCH_PLACE(String sCH_PLACE) {
		SCH_PLACE = sCH_PLACE;
	}

	public String getSCH_COLOR() {
		return SCH_COLOR;
	}

	public void setSCH_COLOR(String sCH_COLOR) {
		SCH_COLOR = sCH_COLOR;
	}

	public String getSCH_CONTENT() {
		return SCH_CONTENT;
	}

	public void setSCH_CONTENT(String sCH_CONTENT) {
		SCH_CONTENT = sCH_CONTENT;
	}

}
