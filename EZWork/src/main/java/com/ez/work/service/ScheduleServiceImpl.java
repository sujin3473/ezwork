package com.ez.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.dao.ScheduleDAO;
import com.ez.work.domain.Member;
import com.ez.work.domain.Schedule;

@Repository
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDAO dao;
	
	//일정 추가
	public int addSchedule(Schedule sche) throws Exception{
		return dao.addSchedule(sche);
	}

	//일정 view
	@Override
	public List<Schedule> showSchedule(String id) {
		return dao.showSchedule(id);
	}
	
	//로그인 정보 가져오기
	@Override
	public Member getInfo(String m_code) {
		return dao.getInfo(m_code);
	}
	
	//일정 상세정보 가져오기
	@Override
	public Schedule getDetailSchedule(int sch_no) {
		return dao.getDetailSchedule(sch_no);
	}

	@Override
	public int deleteSchedule(int sch_no) {
		return dao.deleteSchedule(sch_no);
	}

	//다른사람의 일정 검색하기
	@Override
	public List<Schedule> searchSchedule(String m_code) throws Exception {
		return dao.searchSchedule(m_code);
	}
	

}
