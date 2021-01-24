package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.user.model.UserVo;

public interface UserService {

	List<UserVo> userPagingList(SearchCondition searchCondition);
}
