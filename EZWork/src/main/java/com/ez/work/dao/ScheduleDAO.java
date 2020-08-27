package com.ez.work.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.Member;
import com.ez.work.domain.Schedule;
@Repository
public class ScheduleDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public int addSchedule(Schedule sche) throws Exception  {
		return sqlSession.insert("Schedule.addSchedule", sche);
	}

	public List<Schedule> showSchedule(String id) {
		return sqlSession.selectList("Schedule.showSchedule", id);
	}

	public Member getInfo(String m_code) {
		return sqlSession.selectOne("Members.EmpCheck", m_code);
	}

	public Schedule getDetailSchedule(int sch_no) {
		return sqlSession.selectOne("Schedule.getDetail", sch_no);
	}

	public int deleteSchedule(int sch_no) {
		return sqlSession.delete("Schedule.deleteSchedule", sch_no);
	}

	public List<Schedule> searchSchedule(String m_code) {
		return sqlSession.selectList("Schedule.searchSchedule", m_code);
	}

}
