package com.ez.work.domain;

import org.springframework.web.multipart.MultipartFile;

//혜정, 민혁
public class Member {
	private String M_CODE; // 사원정보
	private String M_PASS; // 비밀번호
	private String M_FILE; // 증명사진
	private String M_OWNER;  //로그인 id(민혁)
	private String M_ORIGINAL; // 증명사진 가공할 이름
	private String M_PART_C; // 부서코드
	private int M_LEVEL; // 직급코드
	private String M_NAME; // 성명
	private String M_GENDER; // 성별
	private String M_BIRTH; // 생년월일
	private String M_ARMY; // 군필여부
	private String M_MARRIAGE; // 결혼여부
	private String M_MOBILE_TEL; // 핸드폰
	private String M_EMAIL; // 이메일
	private String M_ADDRESS; // 자택주소
	private String M_JOIN_DATE; // 입사날짜
	private int M_BOOKMARK; // 즐겨찾기(민혁)
	private String SC_NAME; // 학교명
	private String SC_MAJOR; // 전공
	private String SC_DEGREE; // 학위
	private String SC_ADDRESS; // 소재지
	private String SC_GRAD_DAY; // 졸업일자
	private String LG_NAME; // 외국어명
	private String LG_TEST; // 시험명
	private String LG_GRADE; // 시험점수
	private String LG_ORGAN; // 발급처
	private String LG_DATE; // 발급일
	private String LI_NAME; // 자격증 명
	private String LI_ORGAN; // 발급기관
	private String LI_DATE; // 발급일자
	private MultipartFile profile_avatar;
	private String M_POSTCODE;
	private String M_ADDRESS2;
	private String RES_DATE;

	public String getRES_DATE() {
		return RES_DATE;
	}

	public void setRES_DATE(String rES_DATE) {
		RES_DATE = rES_DATE;
	}

	public String getM_OWNER() {
		return M_OWNER;
	}

	public void setM_OWNER(String m_OWNER) {
		M_OWNER = m_OWNER;
	}

	public String getM_POSTCODE() {
		return M_POSTCODE;
	}

	public void setM_POSTCODE(String m_POSTCODE) {
		M_POSTCODE = m_POSTCODE;
	}

	public String getM_ADDRESS2() {
		return M_ADDRESS2;
	}

	public void setM_ADDRESS2(String m_ADDRESS2) {
		M_ADDRESS2 = m_ADDRESS2;
	}

	public String getM_CODE() {
		return M_CODE;
	}

	public void setM_CODE(String m_CODE) {
		M_CODE = m_CODE;
	}

	public String getM_PASS() {
		return M_PASS;
	}

	public void setM_PASS(String m_PASS) {
		M_PASS = m_PASS;
	}

	public String getM_FILE() {
		return M_FILE;
	}

	public void setM_FILE(String m_FILE) {
		M_FILE = m_FILE;
	}

	public String getM_ORIGINAL() {
		return M_ORIGINAL;
	}

	public void setM_ORIGINAL(String m_ORIGINAL) {
		M_ORIGINAL = m_ORIGINAL;
	}

	public String getM_PART_C() {
		return M_PART_C;
	}

	public void setM_PART_C(String m_PART_C) {
		M_PART_C = m_PART_C;
	}

	public int getM_LEVEL() {
		return M_LEVEL;
	}

