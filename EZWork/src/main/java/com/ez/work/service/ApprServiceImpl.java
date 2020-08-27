package com.ez.work.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.ApprDAO;
import com.ez.work.domain.Appr;
import com.ez.work.domain.Member;

@Service
public class ApprServiceImpl implements ApprService {

	@Autowired
	private ApprDAO dao;

	public int getListCount() {		
		return dao.getListCount();
	}

	public List<Appr> getApprList(int page, int limit, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("id", id);
		return dao.getApprList(map);
		
	}
	
	public String getPart(String m_code) {		
		return dao.getPart(m_code);
	}
	
	public String getName(String m_code) {		
		return dao.getName(m_code);
	}

	public List<Member> searchMemList(String keyword) {
		keyword = "%"+keyword +"%";
		return dao.searchMemList(keyword);
	}

	public int getMemCount() {
		return dao.getMemCount();
	}

	public void insertappr(Appr appr) {
		dao.insertAppr(appr);		
	}

	public Appr getDetail(int num) {
		return dao.getDetail(num);
	}

	public void updateApproval(int apnum, String valcol, int approve_val,String commcol, String comment) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("apnum", apnum);
		map.put("valcol",valcol);
		map.put("approve_val",approve_val);
		map.put("commcol", commcol);
		map.put("comment",comment);
		dao.updateApproval(map);
	}

	public void updateApprovalR(int apnum, String valcol, int approve_val, String commcol, String comment) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("apnum", apnum);
		map.put("valcol",valcol);
		map.put("approve_val",approve_val);
		map.put("commcol", commcol);
		map.put("comment",comment);
		dao.updateApprovalR(map);
		
	}

	public void writeCompDate(int apnum) {
		dao.writeCompDate(apnum);
		
	}

	public List<Appr> getSearchList(String number, String m_name, String contentitle, String start, String end,
			String appr_stat, String appr_val, int page, int limit, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		contentitle= "%"+contentitle+"%";
		
		
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("id", id);
		map.put("number", number);
		map.put("m_name", m_name);
		map.put("contentitle", contentitle);
		map.put("start", start);
		map.put("end", end);
		map.put("appr_stat", appr_stat);
		map.put("appr_val", appr_val);
		System.out.println("getSearchList : "+map);
		
		return dao.getSearchList(map);
	}
	
}
