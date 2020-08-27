package com.ez.work.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ez.work.domain.EventBoard;
import com.ez.work.service.EventBoardService;

@Controller
public class EventBoardController {
	@Autowired
	private EventBoardService eventboardService;
	
	@Value("${savefoldername}")
	private String saveFolder;

	// 글 목록 보기
	@RequestMapping(value = "/BoardList.ev")
	public ModelAndView boardList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			ModelAndView mv, Model m) {
		int limit = 10; 
		int listcount = eventboardService.getListCount(); 

		// 총 페이지수
		int maxpage = (listcount + limit - 1) / limit;

		// 시작 페이지수
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 마지막 페이지수
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<EventBoard> boardlist = eventboardService.getBoardList(page, limit); // 리스트를 받아옴

		m.addAttribute("page", "eventboard/eventboard_list.jsp");
		mv.setViewName("home");
		mv.addObject("page1", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/EventBoardListAjax.ev")
	public Map<String, Object> eventBoardListAjax(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10", required = false) int limit, Model m) {

		int listcount = eventboardService.getListCount(); // 총 리스트 수를 받아옴

		// 총 페이지수
		int maxpage = (listcount + limit - 1) / limit;

		// 시작 페이지수
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 마지막 페이지수
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		List<EventBoard> boardlist = eventboardService.getBoardList(page, limit); // 리스트를 받아옴

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("boardlist", boardlist);
		map.put("limit", limit);

		return map;
	}

	// 글쓰기화면
	@GetMapping(value = "/BoardWrite.ev")
	public String board_write(HttpServletRequest request, Model m) {
		m.addAttribute("page", "eventboard/eventboard_write.jsp");
		return "home";
	}

	// 글저장
	@PostMapping(value = "/Board_write_ok.ev")
	public ModelAndView board_write_ok(EventBoard board, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:BoardList.ev");
		 MultipartFile uploadfile = board.getUploadfile();

	      if (!uploadfile.isEmpty()) {
	         String fileName = uploadfile.getOriginalFilename();// 원래 파일명
	         board.setEV_ORIGINAL(fileName); // 원래 파일명 저장

	         // 새로운 폴더 이름 : 오늘 - 년 - 월 - 일
	         Calendar c = Calendar.getInstance();
	         int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
	         int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
	         int date = c.get(Calendar.DATE); // 오늘 일 구합니다.
	         //String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
	         String homedir = saveFolder + year + "-" + month + "-" + date;
	         System.out.println(homedir);
	         File path1 = new File(homedir);
	         if (!(path1.exists())) {
	            path1.mkdir(); // 새로운 폴더를 생성
	         }

	         // 난수를 구합니다.
	         Random r = new Random();
	         int random = r.nextInt(100000000);

	         /* 확장자 구하기 시작 */
	         int index = fileName.lastIndexOf(".");
	         // 문자열에서 특정 문자열의 위치 값 (index)를 반환한다.
	         // indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
	         // lastIndextOf는 마지막ㄷ으로 발견되는 문자열의 index를 반환합니다.
	         // (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
	         System.out.println("index = " + index);

	         String fileExtension = fileName.substring(index + 1);
	         System.out.println("fileExtension = " + fileExtension);
	         /* 확장자 구하기 끝 */

	         // 새로운 파일명
	         String refileName = "bbs" + year + month + date + random + "." + fileExtension;
	         System.out.println("refileName = " + refileName);

	         // 오라클 디비에 저장될 파일 명
	         String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
	         System.out.println("fileDBName = " + fileDBName);

	         // transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
	         uploadfile.transferTo(new File(saveFolder + fileDBName));

	         // 바뀐 파일명 저장
	         board.setEV_FILE(fileDBName);
	      }
		eventboardService.insertBoard(board); // 저장 메서드 호출
		return mv;
	}
	
	//글 상세보기
	@GetMapping("/BoardDetailAction.ev")
	public ModelAndView Detail(int num, ModelAndView mv, HttpServletRequest request, Model m) {
		EventBoard board = eventboardService.getDetail(num);
		if (board == null) {
			System.out.println("상세보기 실패");
			m.addAttribute("page", "error/error.jsp");
			mv.setViewName("home");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "상세보기 실패입니다.");
		} else {
			System.out.println("상세보기 성공");
			m.addAttribute("page", "eventboard/eventboard_view.jsp");
			mv.setViewName("home");
			mv.addObject("boarddata", board);
		}
		return mv;
	}
	
	//글 수정하기 뷰페이지
	@GetMapping("/BoardModifyView.ev")
	public ModelAndView BoardModifyView(int num, ModelAndView mv, HttpServletRequest request, Model m) {
		System.out.println(num);
		EventBoard boarddata = eventboardService.getDetail(num);
		if (boarddata == null) {
			System.out.println("(수정)상세보기 실패");
			m.addAttribute("page", "error/error.jsp");
			mv.setViewName("home");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "(수정)상세보기 실패입니다.");
			return mv;
		}
		System.out.println("(수정)상세보기 성공");
		m.addAttribute("page", "eventboard/eventboard_modify.jsp");
		mv.setViewName("home");
		mv.addObject("boarddata", boarddata);
		return mv;
	}
	
	//글 수정하기 기능
	@PostMapping("BoardModifyAction.ev")
	public ModelAndView BoardModifyAction(String before_file, EventBoard board, String check, ModelAndView mv, HttpServletRequest request,
			HttpServletResponse response, Model m) throws Exception {
		boolean usercheck = eventboardService.isBoardWriter(board.getEV_NO(), board.getEV_PASS());

		// 비밀번호가 다른 경우
		if (usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		MultipartFile uploadfile = board.getUploadfile();
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";

		System.out.println("check = " + check);
		if (check != null && !check.contentEquals("")) { // 기존 파일 그대로
			board.setEV_ORIGINAL(check);
			// <input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_fILE}">
			// 위 문장 때문에 board.setBOARD_FILE()은 자동 저장 됩니다.
		} else {
			if (uploadfile != null && !uploadfile.isEmpty()) {// 파일 변경한 경우
				System.out.println("파일 변경 되었습니다.");

				String fileName = uploadfile.getOriginalFilename(); // 원래 파일 명
				board.setEV_ORIGINAL(fileName);

				String fileDBName = fileDBName(fileName, saveFolder);

				// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
				uploadfile.transferTo(new File(saveFolder + fileDBName));

				// 바뀐 파일명으로 저장
				board.setEV_FILE(fileDBName);
			} else { // uploadfile.isEmpty() 인 경우 - 파일 선택하지 않은 경우
				System.out.println("선택 파일 없습니다.");
				// <input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_FILE}">
				// 위 태그에 값이 있다면 ""로 값을 변경합니다.
				board.setEV_FILE(""); // ""로 초기화 합니다.
				board.setEV_ORIGINAL(""); // ""로 초기화 합니다.
			}
		}

		// DAO에서 수정 메서드 호출하여 수정합니다.
		int result = eventboardService.boardModify(board);

		// 수정에 실패한 경우
		if (result == 0) {
			System.out.println("게시판 수정 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "게시판 수정 실패");
		} else { // 수정 성공의 경우
			System.out.println("게시판 수정 완료");
			
			// 수정전에 파일이 있고 새로운 파일을 선택한 경우는 삭제할 목록을 테이블에 추가합니다.
			//if(!before_file.equals("") && !before_file.equals(board.getEV_FILE())) {
			//	eventboardService.insert_deleteFile(before_file);
		//	}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 되었습니다.');");
			out.println("location.href="+"'BoardDetailAction.ev?num="+board.getEV_NO()+"';");
			out.println("</script>");
			out.close();
		}
		return mv;

	}

	// DB에 들어갈 내용 가공
	private String fileDBName(String fileName, String saveFolder) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);

		String homedir = saveFolder + year + "-" + month + "-" + date;
		System.out.println(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir(); // 새로운 폴더를 생성
		}

		Random r = new Random();
		int random = r.nextInt(100000000);

		// 확장자 구하기 시작
		int index = fileName.lastIndexOf(".");
		System.out.println("index = " + index);

		String fileExtension = fileName.substring(index + 1);
		System.out.println("fileExtension = " + fileExtension);

		// 새로운 파일명
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		System.out.println("refileName = " + refileName);

		// 오라클 디비에 저장될 파일 명
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		System.out.println("fileDBName = " + fileDBName);
		return fileDBName;
	}

	
	//글 삭제
	@RequestMapping(value = "/BoardDeleteAction.ev")
	public ModelAndView BoardDeleteAction(String EV_PASS, int num,
			ModelAndView mv, 
			HttpServletResponse response,
			HttpServletRequest request
			) throws Exception{
		System.out.println(num);
		// 글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		// 입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제합니다.
		boolean usercheck = eventboardService.isBoardWriter(num, EV_PASS);
		
		// 비밀번호 일치하지 않은 경우
		if (usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 비밀번호 일치하는 경우 삭제 처리 합니다.
		int result = eventboardService.boardDelete(num);
		
		// 삭제처리 실패한 경우
			if(result == 0) {
				System.out.println("게시판 삭제 실패");
				mv.setViewName("error/error");
				mv.addObject("url",request.getRequestURL());
				mv.addObject("message", "삭제 실패");
				return mv;
			}
			
		// 삭제처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
			System.out.println("게시판 삭제 성공");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 되었습니다.');");
			out.println("location.href='BoardList.ev';");
			out.println("</script>");
			out.close();
			return null;
	}
	
	@GetMapping("BoardFileDown.ev")
	public void BoardFileDown(String filename, HttpServletRequest request, String original,
			HttpServletResponse response) throws Exception {

		String savePath = "resources/upload";

		// 서블릿의 실행 환경 정보를 담고 있는 객체를 리턴합니다.
		ServletContext context = request.getSession().getServletContext();
		System.out.println(context);
		String sDownloadPath = context.getRealPath(savePath);

		// String sFilePath = sDownloadPath + "\\" + fileName;
		// "\" 추가하기 위해 "\\" 사용합니다.
		String sFilePath = sDownloadPath + "/" + filename;
		System.out.println(sFilePath);

		byte b[] = new byte[4096];

		// sFilePath에 있는 파일 MimeType을 구해옵니다
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);

		if (sMimeType == null)
			sMimeType = "application/octet-stream";

		response.setContentType(sMimeType);

		// - 이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다.
		String sEncoding = new String(original.getBytes("utf-8"), "ISO-8859-1");
		System.out.println(sEncoding);

		/*
		 * Content-Dispostion: attachment : 브라우저는 해당 Content를 처리하지않고, 다운로드 하게 됩니다.
		 * 
		 */
		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
		//프로젝트 properties - Project facets에서 자바버전 1.8로 수정

		try (
				// 웹 브라우저로의 출력 스트림 생성합니다.
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				// sFilePath로 지정한 파일에 대한 입력 스트림을 생성합니다.
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));) {
			int numRead;
			// read(b, 0, b.length) : 바이트 배열 b의 0번부터 b.length
			// 크기만큼 읽어옵니다.
			while ((numRead = in.read(b, 0, b.length)) != -1) { // 읽을 데이터가 존재하는 경우
				// 바이트 배열 b의 0번 부터 numRead크기 만큼 브라우저로 출력
				out2.write(b, 0, numRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
