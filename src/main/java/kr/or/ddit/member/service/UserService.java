package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.login.model.UserVo;

public interface UserService {

	List<UserVo> userPagingList(SearchCondition searchCondition);
}
