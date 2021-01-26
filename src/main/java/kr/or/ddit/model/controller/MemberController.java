package kr.or.ddit.model.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.model.model.User;
import kr.or.ddit.model.service.UserService;

@RequestMapping("member")
@Controller
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("list")
	public String list(SearchCondition searchCondition, Model model) {
		model.addAttribute("userList", userService.userPagingList(searchCondition));
		return "member/list";
	}
	
	@RequestMapping(path = "regist", method = RequestMethod.GET )
	public String regist() {
		return "member/regist";
	}
	
	@RequestMapping(path = "member", method = RequestMethod.GET )
	public String user(String userid, Model model) {
		
		model.addAttribute("member", userService.findById(userid));
		
		return "member/member";
	}
}
