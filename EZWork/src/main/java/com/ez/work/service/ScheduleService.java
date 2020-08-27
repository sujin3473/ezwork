package com.ez.work.service;

import java.util.List;

import com.ez.work.domain.Member;
import com.ez.work.domain.Schedule;

public interface ScheduleService {
	
	//일정 추가
	public int addSchedule(Schedule sche) throws Exception;
	
	//일정 view
	public List<Schedule> showSchedule(String id)  throws Exception;
	
	//로그인 정보 가져오기
	public Member getInfo(String m_code);
	
	//일정 상세정보 가져오기
	public Schedule getDetailSchedule(int sch_no);
	
	//일정 삭제하기
	public int deleteSchedule(int sch_no);
	
	//다른 사람의 일정 검색하기
	public List<Schedule> searchSchedule(String m_code) throws Exception;
}
