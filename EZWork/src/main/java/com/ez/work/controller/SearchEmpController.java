package com.ez.work.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ez.work.domain.Member;
import com.ez.work.domain.bookmark;
import com.ez.work.service.MemberService;
//¹ÎÇõ

@Controller
public class SearchEmpController {

	@Autowired
	private MemberService memberservice;

	@RequestMapping(value = "/member_list", method = RequestMethod.GET)
	public ModelAndView memberList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit, ModelAndView mv,
			@RequestParam(value = "search_field", defaultValue = "-1") int index,
			@RequestParam(value = "search_word", defaultValue = "") String search_word, Model m, HttpSession session)
			throws Exception {

		List<Member> list = null;
		int listcount = 0;

		String owner = (String) session.getAttribute("M_CODE");
		list = memberservice.getSearchList(index, search_word, page, limit, owner);
		listcount = memberservice.getSearchListCount(index, search_word);
		List<bookmark> realowner = memberservice.getOwnerId(owner);

		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage)
			endpage = maxpage;

		m.addAttribute("page", "Search/SearchEmp.jsp");
		mv.setViewName("home");
		mv.addObject("page1", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("memberlist", list);
		mv.addObject("owner", owner);// 현재 로그인 되어있는 id
		mv.addObject("realowner", realowner);// 선택한 사람
		mv.addObject("limit", limit);
		mv.addObject("search_field", index);
		mv.addObject("search_word", search_word);
		return mv;
	}

	@RequestMapping(value = "/member_info", method = RequestMethod.GET)
	public ModelAndView member_info(@RequestParam("m_code") String M_CODE, ModelAndView mv, Model n) throws Exception {
		Member m = memberservice.member_info(M_CODE);

		n.addAttribute("page", "Search/DetailEmp.jsp");
		mv.setViewName("home");
		mv.addObject("memberinfo", m);
		return mv;
	}

	@RequestMapping(value = "/updatebookmark", method = RequestMethod.GET)
	public @ResponseBody List<bookmark> updatebookmark(@RequestParam("id") String user, HttpSession session,
			@RequestParam("bookmark") int bookmark, ModelAndView mv, Model n) throws Exception {
		System.out.println("컨트롤러단에서의 bookmark값은 " + bookmark);
		System.out.println("id값은 " + user);
		System.out.println("bookmark값은 " + bookmark);
		String owner = (String) session.getAttribute("M_CODE"); // 로그인한 사람 id
		memberservice.choosebookmark(user, owner, bookmark); // 서비스측에 즐겨찾기 선택 당한 id와 선택한 id, 즐겨찾기 버튼 값을 보냄

		List<bookmark> bminf = memberservice.bookmarklist(owner);
		n.addAttribute("page", "Search/SearchEmp.jsp");
		mv.setViewName("home");
		mv.addObject("bminf", bminf);
		return bminf;

	}

	@RequestMapping(value = "/EmpWishlist", method = RequestMethod.GET)
	public ModelAndView EmpWishlist(ModelAndView mv, Model n, HttpSession session) throws Exception {
		String owner = (String) session.getAttribute("M_CODE");
		List<Member> m = memberservice.EmpWishlist(owner);
		n.addAttribute("page", "Search/EmpWishlist.jsp");
		mv.setViewName("home");
		mv.addObject("owner", owner);
		mv.addObject("EmpWishlist", m);
		return mv;
	}

	@RequestMapping(value = "/deleteWishEmp", method = RequestMethod.GET)
	public void DeleteWishEmp(@RequestParam("m_code") String M_CODE, HttpServletResponse response) throws Exception {
		System.out.println("M_CODE의 값은" + M_CODE);
		int m = memberservice.DeleteWishEmp(M_CODE);

		if (m == 1)
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('목록에서 제외되었습니다.');");
			out.println("location.href=" + "'EmpWishlist';");
			out.println("</script>");
			out.close();
		
	}
}
