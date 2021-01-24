package kr.or.ddit.login.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.login.service.LoginService;
import kr.or.ddit.user.model.UserVo;

@RequestMapping("login")
@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping("view")
	public String view() {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(UserVo userVo) {
				
		UserVo getUser = loginService.getUser(userVo.getUserid());
		log.debug("getUser : {}", getUser);
		
		if(userVo.getPass().equals(getUser.getPass())) {
			return "redirect:/member/list";
		}

		return "login";		
	}
}
