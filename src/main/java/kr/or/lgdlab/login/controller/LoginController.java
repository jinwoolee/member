package kr.or.lgdlab.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import kr.or.lgdlab.member.model.MemberVo;
import kr.or.lgdlab.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("login")
@Controller
public class LoginController {

	@Resource(name="memberService")
	private MemberService memberService;
	
	@RequestMapping("view")
	public String view() {
		log.debug("/login/view");
		return "login";
	}
	
	@RequestMapping("login")
	public String login(MemberVo memberVo, HttpSession session) {
				
		MemberVo getUser = memberService.findById(memberVo.getMemberid());
		log.debug("getUser : {}", getUser);
		
		if(memberVo.getPass().equals(getUser.getPass())) {
			session.setAttribute("S_USER", getUser);
			return "redirect:/main";
		}

		return "login";		
	}
}
