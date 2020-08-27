package com.ez.work.service;

import java.util.List;

import com.ez.work.domain.MeetingRoom;

public interface ReserveService {
	public int insertM(MeetingRoom meeting);
	public List<MeetingRoom> getList();
	public void truncate();
}
