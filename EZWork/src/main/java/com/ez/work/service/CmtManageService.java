package com.ez.work.service;

import java.util.List;

import com.ez.work.domain.CmtManage;
import com.ez.work.domain.Member;

public interface CmtManageService {


	// 출근 정보 입력
	public void insertOntime(CmtManage cmtManageOn);

	// 퇴근 정보 입력
	public int insertOfftime(CmtManage cmtManageOff, String id);

	//당일 근태 현황 select
	public CmtManage getDetail(String datecode);
	
	//로그인 정보 가져오기
	public Member getInfo(String M_CODE);
	
	//월간 보기
	public List<CmtManage> monthlyCmt(String m_code);

	//하루 근무 시간
	public void dailyWorkHours();

	//근태 찍은 날짜 수
	public int getListCount(String id);

	//누적근무 계산
	public void accumulativeHours(String id);

	//근태리스트
	public List<CmtManage> getCmtList(int page, int limit, String id);

}
