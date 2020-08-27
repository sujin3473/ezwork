package com.ez.work.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.NoticeBoardDAO;
import com.ez.work.domain.NoticeBoard;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{
	
	@Autowired
	private NoticeBoardDAO dao;
	
	//글 개수
	@Override
	public int getListCount() {
		return dao.getListCount();
	}
	
	//글 목록
	@Override
	public List<NoticeBoard> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public NoticeBoard getDetail(int num) {
		if (setReadCountUpdate(num) != 1)
			return null;
		return dao.getDetail(num);
	}

	@Override
	public int boardReply(NoticeBoard board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardModify(NoticeBoard modifyboard) {
		return dao.boardModify(modifyboard);
	}

	@Override
	public int boardDelete(int num) {
		int result = 0;
		NoticeBoard board = dao.getDetail(num);
		if (board != null) {
			
			//추가 - 삭제할 파일 목록을 저장하기 위한 메서드 호출
			//dao.insert_deleteFiles(board);
			
			result = dao.boardDelete(board);
		}
		return result;
	}

	@Override
	public void insertBoard(NoticeBoard board) {
		dao.insertBoard(board);	
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		NoticeBoard result = dao.isBoardWriter(map);
		if (result == null)
			return false;
		else
			return true;
	}

	@Override
	public int boardReplyUpdate(NoticeBoard board) {
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
