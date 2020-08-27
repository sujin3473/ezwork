package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

public class Appr {
	
	private int APPR_CODE;                          //결재 번호         
	private String M_CODE;                          //사원 코드(id)
	private String M_PART;
	private String APPR_NAME;	
	private String APPR_TITLE;                      //제목            
	private String APPR_CONTENT;                    //내용            
	private String FIRST_CODE;                      //1-사원
	private String M_PART_F; 
	private int FIRST_VAL;
	private String FIRST_COMMENT;
	private String SECOND_CODE;                     //2-사원
	private String M_PART_S; 
	private int SECOND_VAL;
	private String SECOND_COMMENT;	
	private String THIRD_CODE;                      //3-사원
	private String M_PART_T; 	
	private int THIRD_VAL;
	private String THIRD_COMMENT;
	private String APPR_FILE;                       //변경된 파일명       
	private String APPR_ORIGIN;                     //original 파일명  
	private int APPR_STAT;                               //결재 상태         
	private String APPR_DATE;                       //제출 날짜         
	private String APPR_COMP_DATE;                  //완료 날짜         
	private int APPR_VAL;    
	private MultipartFile uploadfile;    
	private int APPR_COUNT;
	private int APPR_CUR_COUNT;
	
	
	public int getAPPR_COUNT() {
		return APPR_COUNT;
	}
	public void setAPPR_COUNT(int aPPR_COUNT) {
		APPR_COUNT = aPPR_COUNT;
	}
	public int getAPPR_CUR_COUNT() {
		return APPR_CUR_COUNT;
	}
	public void setAPPR_CUR_COUNT(int aPPR_CUR_COUNT) {
		APPR_CUR_COUNT = aPPR_CUR_COUNT;
	}
	public int getFIRST_VAL() {
		return FIRST_VAL;
	}
	public void setFIRST_VAL(int fIRST_VAL) {
		FIRST_VAL = fIRST_VAL;
	}
	public String getFIRST_COMMENT() {
		return FIRST_COMMENT;
	}
	public void setFIRST_COMMENT(String fIRST_COMMENT) {
		FIRST_COMMENT = fIRST_COMMENT;
	}
	public int getSECOND_VAL() {
		return SECOND_VAL;
	}
	public void setSECOND_VAL(int sECOND_VAL) {
		SECOND_VAL = sECOND_VAL;
	}
	public String getSECOND_COMMENT() {
		return SECOND_COMMENT;
	}
	public void setSECOND_COMMENT(String sECOND_COMMENT) {
		SECOND_COMMENT = sECOND_COMMENT;
	}
	public int getTHIRD_VAL() {
		return THIRD_VAL;
	}
	public void setTHIRD_VAL(int tHIRD_VAL) {
		THIRD_VAL = tHIRD_VAL;
	}
	public String getTHIRD_COMMENT() {
		return THIRD_COMMENT;
	}
	public void setTHIRD_COMMENT(String tHIRD_COMMENT) {
		THIRD_COMMENT = tHIRD_COMMENT;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getM_PART_T() {
		return M_PART_T;
	}
	public void setM_PART_T(String m_PART_T) {
		M_PART_T = m_PART_T;
	}
	public int getAPPR_CODE() {
		return APPR_CODE;
	}
	public void setAPPR_CODE(int aPPR_CODE) {
		APPR_CODE = aPPR_CODE;
	}
	public String getM_CODE() {
		return M_CODE;
	}
	public void setM_CODE(String m_CODE) {
		M_CODE = m_CODE;
	}
	public String getAPPR_TITLE() {
		return APPR_TITLE;
	}
	public void setAPPR_TITLE(String aPPR_TITLE) {
		APPR_TITLE = aPPR_TITLE;
	}
	public String getAPPR_CONTENT() {
		return APPR_CONTENT;
	}
	public void setAPPR_CONTENT(String aPPR_CONTENT) {
		APPR_CONTENT = aPPR_CONTENT;
	}
	public String getFIRST_CODE() {
		return FIRST_CODE;
	}
	public void setFIRST_CODE(String fIRST_CODE) {
		FIRST_CODE = fIRST_CODE;
	}
	public String getSECOND_CODE() {
		return SECOND_CODE;
	}
	public void setSECOND_CODE(String sECOND_CODE) {
		SECOND_CODE = sECOND_CODE;
	}
	public String getTHIRD_CODE() {
		return THIRD_CODE;
	}
	public void setTHIRD_CODE(String tHIRD_CODE) {
		THIRD_CODE = tHIRD_CODE;
	}
	public String getAPPR_FILE() {
		return APPR_FILE;
	}
	public void setAPPR_FILE(String aPPR_FILE) {
		APPR_FILE = aPPR_FILE;
	}
	public String getAPPR_ORIGIN() {
		return APPR_ORIGIN;
	}
	public void setAPPR_ORIGIN(String aPPR_ORIGIN) {
		APPR_ORIGIN = aPPR_ORIGIN;
	}
	
	public String getAPPR_DATE() {
		return APPR_DATE;
	}
	public void setAPPR_DATE(String aPPR_DATE) {
		APPR_DATE = aPPR_DATE;
	}
	public String getAPPR_COMP_DATE() {
		return APPR_COMP_DATE;
	}
	public void setAPPR_COMP_DATE(String aPPR_COMP_DATE) {
		APPR_COMP_DATE = aPPR_COMP_DATE;
	}
	public int getAPPR_VAL() {
		return APPR_VAL;
	}
	public void setAPPR_VAL(int aPPR_VAL) {
		APPR_VAL = aPPR_VAL;
	}
	public int getAPPR_STAT() {
		return APPR_STAT;
	}
	public void setAPPR_STAT(int aPPR_STAT) {
		APPR_STAT = aPPR_STAT;
	}
	public String getM_PART() {
		return M_PART;
	}
	public void setM_PART(String m_PART) {
		M_PART = m_PART;
	}
	public String getM_PART_F() {
		return M_PART_F;
	}
	public void setM_PART_F(String m_PART_F) {
		M_PART_F = m_PART_F;
	}
	public String getM_PART_S() {
		return M_PART_S;
	}
	public void setM_PART_S(String m_PART_S) {
		M_PART_S = m_PART_S;
	}
	public String getAPPR_NAME() {
		return APPR_NAME;
	}
	public void setAPPR_NAME(String aPPR_NAME) {
		APPR_NAME = aPPR_NAME;
	}
}
