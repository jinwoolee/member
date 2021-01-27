package kr.or.ddit.login.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;


//@WebMvcTest

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void viewTest() throws Exception {

		mockMvc.perform(get("/login/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
	
	@Test
	public void loginTest() throws Exception {
		
		mockMvc.perform(post("/login/login")
							.param("userid", "brown")
							.param("pass", "brownPass"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/member/list"))
				.andDo(MockMvcResultHandlers.print());
	}

}
