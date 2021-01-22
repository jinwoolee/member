package kr.or.ddit.member.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.member.service.UserService;

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
}
