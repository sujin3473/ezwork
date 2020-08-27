drop table eventboard;

select * from eventboard;

create table eventboard(
EV_NO NUMBER(11),           --글번호
EV_SORT VARCHAR2(10),      --말머리
EV_NAME VARCHAR2(10),      --작성자
EV_PASS VARCHAR2(15),      --비밀번호
EV_TITLE VARCHAR2(100),      --제목
EV_CONTENT VARCHAR2(3000),   --내용
EV_DATE DATE,            --작성날짜
EV_FILE      VARCHAR2(50),   --첨부될 파일 명(가공)
EV_ORIGINAL   VARCHAR2(50),   --첨부될 파일 명
EV_RE_REF   NUMBER,         --답변 글 작성시 참조되는 글의 번호
EV_RE_LEV   NUMBER,         --답변 글의 깊이
EV_RE_SEQ   NUMBER,         --답변 글의 순서
EV_READCOUNT NUMBER,      --글의 조회수
PRIMARY KEY(EV_NO)
);

insert into eventboard
      (EV_NO,
      EV_SORT, EV_NAME, EV_PASS,
      EV_TITLE,
      EV_RE_REF,
      EV_RE_LEV,
      EV_RE_SEQ, EV_READCOUNT,
      EV_DATE)
      values
      (6,
      '기타','인사팀', '1234',
      '경조사 게시판 TEST 제목3',
      1,
      0, 0, 0,
      sysdate);
      
      
      insert into EVENTBOARD 
      (EV_NO)
      values (1);
      
      SELECT * FROM eventboard;