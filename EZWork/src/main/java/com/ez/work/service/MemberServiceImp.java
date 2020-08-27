package com.ez.work.service;

//민혁
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.work.dao.EmpDao;
import com.ez.work.domain.Member;
import com.ez.work.domain.bookmark;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	private EmpDao dao;

	@Override
	public List<Member> getSearchList(int index, String search_word, int page, int limit, String owner) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (index != -1) {
			String[] search_field = new String[] { "M_CODE", "M_PART_C", "M_NAME", "M_LEVEL" };
			map.put("search_field", search_field[index]);
			map.put("search_word", "%" + search_word + "%");
		}
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("owner", owner);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getSearchList(map);
	}

	@Override
	public int getSearchListCount(int index, String search_word) {
		Map<String, String> map = new HashMap<String, String>();
		if (index != -1) {
			String[] search_field = new String[] { "M_CODE", "M_PART_C", "M_NAME", "M_LEVEL" };
			map.put("search_field", search_field[index]);
			map.put("search_word", "%" + search_word + "%");
		}
		return dao.getSearchListCount(map);
	}

	@Override
	public Member member_info(String M_CODE) {
		return dao.getSearchMemberInfo(M_CODE);
	}

	@Override
	public void choosebookmark(String user, String owner, int bookmark) {
		System.out.println("서비스단에서의 첫번째 bookmark값은 : " + bookmark);
		if (bookmark == 1) {
			bookmark = 0; // 사라지는거
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("owner", owner);
			map.put("bookmark", bookmark);
			dao.deletebookmark(map);
			System.out.println("서비스단의 두번째 bookmark값은(delete) " + bookmark);
			// delete
		} else if (bookmark == 0) {
			bookmark = 1; // 추가
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("owner", owner);
			map.put("bookmark", bookmark);
			dao.insertbookmark(map);
			System.out.println("서비스단의 두번째 bookmark값은(insert) " + bookmark);
			System.out.println("map의 값은 " + map);
		}

	}

	@Override
	public List<bookmark> bookmarklist(String owner) {
		System.out.println("서비스단으로 넘어온 owner값은 " + owner);
		return dao.bkinf(owner);
	}

	@Override
	public List<bookmark> getOwnerId(String owner) {
		return dao.getOwnerId(owner);
	}

	@Override
	public List<Member> EmpWishlist(String owner) {
		return dao.EmpWishlist(owner);
	}

	@Override
	public int DeleteWishEmp(String m_CODE) {
		return dao.DeleteWishEmp(m_CODE);
	}

	/*
	 * @Override public int updatebookmark(String id, int bookmark) {
	 * System.out.println("if문 전의 bookmark의 값은 " + bookmark); if (bookmark == 0) {
	 * bookmark = 1; } else if (bookmark == 1) { bookmark = 0; }
	 * 
	 * System.out.println("if문 뒤의 bookmark의 값은 " + bookmark);
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("id", id);
	 * map.put("bookmark", bookmark); dao.updatebookmark(map); return bookmark; }
	 */

}
