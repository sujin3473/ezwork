package com.ez.work.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ez.work.service.ReserveService;

@Service
public class ResetMeeting {
	
	@Autowired
	private ReserveService reserveService;
	
	@Scheduled(cron="0 0 20 * * *")
	public void ResetMRoom(){
		reserveService.truncate();
		System.out.println("미팅룸 예약이 리셋되었습니다.");
	}
	
}