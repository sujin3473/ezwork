package com.ez.work.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.Mail;

@Repository
public class MailDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void insertMail(Mail mail) {
		sqlSession.insert("Mails.insertout", mail);
		sqlSession.insert("Mails.insertin", mail);
	}
	
	public void tempMail(Mail mail) {
		sqlSession.insert("Mails.temp", mail);
	}
	
	public List<Mail> getInboxList(HashMap<String, Object> map){
		return sqlSession.selectList("Mails.inlist", map);
	}
	
	public List<Mail> getOutboxList(HashMap<String, Object> map){
		return sqlSession.selectList("Mails.outlist", map);
	}
	
	public List<Mail> getTempboxList(HashMap<String, Object> map){
		return sqlSession.selectList("Mails.templist", map);
	}
	
	public List<Mail> getBinList(HashMap<String, Object> map){
		return sqlSession.selectList("Mails.binlist", map);
	}
	
	public int getListCount(String id) {
		return sqlSession.selectOne("Mails.incount",id);
	}
	public int getOutCount(String id) {
		return sqlSession.selectOne("Mails.outcount",id);
	}
	public int getTempCount(String id) {
		return sqlSession.selectOne("Mails.tempcount",id);
	}
	public int getBinCount(String id) {
		int in = sqlSession.selectOne("Mails.binoutcount",id);
		int out = sqlSession.selectOne("Mails.binincount",id);
		return (in+out);
	}

	public int setRCheckUpdate(int num) {
		return sqlSession.update("Mails.RcheckUpdate",num);
	}

	public Mail Indetail(int num) {
		return sqlSession.selectOne("Mails.indetail",num);
	}
	
	public Mail Outdetail(int num) {
		return sqlSession.selectOne("Mails.outdetail",num);
	}
	
	public int InToBin(int num) {
		return sqlSession.update("Mails.intobin",num);
	}
	
	public int OutToBin(int num) {
		return sqlSession.update("Mails.outtobin",num);
	}
	
	public int tempDelete(int num) {
		return sqlSession.delete("Mails.tempdelete", num);
	}

	public int receiptDelete(int num) {
		return sqlSession.delete("Mails.receiptdelete", num);
	}

	public int sentDelete(int num) {
		return sqlSession.delete("Mails.sentdelete", num);
	}
}
