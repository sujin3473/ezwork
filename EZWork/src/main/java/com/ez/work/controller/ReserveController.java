package com.ez.work.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ez.work.domain.MeetingRoom;
import com.ez.work.service.ReserveService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/*수진*/

@Controller
public class ReserveController {
	@Autowired
	private ReserveService reserveService;

	@ResponseBody
	@RequestMapping(value = "/meeting.res")
	public ModelAndView inbox(HttpServletRequest request, ModelAndView mv) {
		List<MeetingRoom> list = null;
		list = reserveService.getList();
		mv.setViewName("home");
		mv.addObject("page", "reserve/meeting.jsp");
		mv.addObject("list", list);
		return mv;
	}
/*
	@ResponseBody
	@RequestMapping(value = "/meetingAjax.res", produces = "application/json;charset=utf-8")
	public Map<String, Object> inboxAjax() {
		List<MeetingRoom> list = null;
		list = reserveService.getList();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		System.out.println("listmeetingAjax : " + list.get(0).getMSUBJECT());
		
		
		
		return map;
	}
*/
	//@ResponseBody
	@RequestMapping(value = "/meetingAjax.res")
	public void inboxAjax(HttpServletResponse response) throws Exception {
		JsonObject object = new JsonObject();
		List<MeetingRoom> list = null;
		list = reserveService.getList();
		JsonElement je = new Gson().toJsonTree(list);
		object.add("list", je);
		Gson gson = new Gson();
		String json = gson.toJson(object);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(json);
		System.out.println(json);
	}
	
	@PostMapping("/ReserveAction.res")
	public ModelAndView mailadd(MeetingRoom meeting, ModelAndView mv, HttpServletResponse response) throws Exception {
		System.out.println("ReserveAction starts");

		System.out.println("meeting user info: " + meeting.getUSER_NAME());
		System.out.println("meeting subject: " + meeting.getMSUBJECT());

		int result = reserveService.insertM(meeting);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		if (result == 0) {
			mv.setViewName("home");
			mv.addObject("page", "reserve/meeting.jsp");
			return mv;
		} else {
			out.println("<script>alert('이미 예약된 시간입니다. 다른 회의실을 이용해주세요 ^ ^'); history.back()</script>");
			return null;
		}
	}
}

