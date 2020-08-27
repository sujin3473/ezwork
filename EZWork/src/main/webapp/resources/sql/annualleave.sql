drop table REQUEST_AL;
drop table LIST_AL;


create table REQUEST_AL( --연차신청 table
AL_DATE date, --등록일
AL_M_NAME varchar2(20), --등록자 이름
AL_M_PART_C varchar2(20), --부서
AL_M_CODE varchar2(20), --사번
AL_SORT varchar2(20),  --연차종류
AL_STARTDATE date,  --시작일
AL_ENDDATE date,   --종료일
AL_REASON varchar(500) --휴가사유
);

select * from request_al;



create table LIST_AL(
AL_M_CODE varchar2(20), --사번
AL_M_NAME varchar2(20), --등록자 이름
AL_M_PART_C varchar2(20), --부서 소속
AL_M_JOIN_DATE date, --입사날짜
AL_YEARS number,-- 근속년수
AL_TOTALDAY number, --보유연차 (일수)
AL_TOTALHOUR number, --보유연차 (시간)
AL_USED number, -- 사용한 연차
AL_UNUSED number -- 남은연차
);

select * from list_al;

--한번씩 돌려주세요--
update list_al
set al_unused = 0, al_used = 0;


delete from REQUEST_AL;
