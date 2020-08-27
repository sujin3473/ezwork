package com.ez.work.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.work.domain.Member;


/* 혜정  */
/* 혜정  */
/* 혜정  */

//LoginServiceImpl로 이동... 


@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insert(Member m) {
		int result1 = sqlSession.insert("Members.insert", m); //회원가입 테이블에 정보 입력
		String code = sqlSession.selectOne("ALlist.getcode");
		code = "EMP"+code;
		m.setM_CODE(code);
		
		int result2=-1;
		
		if(result1 != 0)
			result2 = sqlSession.insert("ALlist.insert",m);
		 int result3 = sqlSession.insert("ALlist.update",m); //연차 테이블 근속년수 계산 입력
		 sqlSession.update("ALlist.updateUnder"); //연차 일수 일자로 계산 
		 int result4 = sqlSession.update("ALlist.updateOver", m);
		 sqlSession.update("ALlist.calHour"); // 연차 일수를 시간으로 계산
		if (result1 > 0 && result2 >0  && result3 > 0 && result4 > 0)
			return 1;
		else
			return 0;
		
	}

	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck",id);// 이부분이 mamber.xml 에서 mapper Members
		
	}
	
	public Member member_info(String id) {
		return sqlSession.selectOne("Members.idcheck",id);
	}


	public int update(Member m) {
		return sqlSession.update("Members.update",m);
	}
	
	//관리자 업데이트
	public int update_admin(Member m) {
		return sqlSession.update("Members.update_admin",m);
	}
	
	
	//퇴사일 업데이트
	public int update_res(Member m) {
		return sqlSession.update("Members.update_res",m);
	}
	
	
	
	//사원 조회 페이지
	//사원 검색 페이지2
	public List<Member> getSearchList2(Map<String, Object> map) {
		return sqlSession.selectList("Members.getSearchList2", map);
	}
	
	//사원 조회 페이지
	 public List<Member> getList(Map<String, Integer> map){
	      return sqlSession.selectList("Members.getList", map);
	   }


	public int getSearchListCount(Map<String, String> map) {
		return sqlSession.selectOne("Members.searchcount2", map);
	}

	
	//퇴사 사원 조회 페이지	
	public List<Member> getresignList(Map<String, Object> map) {
		return sqlSession.selectList("Members.getresignList", map);
	}



	
	

}
