package com.ez.work.service;

import java.util.List;

import com.ez.work.domain.ALList;
import com.ez.work.domain.ALRequest;
import com.ez.work.domain.Member;

public interface AnnualLeaveService {

	// 로그인 정보 가져오기
	public Member getInfo(String M_CODE);
	
	//휴가 신청 등록
	public int insertAL(ALRequest alrequest);
	
	//휴가 신청 list
	public List<ALRequest> getRequestList(int page, int limit, String id);
	
	// 목록 갯수 구하기
	public int getListCount(String id);
	
	// 부서 휴가 목록
	public List<ALRequest> getTeamInfo(String M_PART_C);
	
	//휴가 기본정보 등록
	public int ALlistInsert(Member member);
	
	//근속년수 계산
	public int ALlistUpdate(Member member);
	
	//휴가 개수 1년차 이상 계산
	public int updateOverOneYear(Member member);
	
	//휴가 개수 1년차 미만 계산
	public void updateUnderOneYear();
	
	//휴가 갯수 시간으로 환산
	public void calculateHour();

	//휴가 정보 불러오기
	public ALList getALInfo(String id);
	
	//휴가 신청한 총 시간 계산하기
	public void calUsedHour(String id, String startdate, String enddate, int sort);
}
