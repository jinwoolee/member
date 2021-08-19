package kr.or.lgdlab.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import kr.or.lgdlab.member.model.MemberVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.or.lgdlab.common.SearchCondition;
import kr.or.lgdlab.common.SearchType;
import kr.or.lgdlab.member.model.Member;
import kr.or.lgdlab.member.repository.MemberRepository;

@Slf4j
@Transactional
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Description : 사용자 페이지 리스트 조회
	 * @param sc : 검색 조건
	 * @return : Map (페이지 리스트, 페이지 네비게이션 문자열)
	 */
	@Override
	public Map<String, Object> userPagingList(SearchCondition sc) {
		//.by(Sort.Direction.DESC, "userid")
		//List<Member> userList = memberRepository.findAll(Sort.by(Order.asc("alias"), Order.asc("userid")));
		//List<Member> userList = memberRepository.findByOrderByUseridAsc();

		Map<String, Object> resultMap = new HashMap<>();
		Page<Member> userList = null;

		long memberTotalCount = memberRepository.count();

		
		if(sc.getSearchType() == SearchType.none) {
			userList = memberRepository.findAll(PageRequest.of(sc.getPage(), sc.getPageSize()));
		}
		else if(sc.getSearchType() == SearchType.memberid) {
			userList = memberRepository.findByMemberidStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}
		else if(sc.getSearchType() == SearchType.membernm) {
			userList = memberRepository.findByMembernmStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}
		else if(sc.getSearchType() == SearchType.alias) {
			userList = memberRepository.findByAliasStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}

		List<MemberVo> memberVoList = userList.stream()
											.map(user-> modelMapper.map(user, MemberVo.class))
											.collect(Collectors.toList());

		String pageNavigation = makePageNavigation(memberTotalCount, sc.getPageSize(), "/member/list");

		resultMap.put("pageNavigation", pageNavigation);
		resultMap.put("memberList", memberVoList);
		resultMap.put("memberTotalCount", memberTotalCount);
		resultMap.put("pages", (long)Math.ceil((double)memberTotalCount / sc.getPageSize()));

		return resultMap;
	}

	/**
	 * Description : 페이지 네비게이션 생성
	 * @param memberTotalCount : 전체 콘텐츠 수
	 * @param pageSize : 페이지 사이즈
	 * @param url : 이동 url
	 * @return : 생성된 페이지 네비게이션 문자열
	 */
	private String makePageNavigation(long memberTotalCount, long pageSize, String url ) {
		String pageNavigation = "";
		long totalPage = (long)Math.ceil((double)memberTotalCount / pageSize);
		log.debug("memberTotalCount : {} / pageSize : {} / totalPage : {}", memberTotalCount, pageSize, totalPage);
		for(int i = 0; i < totalPage; i++){
			int page = i+1;
			pageNavigation += "<li><a href=\"" + url + "?page=" + i + "\">"+ page + "</a></li>";
		}
		return pageNavigation;
	}

	/**
	 * Description :사용자 조회
	 * @param userid : 사용자 아이디
	 * @return : 조회된 사용자(uservo)
	 */

	@Override
	public MemberVo findById(String userid) {
		return modelMapper.map(memberRepository.findById(userid).get(), MemberVo.class);
	}

	/**
	 * 사용자 입력
	 * @param member 사용자 도메인 객체
	 */
	@Override
	public void save(Member member) {
		memberRepository.save(member);
	}
	
	
}
