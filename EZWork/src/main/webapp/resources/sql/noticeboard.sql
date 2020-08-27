drop table noticeboard;

select * from noticeboard;

create table noticeboard(
NO_NO NUMBER(11),  			--글번호
NO_SORT VARCHAR2(10),		--말머리
NO_NAME VARCHAR2(10),		--작성자
NO_PASS VARCHAR2(15),		--비밀번호
NO_TITLE VARCHAR2(200),		--제목
NO_CONTENT VARCHAR2(3000),	--내용
NO_DATE DATE,				--작성날짜
NO_FILE		VARCHAR2(50),	--첨부될 파일 명(가공)
NO_ORIGINAL	VARCHAR2(50),	--첨부될 파일 명
NO_RE_REF	NUMBER,			--답변 글 작성시 참조되는 글의 번호
NO_RE_LEV	NUMBER,			--답변 글의 깊이
NO_RE_SEQ	NUMBER,			--답변 글의 순서
NO_READCOUNT NUMBER,		--글의 조회수
PRIMARY KEY(NO_NO)
);

insert into noticeboard
		(NO_NO,
		NO_SORT, NO_NAME, NO_PASS,
		NO_TITLE, NO_CONTENT, 
		NO_RE_REF,
		NO_RE_LEV,
		NO_RE_SEQ, NO_READCOUNT,
		NO_DATE)
		values
		(1,
		'공지','인사팀', '1234',
		'사내 통신망 연결 공지','떼잉! 그런거 없수다',
		1,
		0, 0, 0,
		sysdate);
		
		
		insert into noticeboard 
		(NO_NO)
		values (1);
		
		SELECT * FROM noticeboard;