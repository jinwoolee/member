package kr.or.lgdlab.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.annotation.Resource;

import kr.or.lgdlab.member.model.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.lgdlab.common.SearchCondition;
import kr.or.lgdlab.member.model.Member;
import kr.or.lgdlab.member.service.MemberService;

/**
 *  Description : 멤버 컨트롤러
 *
 *  수정일       수정자       수정내용
 *  ---------   ---------   -------------------------------
 *  2021-08-12  ljw         최초생성
 *
 *  Copyright (C) by LG Discovery Lab. All right reserved.
 */
@RequestMapping("member")
@Controller
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@Resource(name="memberService")
	private MemberService memberService;

	/**
	 * 멤버 리스트 페이징 조회
	 * @param searchCondition : 검색조건
	 * @param model : 모델
	 * @return : view name (멤버 리스트)
	 */
	@RequestMapping("list")
	public String list(SearchCondition searchCondition, Model model) {
		//model.addAttribute("memberList", memberService.userPagingList(searchCondition));
		model.addAllAttributes(memberService.userPagingList(searchCondition));
		return "member/list";
	}

	/**
	 * 멤버 등록 뷰
	 * @return : view name (멤버 등록 뷰)
	 */
	@RequestMapping(path = "regist", method = RequestMethod.GET )
	public String regist() {
		return "member/regist";
	}

	/**
	 * 멤버 등록
	 * @param member : 등록 멤버 객체
 	 * @param picture : 멤버 사진
	 * @return : view name(등록 멤버 상세 조회 페이지)
	 */
	@RequestMapping(path = {"regist", "modify"}, method = RequestMethod.POST )
	public String regist(Member member, MultipartFile picture) {
		
		log.debug("regist member : {}", member);
		
		try {
			String filename = null;
			String realFilename = null;
			if(picture != null && picture.getSize() > 0) {
				filename = picture.getOriginalFilename();
				realFilename = UUID.randomUUID().toString();
				member.setRealFilename(realFilename);
				member.setFilename(filename);
				
				picture.transferTo(new File("d:\\upload\\" + realFilename));
			}
			log.debug("before save");
			memberService.save(member);
			log.debug("after save");
		}catch(Exception e) {
			return "redirect:/member/view?memberid=" + member.getMemberid();
		}
		
		return "redirect:/member/view?memberid=" + member.getMemberid();
	}

	/**
	 * 멤버 상세 조회
	 * @param memberid : 상세 조회 멤버 아이디
	 * @param model : 모델
	 * @return : view name (멤버 조회)
	 */
	@RequestMapping(path = "view", method = RequestMethod.GET )
	public String member(String memberid, Model model) {
		log.debug("member/member memberid : {}", memberid);
		model.addAttribute("member", memberService.findById(memberid));
		
		return "member/view";
	}

	/**
	 * 멤버 이미지 요청
	 * @param memberid : 이미지 요청 멤버 아이디
	 * @param os : outputStream (응답 생성 스트림)
	 */
	@RequestMapping(path="profile", method=RequestMethod.GET)
	public void profile(String memberid, OutputStream os) {
		MemberVo memberVo = memberService.findById(memberid);
		
		try {
			FileInputStream fis = new FileInputStream("d:\\upload\\" + memberVo.getRealFilename());
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

	/**
	 * 멤버 수정 화면
	 * @param memberid : 수정 요청 멤버 아이디
	 * @param model : 모델
	 * @return : view name (수정 화면)
	 */
	@RequestMapping(path = "modify", method = RequestMethod.GET )
	public String modify(String memberid, Model model) {
		model.addAttribute("member", memberService.findById(memberid));
		return "member/modify";
	}
}
