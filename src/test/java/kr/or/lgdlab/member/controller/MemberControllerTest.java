package kr.or.lgdlab.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *  Description : 멤버 컨트롤러 테테스트 *
 *  수정일       수정자       수정내용
 *  ---------   ---------   -------------------------------
 *  2021-08-12  ljw         최초생성
 *
 *  Copyright (C) by LG Discovery Lab. All right reserved.
 */
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * @throws Exception
     */
    @Test
    void list() throws Exception {
        mockMvc.perform(get("/member/list"))
                .andExpect(model().attributeExists("memberList"))
                .andExpect(model().attributeExists("memberTotalCount"))
                .andExpect(model().attributeExists("pageNavigation"))
                .andExpect(view().name("member/list"))
                .andDo(print())
                .andDo(log());
    }

    /**
     * 멤버 등록 view 테스트
     */
    //@Test
    void regist() throws Exception {
        mockMvc.perform(get("/member/regist"))
                .andExpect(view().name("member/regist"))
                .andDo(print())
                .andDo(log());
    }

    /**
     * 멤버 등록 / 수정 테스트
     * @throws Exception
     */
    @Test
    void registTest() throws Exception {
        mockMvc.perform(post("/member/regist")
                        .param("memberid", "newMemberId")
                        .param("membernm", "신규유저")
                        .param("alias", "별명"))
                .andExpect(view().name("redirect:/member/view?memberid=newMemberId"))
                .andDo(print())
                .andDo(log());
    }

    /**
     * 멤버 상세 화면 테스트
     */
    @Test
    void member() throws Exception {
        mockMvc.perform(get("/member/view").param("memberid", "brown"))
                .andExpect(view().name("member/view"))
                .andDo(print())
                .andDo(log());
    }

    @Test
    void profile() {
    }

    /**
     * 멤버 수정화면 요청
     */
    @Test
    void modify() throws Exception {
        mockMvc.perform(get("/member/modify").param("memberid", "brown"))
                .andExpect(view().name("member/modify"))
                .andDo(print())
                .andDo(log());
    }
}