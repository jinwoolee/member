package kr.or.ddit.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.member.model.User;
import kr.or.ddit.member.model.UserVo;
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
	
	@RequestMapping(path = "regist", method = RequestMethod.GET )
	public String regist() {
		return "member/regist";
	}
	
	@RequestMapping(path = {"regist", "modify"}, method = RequestMethod.POST )
	public String regist(User user, MultipartFile picture) {
		
		log.debug("regist user : {}", user);
		
		try {
			String filename = null;
			String realFilename = null;
			if(picture.getSize() > 0) {
				filename = picture.getOriginalFilename();
				realFilename = UUID.randomUUID().toString();
				user.setRealFilename(realFilename);
				user.setFilename(filename);
				
				picture.transferTo(new File("d:\\upload\\" + realFilename));
			}
			userService.save(user);
		}catch(Exception e) {
			return "redirect:/member/member?userid=" + user.getUserid();
		}
		
		return "redirect:/member/member?userid=" + user.getUserid();
	}
	
	@RequestMapping(path = "member", method = RequestMethod.GET )
	public String user(String userid, Model model) {
		
		model.addAttribute("member", userService.findById(userid));
		
		return "member/member";
	}
	
	@RequestMapping(path="profile", method=RequestMethod.GET)
	public void profile(String userid, OutputStream os) {
		UserVo userVo = userService.findById(userid);
		
		try {
			FileInputStream fis = new FileInputStream("d:\\upload\\" + userVo.getRealFilename());
			byte[] buff = new byte[512];
			while(fis.read(buff) != -1) {
				os.write(buff);
			}
			fis.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(path = "modify", method = RequestMethod.GET )
	public String modify(String userid, Model model) {
		model.addAttribute("member", userService.findById(userid));
		return "member/modify";
	}
}
