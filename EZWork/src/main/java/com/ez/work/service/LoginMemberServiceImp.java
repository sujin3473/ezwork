package com.ez.work.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//혜정
//혜정

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.MemberDAO;
import com.ez.work.domain.Member;

@Service
public class LoginMemberServiceImp implements LoginMemberService {

	@Autowired
	private MemberDAO dao;

	// MemberController에서 넘어옴 -> MemberDAO에서 insert 만들고...

	@Override
	public int isId(String id, String password) {
		Member rmember = dao.isId(id);
		int result = -1; // 아이디가 존재하지 않는 경우 - rmember가 null인 경우
		if (rmember != null) { // 아이디가 존재하는 경우
			if (rmember.getM_PASS().equals(password)) {
				result = 1; // 아이디와 비밀번호가 일치하는 경우
			} else
				result = 0; // 아이디는 존재하지만 비밀번호가 일치하지 않는 경우
		}
		return result;
	}

	@Override
	public int insert(Member m) {
		return dao.insert(m);
	}

	@Override
	public Member member_info(String id) {
		return dao.member_info(id);
	}

	@Override
	public int update(Member m) {
		return dao.update(m);
	}

	@Override
	public List<Member> getSearchList2(int index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (index != -1) {
			String[] search_field = new String[] { "M_NAME", "M_PART_C", "M_MOBILE_TEL" };
			map.put("search_field", search_field[index]);
			map.put("search_word", "%" + search_word + "%");
		}

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getSearchList2(map);
	}

	@Override
	public int getSearchListCount(int index, String search_word) {
		Map<String, String> map = new HashMap<String, String>();
		if (index != -1) {
			String[] search_field = new String[] { "M_NAME", "M_PART_C", "M_MOBILE_TEL" };
			map.put("search_field", search_field[index]);
			map.put("search_word", "%" + search_word + "%");
		}
		return dao.getSearchListCount(map);
	}

	@Override
	public int isId(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 관리자의 사원정보수정
	@Override
	public int update_admin(Member m) {
		return dao.update_admin(m);
	}

	// 퇴사사원 조회
	@Override
	public List<Member> resSearchList(int index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (index != -1) {
			String[] search_field = new String[] { "M_NAME", "M_PART_C", "M_MOBILE_TEL" };
			map.put("search_field", search_field[index]);
			map.put("search_word", "%" + search_word + "%");
		}

		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getresignList(map);
	}

	// 퇴사일 업데이트
	@Override
	public int update_res(Member member) {
		return dao.update_res(member);
	}

}
