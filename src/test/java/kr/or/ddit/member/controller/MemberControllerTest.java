package kr.or.ddit.member.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.or.ddit.member.model.UserVo;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void listTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/member/list"))
								.andExpect(view().name("member/list"))
								.andExpect(status().isOk())
								.andDo(print())
								.andReturn();
		
		HttpSession session = mvcResult.getRequest().getSession();
		UserVo userVo = (UserVo)session.getAttribute("S_USER");
		assertNotNull(userVo);
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	void registTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/member/regist"))
								.andExpect(view().name("member/regist"))
								.andExpect(status().isOk())
								.andDo(print())
								.andReturn();
	}

}
