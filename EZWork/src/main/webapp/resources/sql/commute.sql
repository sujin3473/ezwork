CREATE TABLE COMMUTE(
CM_CODE VARCHAR2(20), 					--근태코드 // 그날 날짜 20200713
CM_TEAMNAME VARCHAR2(20), 					--팀명
CM_MCODE VARCHAR2(20),						--사원번호 
CM_NAME VARCHAR2(15),								--성명
CM_ONTIME VARCHAR2(10),										--근무시작시간 17:00 ->1700
CM_OFFTIME VARCHAR2(10),									--근무종료시간 18:00 -> 1800
CM_DATE DATE,										--등록날짜시간
CM_OFFDATE DATE										--퇴근날짜시간
);										


DROP TABLE COMMUTE;

select * from commute;

DELETE FROM COMMUTE WHERE CM_CODE='20200719';





