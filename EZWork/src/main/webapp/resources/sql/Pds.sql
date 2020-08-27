drop table Pds;

select * from Pds;

create table Pds (
pds_NO NUMBER(11),           --글번호
pds_SORT VARCHAR2(30),      --말머리
pds_NAME VARCHAR2(10),      --작성자
pds_PASS VARCHAR2(15),      --비밀번호
pds_TITLE VARCHAR2(100),      --제목
pds_CONTENT VARCHAR2(3000),   --내용
pds_DATE DATE,            --작성날짜
pds_FILE      VARCHAR2(50),   --첨부될 파일 명(가공)
pds_ORIGINAL   VARCHAR2(50),   --첨부될 파일 명
pds_RE_REF   NUMBER,         --답변 글 작성시 참조되는 글의 번호
pds_RE_LEV   NUMBER,         --답변 글의 깊이
pds_RE_SEQ   NUMBER,         --답변 글의 순서
pds_READCOUNT NUMBER,      --글의 조회수
PRIMARY KEY(pds_NO)
);

insert into Pds
      (pds_NO,
      pds_SORT, pds_NAME, pds_PASS,
      pds_TITLE,
      pds_RE_REF,
      pds_RE_Lpds,
      pds_RE_SEQ, pds_READCOUNT,
      pds_DATE)
      values
      (6,
      '기타','인사팀', '1234',
      '자료실 TEST 제목3',
      1,
      0, 0, 0,
      sysdate);
      
      
      insert into Pds
      (pds_NO)
      values (1);
      
      SELECT * FROM Pds;