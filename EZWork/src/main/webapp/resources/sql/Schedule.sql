DROP TABLE SCHEDULE;

CREATE TABLE SCHEDULE(
SCH_NO NUMBER,
SCH_NAME VARCHAR2(30), --등록한사람
SCH_TITLE VARCHAR2(100),
SCH_STARTDATE VARCHAR2(30),
SCH_ENDDATE VARCHAR2(30),
SCH_PARTICIPANT VARCHAR2(200),
SCH_PLACE VARCHAR2(100),
SCH_COLOR VARCHAR2(40),
SCH_CONTENT VARCHAR2(500)
);

SELECT * FROM SCHEDULE;

CREATE SEQUENCE SCHE_SEQ 
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;