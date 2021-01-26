package kr.or.ddit.login.service;

import kr.or.ddit.model.model.UserVo;

public interface LoginService {
	
	UserVo getUser(String userid);
}
