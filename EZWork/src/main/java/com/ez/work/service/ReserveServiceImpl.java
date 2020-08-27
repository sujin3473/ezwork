package com.ez.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.MeetingDAO;
import com.ez.work.domain.MeetingRoom;

@Service
public class ReserveServiceImpl implements ReserveService{
	@Autowired
	private MeetingDAO dao;
	
	public int insertM(MeetingRoom meeting) {
		int check = dao.verify(meeting);
		if(check>0) {
			return check;
		} else {
			dao.insertMeeting(meeting);
			return check;
		}
	}

	public List<MeetingRoom> getList() {
		return dao.getList();
	}

	public void truncate() {
		dao.truncate();
	}
}
