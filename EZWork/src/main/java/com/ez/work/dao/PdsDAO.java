package com.ez.work.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.Pds;


@Repository
public class PdsDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Pds> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Pds.list", map);
	}

	public int getListCount() {
		return sqlSession.selectOne("Pds.count");
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("Pds.ReadCountUpdate", num);
	}

	public void insertBoard(Pds board) {
		sqlSession.insert("Pds.insert", board);
		
	}
	
	public Pds getDetail(int num) {
		return sqlSession.selectOne("Pds.Detail", num);
	}
	
	public int boardModify(Pds modifyboard) {
		return sqlSession.update("Pds.modify", modifyboard);
	}

	public Pds isBoardWriter(Map<String, Object> map) {
		return sqlSession.selectOne("Pds.BoardWriter", map);
	}

	public void insert_deleteFiles(Pds board) {
		
	}

	public int boardDelete(Pds board) {
		return sqlSession.delete("Pds.delete", board);
	}
}
