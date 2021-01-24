package kr.or.ddit.login.service;

import kr.or.ddit.user.model.UserVo;

public interface LoginService {
	
	UserVo getUser(String userid);
}
