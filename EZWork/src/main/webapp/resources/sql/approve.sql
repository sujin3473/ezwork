drop table approve;

drop sequence appr_seq;
create sequence appr_seq
start with 5;

create table approve(
	APPR_CODE		NUMBER			PRIMARY KEY,	--결재 번호
	M_CODE			VARCHAR2(50),					--사원 코드(id)
	APPR_NAME		VARCHAR2(50),
	M_PART			VARCHAR2(30),
	APPR_TITLE		VARCHAR2(200),					--제목
	APPR_CONTENT	VARCHAR2(1000),					--내용
	FIRST_CODE		VARCHAR2(50),					--1-사원
	M_PART_F		VARCHAR2(30),
	FIRST_COMMENT	VARCHAR2(500),
	FIRST_VAL		NUMBER,
	SECOND_CODE		VARCHAR2(50),					--2-사원
	M_PART_S		VARCHAR2(30),
	SECOND_COMMENT	VARCHAR2(500),
	SECOND_VAL		NUMBER,
	THIRD_CODE		VARCHAR2(50),					--3-사원
	M_PART_T		VARCHAR2(30),
	THIRD_COMMENT	VARCHAR2(500),
	THIRD_VAL		NUMBER,
	APPR_FILE		VARCHAR2(500),					--변경된 파일명
	APPR_ORIGIN		VARCHAR2(500),					--original 파일명
	APPR_STAT		NUMBER,							--결재 상태
	APPR_DATE		date,							--제출 날짜
	APPR_COMP_DATE	date,							--완료 날짜
	APPR_VAL		NUMBER,							--??
	APPR_COUNT		NUMBER default 0,							--결재자 인원수
	APPR_CUR_COUNT	NUMBER							--결재 진행 상태
);

select * from approve;