	public void setM_LEVEL(int m_LEVEL) {
		M_LEVEL = m_LEVEL;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getM_GENDER() {
		return M_GENDER;
	}

	public void setM_GENDER(String m_GENDER) {
		M_GENDER = m_GENDER;
	}

	public String getM_BIRTH() {
		return M_BIRTH;
	}

	public void setM_BIRTH(String m_BIRTH) {
		M_BIRTH = m_BIRTH;
	}

	public String getM_ARMY() {
		return M_ARMY;
	}

	public void setM_ARMY(String m_ARMY) {
		M_ARMY = m_ARMY;
	}

	public String getM_MARRIAGE() {
		return M_MARRIAGE;
	}

	public void setM_MARRIAGE(String m_MARRIAGE) {
		M_MARRIAGE = m_MARRIAGE;
	}

	public String getM_MOBILE_TEL() {
		return M_MOBILE_TEL;
	}

	public void setM_MOBILE_TEL(String m_MOBILE_TEL) {
		M_MOBILE_TEL = m_MOBILE_TEL;
	}

	public String getM_EMAIL() {
		return M_EMAIL;
	}

	public void setM_EMAIL(String m_EMAIL) {
		M_EMAIL = m_EMAIL;
	}

	public String getM_ADDRESS() {
		return M_ADDRESS;
	}

	public void setM_ADDRESS(String m_ADDRESS) {
		M_ADDRESS = m_ADDRESS;
	}

	public int getM_BOOKMARK() {
		return M_BOOKMARK;
	}

	public void setM_BOOKMARK(int m_BOOKMARK) {
		M_BOOKMARK = m_BOOKMARK;
	}

	public String getM_JOIN_DATE() {
		return M_JOIN_DATE.substring(0,10);
	}

	public void setM_JOIN_DATE(String m_JOIN_DATE) {
		M_JOIN_DATE = m_JOIN_DATE;
	}

	public String getSC_NAME() {
		return SC_NAME;
	}

	public void setSC_NAME(String sC_NAME) {
		SC_NAME = sC_NAME;
	}

	public String getSC_MAJOR() {
		return SC_MAJOR;
	}

	public void setSC_MAJOR(String sC_MAJOR) {
		SC_MAJOR = sC_MAJOR;
	}

	public String getSC_DEGREE() {
		return SC_DEGREE;
	}

	public void setSC_DEGREE(String sC_DEGREE) {
		SC_DEGREE = sC_DEGREE;
	}

	public String getSC_ADDRESS() {
		return SC_ADDRESS;
	}

	public void setSC_ADDRESS(String sC_ADDRESS) {
		SC_ADDRESS = sC_ADDRESS;
	}

	public String getSC_GRAD_DAY() {
		return SC_GRAD_DAY;
	}

	public void setSC_GRAD_DAY(String sC_GRAD_DAY) {
		SC_GRAD_DAY = sC_GRAD_DAY;
	}

	public String getLG_NAME() {
		return LG_NAME;
	}

	public void setLG_NAME(String lG_NAME) {
		LG_NAME = lG_NAME;
	}

	public String getLG_TEST() {
		return LG_TEST;
	}

	public void setLG_TEST(String lG_TEST) {
		LG_TEST = lG_TEST;
	}

	public String getLG_GRADE() {
		return LG_GRADE;
	}

	public void setLG_GRADE(String lG_GRADE) {
		LG_GRADE = lG_GRADE;
	}

	public String getLG_ORGAN() {
		return LG_ORGAN;
	}

	public void setLG_ORGAN(String lG_ORGAN) {
		LG_ORGAN = lG_ORGAN;
	}

	public String getLG_DATE() {
		return LG_DATE;
	}

	public void setLG_DATE(String lG_DATE) {
		LG_DATE = lG_DATE;
	}

	public String getLI_NAME() {
		return LI_NAME;
	}

	public void setLI_NAME(String lI_NAME) {
		LI_NAME = lI_NAME;
	}

	public String getLI_ORGAN() {
		return LI_ORGAN;
	}

	public void setLI_ORGAN(String lI_ORGAN) {
		LI_ORGAN = lI_ORGAN;
	}

	public String getLI_DATE() {
		return LI_DATE;
	}

	public void setLI_DATE(String lI_DATE) {
		LI_DATE = lI_DATE;
	}

	public MultipartFile getProfile_avatar() {
		return profile_avatar;
	}

	public void setProfile_avatar(MultipartFile profile_avatar) {
		this.profile_avatar = profile_avatar;
	}

	// qna_board_write.jsp에서 name 속성 확인하세요
	// <input type="file" id="upfile" name="profile_avatar"> 확인

}
