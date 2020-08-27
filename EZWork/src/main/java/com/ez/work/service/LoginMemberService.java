package com.ez.work.service;

import java.util.List;
import com.ez.work.domain.Member;

//혜정
//혜정

public interface LoginMemberService {
	public int isId(String id, String pass);
	public int insert(Member m);
	public int isId(String id);
	public Member member_info(String id);
	public int update(Member m);
	public int update_admin(Member m);
	public int update_res(Member member);

	public List<Member> getSearchList2(int index, String search_word, int page, int limit);
	public List<Member> resSearchList(int index, String search_word, int page, int limit);
	public int getSearchListCount(int index, String search_word);

}
