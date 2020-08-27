package com.ez.work.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.MeetingRoom;

@Repository
public class MeetingDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertMeeting(MeetingRoom meeting) {
		return sqlSession.insert("Reserves.insert", meeting);
	}

	public int verify(MeetingRoom meeting) {
		return sqlSession.selectOne("Reserves.check", meeting);
	}
	
	public List<MeetingRoom> getList(){
		return sqlSession.selectList("Reserves.list");
	}

	public void truncate() {
		sqlSession.delete("Reserves.reset");
	}
}
