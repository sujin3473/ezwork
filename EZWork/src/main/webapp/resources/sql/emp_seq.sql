
--혜정
--사원번호 시퀀스
drop sequence emp_seq;


CREATE SEQUENCE emp_seq
INCREMENT BY 1     		 -- 1씩 증가 
START WITH 202001        -- 202001 시작
;

select M.M_CODE from (select M_CODE, ROWNUM from member where M_CODE != 'admin' order by ROWNUM desc) M where ROWNUM = 1

