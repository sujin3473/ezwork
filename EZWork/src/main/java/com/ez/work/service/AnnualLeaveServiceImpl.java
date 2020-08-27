package com.ez.work.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.AnnualLeaveDAO;
import com.ez.work.domain.ALList;
import com.ez.work.domain.ALRequest;
import com.ez.work.domain.Member;

@Service
public class AnnualLeaveServiceImpl implements AnnualLeaveService {
	
	@Autowired
	private AnnualLeaveDAO dao;

	@Override
	public Member getInfo(String M_CODE) {
		return dao.getInfo(M_CODE);
	}

	@Override
	public int insertAL(ALRequest alrequest) {
		return dao.insertAL(alrequest);
	}

	@Override
	public List<ALRequest> getRequestList(int page, int limit, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("id", id);
		return dao.getRequestList(map);
	}

	@Override
	public int getListCount(String id) {
			return dao.getListCount(id);
	}

	@Override
	public List<ALRequest> getTeamInfo(String M_PART_C) {
		return dao.getTeamInfo(M_PART_C);
	}

	@Override
	public int ALlistInsert(Member member) {
		return dao.ALlistInsert(member);
	}

	@Override
	public int ALlistUpdate(Member member) {
		return dao.ALlistUpdate(member);
	}

	@Override
	public int updateOverOneYear(Member member) {
		return dao.updateOverOneYear(member);
	}

	@Override
	public void updateUnderOneYear() {
		dao.updateUnderOneYear();
	}

	@Override
	public void calculateHour() {
		dao.calculateHour();
	}

	@Override
	public ALList getALInfo(String id) {
		return dao.getALInfo(id);
	}

	@Override
	public void calUsedHour(String id, String startdate, String enddate, int sort) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int[] ilsu = {8,4,0,8,4};
		int hour = ilsu[sort];
		 SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd");
		try {
			Date startdate1 = sdf.parse(startdate);
			Date enddate1 = sdf.parse(enddate);
			long finaltime = enddate1.getTime() - startdate1.getTime();
			long finaldays = finaltime / (24*60*60*1000); 
			System.out.println(finaltime);
			System.out.println(finaldays);
			map.put("id", id);
			map.put("finaldays", finaldays);
			map.put("hour", hour);
			dao.calUsedHour(map);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
