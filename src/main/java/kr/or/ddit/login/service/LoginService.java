package kr.or.ddit.login.service;

import kr.or.ddit.login.model.UserVo;

public interface LoginService {
	
	UserVo getUser(String userid);
}
