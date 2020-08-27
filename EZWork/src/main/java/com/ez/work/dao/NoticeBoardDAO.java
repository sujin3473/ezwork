package com.ez.work.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.NoticeBoard;


@Repository
public class NoticeBoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<NoticeBoard> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("NoticeBoards.list", map);
	}

	public int getListCount() {
		return sqlSession.selectOne("NoticeBoards.count");
	}

	public int setReadCountUpdate(int num) {
		return sqlSession.update("NoticeBoards.ReadCountUpdate", num);
	}

	public void insertBoard(NoticeBoard board) {
		sqlSession.insert("NoticeBoards.insert", board);
		
	}
	
	public NoticeBoard getDetail(int num) {
		return sqlSession.selectOne("NoticeBoards.Detail", num);
	}
	
	public int boardModify(NoticeBoard modifyboard) {
		return sqlSession.update("NoticeBoards.modify", modifyboard);
	}

	public NoticeBoard isBoardWriter(Map<String, Object> map) {
		return sqlSession.selectOne("NoticeBoards.BoardWriter", map);
	}

	public void insert_deleteFiles(NoticeBoard board) {
		// TODO Auto-generated method stub
		
	}

	public int boardDelete(NoticeBoard board) {
		return sqlSession.delete("NoticeBoards.delete", board);
	}
}
