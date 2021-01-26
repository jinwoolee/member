package kr.or.ddit.model.service;

import java.util.List;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.model.model.UserVo;

public interface UserService {

	List<UserVo> userPagingList(SearchCondition searchCondition);
	
	UserVo findById (String userid);
}
