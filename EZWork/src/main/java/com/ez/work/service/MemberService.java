package com.ez.work.service;

//민혁
import java.util.List;
import java.util.Map;

import com.ez.work.domain.Member;
import com.ez.work.domain.bookmark;

public interface MemberService {
	public List<Member> getSearchList(int index, String search_word, int page, int limit, String owner);

	public int getSearchListCount(int index, String search_word);

	public Member member_info(String M_CODE);

	public void choosebookmark(String user, String owner, int bookmark);

	public List<bookmark> bookmarklist(String owner);

	public List<bookmark> getOwnerId(String owner);

	public List<Member> EmpWishlist(String owner);

	public int DeleteWishEmp(String m_CODE);

}
