package com.ez.work.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ez.work.domain.ALList;
import com.ez.work.domain.CmtManage;
import com.ez.work.domain.Member;
import com.ez.work.domain.NoticeBoard;
import com.ez.work.service.AnnualLeaveService;
import com.ez.work.service.CmtManageService;
import com.ez.work.service.NoticeBoardService;

@Controller
public class MainController {

	@Autowired
	private CmtManageService cmtManageService;

	@Autowired
	private AnnualLeaveService annualLeaveService;
	
	@Autowired
	private NoticeBoardService noticeboardService;
	
	// 메인화면
	@GetMapping(value = "/main")
	public ModelAndView DailyCommute(HttpServletRequest request, ModelAndView mv, HttpSession session,
			@RequestParam(value = "check", defaultValue = "0") int check,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10", required = false) int limit) {
		String id = (String) session.getAttribute("M_CODE");
		Member memberinfo = cmtManageService.getInfo(id); // 로그인 된 ID정보 가져옴
		CmtManage memberinfo2 = cmtManageService.getDetail(id); // 당일 근태 정보
		ALList allist = annualLeaveService.getALInfo(id); // 휴가정보 불러오기

		int listcount = noticeboardService.getListCount();

		// 총 페이지수
		int maxpage = (listcount + limit - 1) / limit;

		// 시작 페이지수
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 마지막 페이지수
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;
		List<NoticeBoard> boardlist = noticeboardService.getBoardList(page, limit);
		// System.out.println("입사일 : " + allist.getAL_M_JOIN_DATE());

		mv.setViewName("home");
		mv.addObject("memberinfo", memberinfo);
		mv.addObject("memberinfo2", memberinfo2);
		mv.addObject("boardlist", boardlist);
		mv.addObject("allist", allist);

		if (memberinfo2 == null) {
			check = 0;
		} else {
			if (memberinfo2.getCM_OFFTIME() == null)
				check = 1;
			else
				check = 2;
		}
		mv.addObject("check", check);
		return mv;
	}

	// 메인에서 출근등록
	@PostMapping(value = "/OnTime.main")
	public void OnTime_ok(ModelAndView mv, CmtManage CmtManage, Member member, HttpServletResponse response)
			throws Exception {
		System.out.println(member.getM_CODE());
		CmtManage result = cmtManageService.getDetail(member.getM_CODE());

		if (result == null) {
			cmtManageService.insertOntime(CmtManage); // 저장 메서드 호출
			System.out.println("출근 등록 완료");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('출근 처리 되었습니다.');");
			out.println("location.href=" + "'main?check=1';");
			out.println("</script>");
			out.close();
		} else {
			System.out.println("출근 등록 실패");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('금일 근태 등록은 완료되었습니다.');");
			out.println("location.href=" + "'main?check=0';");
			out.println("</script>");
			out.close();
		}
	}

	// 메인에서 퇴근등록
	@PostMapping("/OffTime.main")
	public void OffTime_ok(CmtManage CmtManage, ModelAndView mv, HttpServletRequest request,
			HttpServletResponse response, Model m, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("M_CODE");
		// 퇴근 메소드 호출

		System.out.println("메인에서 퇴근등록 : " + CmtManage.getCM_MCODE() + " / " + CmtManage.getCM_OFFTIME());
		int result = cmtManageService.insertOfftime(CmtManage, id);

		// 퇴근 등록 실패한 경우
		if (result == 0) {
			System.out.println("퇴근 등록 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "퇴근 등록 실패");
		} else { // 퇴근 등록 성공의 경우
			System.out.println("퇴근 등록 완료");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('퇴근 처리 되었습니다.');");
			out.println("location.href=" + "'main?check=2';");
			out.println("</script>");
			out.close();
		}

	}

}
