package com.ez.work.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.Member;
import com.ez.work.domain.bookmark;

//민혁
@Repository
public class EmpDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Member> getSearchList(HashMap<String, Object> map) {
		return sqlSession.selectList("Members.getSearchList", map);
	}

	public int getSearchListCount(Map<String, String> map) {
		return sqlSession.selectOne("Members.searchcount", map);
	}

	public Member getSearchMemberInfo(String M_CODE) {
		return sqlSession.selectOne("Members.EmpCheck", M_CODE);
	}

	public int insertbookmark(Map<String, Object> map) {
		return sqlSession.insert("Members.bkinsert", map);
	}

	public int deletebookmark(Map<String, Object> map) {
		return sqlSession.delete("Members.bkdelete", map);
	}

	public List<bookmark> bkinf(String owner) {
		return sqlSession.selectList("Members.bkinf", owner);
	}

	public List<bookmark> getOwnerId(String owner) {
		return sqlSession.selectList("Members.getOwnerId", owner);
	}

	public List<Member> EmpWishlist(String owner) {
		return sqlSession.selectList("Members.EmpWishlist", owner);
	}

	public int DeleteWishEmp(String m_CODE) {
		return sqlSession.delete("Members.DeleteWishEmp", m_CODE);
		
	}

}
