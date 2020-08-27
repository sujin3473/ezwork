package com.ez.work.service;

import java.util.List;

import com.ez.work.domain.EventBoard;

public interface EventBoardService {

	// 글의 갯수 구하기
	public int getListCount();

	// 글 목록 보기
	public List<EventBoard> getBoardList(int page, int limit);

	// 글 내용 보기
	public EventBoard getDetail(int num);

	// 글 답변
	public int boardReply(EventBoard board);

	// 글 수정
	public int boardModify(EventBoard modifyboard);

	// 글 삭제
	public int boardDelete(int num);

	// 글 등록
	public void insertBoard(EventBoard board);

	// 조회수 업데이트
	public int setReadCountUpdate(int num);

	// 글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass);

	// 시퀀스 수정
	public int boardReplyUpdate(EventBoard board);

	// 파일 삭제를 위한 파일명 추가합니다.
	public int insert_deleteFile(String before_file);

	public List<String> getDeleteFileList();

}
