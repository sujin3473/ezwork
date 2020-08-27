package com.ez.work.controller;

import java.io.File;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ez.work.domain.Member;
import com.ez.work.service.AnnualLeaveService;
import com.ez.work.service.CmtManageService;
import com.ez.work.service.LoginMemberService;

/* 혜정  */
/* 혜정  */
/* 혜정  */
/* 혜정 */

@Controller
public class MemberController {

	@Value("${membersavefoldername}")
	private String membersaveFolder;

	@Autowired
	private LoginMemberService loginmemberservice; // MemberService로 이동해서 주입

	@Autowired // 비밀번호 암호화
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CmtManageService cmtManageService;

	// 로그인화면으로 이동
	@RequestMapping(value = "/login.net")
	public ModelAndView login(ModelAndView mv, @CookieValue(value = "saveid", required = false) Cookie readCookie)
			throws Exception {

		if (readCookie != null) {
			mv.addObject("saveid", readCookie.getValue());

			System.out.println("cookie time = " + readCookie.getMaxAge());
		}

		mv.setViewName("member/loginForm");
		return mv;
	}

	// 사원등록페이지
	@GetMapping(value = "/insert.hr")
	public String joinForm(HttpServletRequest request, Model m) {
		m.addAttribute("page", "member/joinForm.jsp");
		return "home";
	}

	// 로그인 처리
	@RequestMapping(value = "/loginProcess.net", method = RequestMethod.POST)
	public String loginProcess(@RequestParam(value = "M_CODE", required = false) String id, // 로그인값 넘어온것
			@RequestParam(value = "M_PASS", required = false) String password,
			@RequestParam(value = "remember", defaultValue = "") String remember, // 체크하는거에요. 기본값 스트링이니까 빈값으로 넣어줬읍니다.
			HttpServletResponse response, HttpSession session) throws Exception {

		int result = loginmemberservice.isId(id, password); // 두개 넘겨줍니다.
		System.out.println("결과는 " + result);

		if (result == 1) { // 결과에 따라서 나머지 조건을 처리한겁니다. 리멤버를 체크한 경우와 아닌 경우 두가지를 설정할겁니다.

			// 로그인 성공
			session.setAttribute("M_CODE", id);
			Member memberinfo = cmtManageService.getInfo(id);
			session.setAttribute("M_FILE", memberinfo.getM_FILE());
			Cookie savecookie = new Cookie("saveid", id);
			if (!remember.equals("")) {
				savecookie.setMaxAge(60 * 60 * 24);
				System.out.println("쿠키저장 : 60*60"); // 저장한 경우입니다.

			} else {
				System.out.println("쿠키저장 : 0");
				savecookie.setMaxAge(0); // 저장 안된 경우입니다.
			}
			response.addCookie(savecookie); // 모두 값을 담습니다.
			return "redirect:main"; // 그리고 리턴합니다. -> BoardController로 이동~

		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			if (result == -1)
				message = "사원번호가 존재하지 않습니다.";

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.net';");
			out.println("</script>");
			out.close();
			return null;

		}
	}

	// 로그아웃

	@RequestMapping(value = "/logout.net", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:login.net";

	}

	// 사원등록
	@RequestMapping(value = "/joinProcess.net", method = RequestMethod.POST)
	public void joinProcess(Member member, HttpServletResponse response, HttpServletRequest request) throws Exception {
		System.out.println(member.getM_PASS());
		/*
		 * String saveFolder =
		 * request.getSession().getServletContext().getRealPath("resources") +
		 * "/upload/";
		 */
		// System.out.println(saveFolder);
		System.out.println(member.getM_BIRTH());
		MultipartFile uploadfile = member.getProfile_avatar(); // 자료형이 MultipartFile로 가져온겁니다.

		// 오리지널은 이름 그대로 넣을꺼에요. 올린 이름 그대로. 실제로 여러분들이 저장하는 곳 위치에서는 이름을 바꿀거에요.
		if (!uploadfile.isEmpty()) { // 파일을 선택했다면
			String fileName = uploadfile.getOriginalFilename();// 원래 파일명, 이곳에 집어넣습니다.
			member.setM_ORIGINAL(fileName); // 원래 파일명 저장

			// 새로운 폴더 이름 : 오늘 년-월-일 , 저장할땐 이름이 겹치는 경우가 있기때문에.. 첫번째는 오늘날짜에 대한 연월일을 구합니다.
			// 새로운 폴더 이름 : 오늘 - 년 - 월 - 일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
			int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
			int date = c.get(Calendar.DATE); // 오늘 일 구합니다.

			String homedir = membersaveFolder + year + "-" + month + "-" + date;
			System.out.println(homedir);
			File path1 = new File(homedir);
			if (!(path1.exists())) {
				path1.mkdir(); // 새로운 폴더를 생성
			}

			// 난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);

			/**** 확장자 구하기 시작 ****/
			int index = fileName.lastIndexOf(".");
			// 문자열에서 특정 문자열의 위치 값 (index)를 반환한다.
			// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			// lastIndextOf는 마지막ㄷ으로 발견되는 문자열의 index를 반환합니다.
			// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			/**** 확장자 구하기 끝 ****/

			// 새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			// 오라클 디비에 저장될 파일 명
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// tranferTo 업로드한 파일을 매개변수 경로에저장하는것! 매우 중요합니다.
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(membersaveFolder + fileDBName));

			// bbs.연월일. 난수발생. fileExtension(확장자 구한것)= 생성된 새로운 파일명
			// 바뀐 파일명 저장
			member.setM_FILE(fileDBName);
		}
		loginmemberservice.insert(member); // 저장 메서드 호출

