package kr.or.lgdlab.member.service;

import java.util.Map;

import kr.or.lgdlab.common.SearchCondition;
import kr.or.lgdlab.member.model.Member;
import kr.or.lgdlab.member.model.MemberVo;

public interface MemberService {

	Map<String, Object> userPagingList(SearchCondition searchCondition);
	
	MemberVo findById (String userid);

	void save(Member member);
}
