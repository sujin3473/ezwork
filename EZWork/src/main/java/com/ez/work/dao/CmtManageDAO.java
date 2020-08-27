package com.ez.work.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.CmtManage;
import com.ez.work.domain.Member;

@Repository
public class CmtManageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	

	public void insertOntime(CmtManage cmtManageOn) {
		 sqlSession.insert("CmtManage.insert", cmtManageOn);
	}

	public int insertOfftime(HashMap<String, Object> map) {
		return sqlSession.update("CmtManage.modify", map);
	}


	public CmtManage getDetail(String m_code) {
		return sqlSession.selectOne("CmtManage.Detail", m_code);
	}

	public Member getInfo(String m_code) {
		return sqlSession.selectOne("Members.EmpCheck", m_code);
	}

	public List<CmtManage> monthlyCmt(String m_code) {
		return sqlSession.selectList("CmtManage.monthlyCmt", m_code);
	}

	public void dailyWorkHours() {
		sqlSession.update("CmtManage.dailyWork");
	}

	public int getListCount(String id) {
		return sqlSession.selectOne("CmtManage.count", id);
	}
	public List<CmtManage> getCmtList(HashMap<String, Object> map) {
		return sqlSession.selectList("CmtManage.list", map);
	}

	public void accumulativeHours(String id) {
		sqlSession.update("CmtManage.accumulative", id);
	}
}
