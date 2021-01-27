package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;

@RequestMapping("login")
@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("view")
	public String view() {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(UserVo userVo, HttpSession session) {
				
		UserVo getUser = userService.findById(userVo.getUserid());
		log.debug("getUser : {}", getUser);
		
		if(userVo.getPass().equals(getUser.getPass())) {
			session.setAttribute("S_USER", getUser);
			return "redirect:/member/list";
		}

		return "login";		
	}
}