		// Member member 정보를 받아왔어요 간단하게 command로.. 간편
		// 우린 더이상 new MemberDAO를 쓰지 않습니다.
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 비밀번호 암호화 추가
		String encPassword = passwordEncoder.encode(member.getM_PASS());
		System.out.println(encPassword);
		member.setM_PASS(encPassword);

		out.println("<script>");

		// 삽입이 된 경우

		out.println("alert('신규사원 등록이 완료되었습니다.');");
		out.println("location.href='main';");

		out.println("</script>");
		out.close();

	}

	// 수정폼
	@RequestMapping(value = "/update.hr", method = RequestMethod.GET)
	public String member_update(HttpSession session, Model m) throws Exception {
		// @RequestParam(value="M_CODE",required=false)String user_id
		String id = (String) session.getAttribute("M_CODE");
		Member member = loginmemberservice.member_info(id);

		// mv.setViewName("member/updateForm2");
		// mv.setViewName("member/joinForm");

		m.addAttribute("page", "member/updateForm.jsp");
		m.addAttribute("info", member);

		return "home";
	}

	// 수정처리
	@RequestMapping(value = "/updateProcess.net", method = RequestMethod.POST)
	public void updateProcess(Member member, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = loginmemberservice.update(member);
		System.out.println(member);
		System.out.println("결과는 " + result);
		out.println("<script>");

		// 삽입이 된 경우
		if (result == 1) {
			out.println("alert('수정되었습니다.')");
			out.println("location.href='update.hr';");
		} else {
			out.println("alert('사원 정보 수정에 실패했습니다.');");
			out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지 되어 있습니다
		}
		out.println("</script>");
		out.close();
	}

	// 관리자의 정보수정 관리자의 정보수정
	// 수정폼
	@RequestMapping(value = "/update_admin.hr", method = RequestMethod.GET)
	public String member_update_admin(HttpServletRequest request, Model m) throws Exception {

		String code = request.getParameter("code");

		Member member = loginmemberservice.member_info(code);

		// mv.setViewName("member/updateForm2");
		// mv.setViewName("member/joinForm");

		m.addAttribute("page", "member/updateForm_admin.jsp");
		m.addAttribute("info", member);

		return "home";
	}

	// 관리자- 수정처리
	@RequestMapping(value = "/admin_updateProcess.net", method = RequestMethod.POST)
	public void updateProcess_admin(Member member, HttpServletResponse response) throws Exception {

		MultipartFile uploadfile = member.getProfile_avatar(); // 자료형이 MultipartFile로 가져온겁니다.

		// 오리지널은 이름 그대로 넣을꺼에요. 올린 이름 그대로. 실제로 여러분들이 저장하는 곳 위치에서는 이름을 바꿀거에요.
		if (!uploadfile.isEmpty()) { // 파일을 선택했다면
			String fileName = uploadfile.getOriginalFilename();// 원래 파일명, 이곳에 집어넣습니다.
			member.setM_ORIGINAL(fileName); // 원래 파일명 저장

			// 새로운 폴더 이름 : 오늘 년-월-일 , 저장할땐 이름이 겹치는 경우가 있기때문에.. 첫번째는 오늘날짜에 대한 연월일을 구합니다.
			// 새로운 폴더 이름 : 오늘 - 년 - 월 - 일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
			int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
			int date = c.get(Calendar.DATE); // 오늘 일 구합니다.

			String homedir = membersaveFolder + year + "-" + month + "-" + date;
			System.out.println(homedir);
			File path1 = new File(homedir);
			if (!(path1.exists())) {
				path1.mkdir(); // 새로운 폴더를 생성
			}

			// 난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);

			/**** 확장자 구하기 시작 ****/
			int index = fileName.lastIndexOf(".");
			// 문자열에서 특정 문자열의 위치 값 (index)를 반환한다.
			// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			// lastIndextOf는 마지막ㄷ으로 발견되는 문자열의 index를 반환합니다.
			// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
			System.out.println("index = " + index);

			String fileExtension = fileName.substring(index + 1);
			System.out.println("fileExtension = " + fileExtension);
			/**** 확장자 구하기 끝 ****/

			// 새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			System.out.println("refileName = " + refileName);

			// 오라클 디비에 저장될 파일 명
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);

			// tranferTo 업로드한 파일을 매개변수 경로에저장하는것! 매우 중요합니다.
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(membersaveFolder + fileDBName));

			// bbs.연월일. 난수발생. fileExtension(확장자 구한것)= 생성된 새로운 파일명
			// 바뀐 파일명 저장
			member.setM_FILE(fileDBName);
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = loginmemberservice.update_admin(member);
		System.out.println(member);
		System.out.println("결과는 " + result);
		out.println("<script>");

		// 삽입이 된 경우
		if (result == 1) {
			out.println("alert('수정되었습니다.')");
			out.println("location.href='list.hr';");
		} else {
			out.println("alert('사원 정보 수정에 실패했습니다.');");
			out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지 되어 있습니다
		}
		out.println("</script>");
		out.close();
	}
	
	
	// 관리자 - 퇴사처리	
	// 수정폼
		@RequestMapping(value = "/update_resignation.hr", method = RequestMethod.GET)
		public String member_update_res(HttpServletRequest request, Model m) throws Exception {

			String code = request.getParameter("code");
			Member member = loginmemberservice.member_info(code);
			
			// mv.setViewName("member/updateForm2");
			// mv.setViewName("member/joinForm");

			m.addAttribute("page", "member/updateForm_res.jsp");
			m.addAttribute("info", member);

			return "home";
		}

		// 수정처리
		@RequestMapping(value = "/resignationProcess.net", method = RequestMethod.POST)
		public void resignationProcess(Member member, HttpServletResponse response) throws Exception {

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			int result = loginmemberservice.update_res(member);

			out.println("<script>");

			// 삽입이 된 경우
			if (result == 1) {
				out.println("alert('퇴사처리되었습니다.')");
				out.println("location.href='list.hr';"); //완료후 퇴사자 목록으로 이동 변경
			} else {
				out.println("alert('퇴사처리에 실패했습니다.');");
				out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지 되어 있습니다
			}
			out.println("</script>");
			out.close();
		}

	
	
	// 사원 조회 리스트
	@GetMapping(value = "/list.hr")
	public String member_list(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "3", required = false) int limit, ModelAndView mv,
			@RequestParam(value = "search_field", defaultValue = "-1") int index,
			@RequestParam(value = "search_word", defaultValue = "") String search_word, Model m) throws Exception {

		List<Member> list = null;
		int listcount = 0;

		list = loginmemberservice.getSearchList2(index, search_word, page, limit);
		listcount = loginmemberservice.getSearchListCount(index, search_word); // 총 리스트 수를 받아옴

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		m.addAttribute("page", "member/member_list.jsp");
		m.addAttribute("mem_info", list);
		mv.addObject("maxpage", maxpage);
		m.addAttribute("startpage", startpage);
		m.addAttribute("endpage", endpage);
		m.addAttribute("listcount", listcount);
		m.addAttribute("limit", limit);
		m.addAttribute("search_field", index);
		m.addAttribute("search_word", search_word);
		System.out.println(list);
		return "home";
	}
	
	// 퇴사 사원 조회 리스트
		@GetMapping(value = "/res_list.hr")
		public String resign_list(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
				@RequestParam(value = "limit", defaultValue = "3", required = false) int limit, ModelAndView mv,
				@RequestParam(value = "search_field", defaultValue = "-1") int index,
				@RequestParam(value = "search_word", defaultValue = "") String search_word, Model m) throws Exception {

			List<Member> list = null;

			list = loginmemberservice.resSearchList(index, search_word, page, limit);
			
			m.addAttribute("page", "member/res_list.jsp");
			m.addAttribute("mem_info", list);
			m.addAttribute("limit", limit);
			m.addAttribute("search_field", index);
			m.addAttribute("search_word", search_word);
			System.out.println(list);
			return "home";
		}


}
