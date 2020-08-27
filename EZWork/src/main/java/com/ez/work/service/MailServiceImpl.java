package com.ez.work.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.MailDAO;
import com.ez.work.domain.Mail;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private MailDAO dao;
	
	public void insertMail(Mail mail) {
		dao.insertMail(mail);
	}
	
	public void tempMail(Mail mail) {
		dao.tempMail(mail);
	}

	public List<Mail> getInboxList(int page, int limit, String sender) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("sender", sender);
		return dao.getInboxList(map);
	}
	
	public List<Mail> getOutboxList(int page, int limit, String sender) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("sender", sender);
		return dao.getOutboxList(map);
	}
	
	public List<Mail> getTempboxList(int page, int limit, String sender) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("sender", sender);
		return dao.getTempboxList(map);
	}
	
	public List<Mail> getBinList(int page, int limit, String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int startrow = (page-1)*limit + 1;
		int endrow = startrow + limit -1;
		map.put("start", startrow);
		map.put("end", endrow);
		map.put("id", id);
		return dao.getBinList(map);
	}
	
	public int getListCount(String id) {
		return dao.getListCount(id);
	}
	public int getOutListCount(String id) {
		return dao.getOutCount(id);
	}
	public int getTempListCount(String id) {
		return dao.getTempCount(id);
	}
	public int getBinListCount(String id) {
		return dao.getBinCount(id);
	}
	
	public Mail getDetail(int num) {
		if(setRCheckUpdate(num)!=1)
			return null;
		return dao.Indetail(num);
	}
	
	public Mail outDetail(int num) {
		return dao.Outdetail(num);
	}

	public int setRCheckUpdate(int num) {
		return dao.setRCheckUpdate(num);	//return 1이면 1줄 업데이트 된 것.
	}
	
	public int InToBin(int num) {
		return dao.InToBin(num);
	}
	
	public int OutToBin(int num) {
		return dao.OutToBin(num);
	}
	
	public int tempDelete(int num) {
		int result = 0;
		//파일 삭제 처리하기
		result = dao.tempDelete(num);
		return result;
	}

	public int sentDelete(int num) {
		int result = 0;
		//파일 삭제 처리하기
		result = dao.sentDelete(num);
		return result;
	}

	public int receiptDelete(int num) {
		int result = 0;
		//파일 삭제 처리하기
		result = dao.receiptDelete(num);
		return result;
	}	
}
