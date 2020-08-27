drop table RECEIPTMAIL;
drop table SENTMAIL;

create table RECEIPTMAIL(
	MAIL_NUM		NUMBER,
	MAIL_SENDER  	VARCHAR2(30),
	M_FILE		  	VARCHAR2(100),
	MAIL_RECIPIENT	VARCHAR2(30),
	MAIL_SUBJECT	VARCHAR2(300),
	MAIL_CONTENT    VARCHAR2(4000),
	MAIL_FILE       VARCHAR2(50),
	MAIL_ORIGINAL	VARCHAR2(50),
	MAIL_RCHECK	    NUMBER,
	MAIL_DATE		DATE,
	MAIL_TYPE		VARCHAR2(10),
	PRIMARY KEY(MAIL_NUM)
);

create table SENTMAIL(
	MAIL_NUM		NUMBER,
	MAIL_SENDER  	VARCHAR2(30),
	M_FILE		  	VARCHAR2(100),
	MAIL_RECIPIENT	VARCHAR2(30),
	MAIL_SUBJECT	VARCHAR2(300),
	MAIL_CONTENT    VARCHAR2(4000),
	MAIL_FILE       VARCHAR2(50),
	MAIL_ORIGINAL	VARCHAR2(50),
	MAIL_RCHECK	    NUMBER,
	MAIL_DATE		DATE,
	MAIL_TYPE		VARCHAR2(10),
	PRIMARY KEY(MAIL_NUM)
);

insert into RECEIPTMAIL
	(MAIL_NUM, MAIL_SENDER, MAIL_RECIPIENT, MAIL_SUBJECT,
	   MAIL_CONTENT, MAIL_FILE, MAIL_ORIGINAL,
	   MAIL_RCHECK, MAIL_TYPE, MAIL_DATE)
	 values(1, 'admin', 'admin', '첫번째 메일', 'ㅎㅇ',
	 null, null, 0, 'in', sysdate);	--받은 메일함
	 
insert into SENTMAIL
	(MAIL_NUM, MAIL_SENDER, MAIL_RECIPIENT, MAIL_SUBJECT,
	   MAIL_CONTENT, MAIL_FILE, MAIL_ORIGINAL,
	   MAIL_RCHECK, MAIL_TYPE, MAIL_DATE)
	 values(1, 'admin', 'admin', '첫번째 메일', 'ㅎㅇ',
	 null, null, 0, 'out', sysdate);	--받은 메일함
	 
insert into SENTMAIL
	(MAIL_NUM, MAIL_SENDER, MAIL_RECIPIENT, MAIL_SUBJECT,
	   MAIL_CONTENT, MAIL_FILE, MAIL_ORIGINAL,
	   MAIL_RCHECK, MAIL_TYPE, MAIL_DATE)
	 values(2, 'admin', 'admin', '임시보관 메일', 'ㅎㅇ',
	 null, null, 0, 'temp', sysdate); --임시 보관함

insert into sentMAIL
	(MAIL_NUM, MAIL_SENDER, MAIL_RECIPIENT, MAIL_SUBJECT,
	   MAIL_CONTENT, MAIL_FILE, MAIL_ORIGINAL,
	   MAIL_RCHECK, MAIL_TYPE, MAIL_DATE)
	 values(3, 'admin', 'admin', '세번째 메일', 'ㅎㅇ',
	 null, null, 0, 'sbin', sysdate); --휴지통

select* from
	(select rownum rnum, u.*
	from
		(select* 
		from 
			(select* from SENTMAIL
		 	 where MAIL_SENDER='admin' and MAIL_TYPE='bin'
		 	 union 
			 select* from RECEIPTMAIL
		 	 where MAIL_RECIPIENT='admin' and MAIL_TYPE='bin') 
		order by MAIL_DATE desc) u)
where rnum>=1 and rnum<=10

