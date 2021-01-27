package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.member.model.User;
import kr.or.ddit.member.model.UserVo;

public interface UserService {

	List<UserVo> userPagingList(SearchCondition searchCondition);
	
	UserVo findById (String userid);

	void save(User user);
}
