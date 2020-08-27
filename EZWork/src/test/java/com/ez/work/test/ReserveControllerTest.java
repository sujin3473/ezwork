package com.ez.work.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ez.work.domain.MeetingRoom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ReserveControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveControllerTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("setup~!");
	}
	
	@Test
	public void testMybatis() throws Exception {
		logger.info("===== testMybatis() =====");
		
		try {
			mockMvc.perform(post("/ReserveAction.res")
				   .param("RNAME","Ocean")
				   .param("USER_TEL","010-123-1234")
				   .param("MSUBJECT","7월 팀미팅")
				   .param("MTIME","12")
				   .param("USER_NAME","EMP202002"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("page"))
			.andExpect(view().name("home"));
			logger.info("~~~ 수행 성공 ~~~");
		} catch(Exception e) {
			logger.error(">>> 수행 실패: " + e.getMessage());
		}
	}
}

