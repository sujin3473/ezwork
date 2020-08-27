package com.ez.work.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.EventBoardDAO;
import com.ez.work.domain.EventBoard;

@Service
public class EventBoardServiceImpl implements EventBoardService{
	
	@Autowired
	private EventBoardDAO dao;
	
	//글 개수
	@Override
	public int getListCount() {
		return dao.getListCount();
	}
	
	//글 목록
	@Override
	public List<EventBoard> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	//글 삽입
	@Override
	public void insertBoard(EventBoard board) {
		dao.insertBoard(board);
	}
	
	//글 조회수
	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}
	
	//글 상세보기
	@Override
	public EventBoard getDetail(int num) {
		if (setReadCountUpdate(num) != 1)
			return null;
		return dao.getDetail(num);
	}

	//답글 쓰기
	@Override
	public int boardReply(EventBoard board) {
		// TODO Auto-generated method stub
		return 0;
	}

	//글 수정
	@Override
	public int boardModify(EventBoard modifyboard) {
		return dao.boardModify(modifyboard);
	}
	
	//글 수정 시 아이디 비번 검사
	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		EventBoard result = dao.isBoardWriter(map);
		if (result == null)
			return false;
		else
			return true;
	}
	
	//글 삭제
	@Override
	public int boardDelete(int num) {
		int result = 0;
		EventBoard board = dao.getDetail(num);
		if (board != null) {
			
			//추가 - 삭제할 파일 목록을 저장하기 위한 메서드 호출
			//dao.insert_deleteFiles(board);
			
			result = dao.boardDelete(board);
		}
		return result;
	}


	//
	@Override
	public int boardReplyUpdate(EventBoard board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert_deleteFile(String before_file) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getDeleteFileList() {
		// TODO Auto-generated method stub
		return null;
	}

}
