package kr.or.ddit.login.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.model.model.User;
import kr.or.ddit.model.model.UserVo;
import kr.or.ddit.model.repository.UserRepository;

@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource(name="userRepository")
	private UserRepository userRepository;

	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserVo getUser(String userid) {	
		return modelMapper.map(userRepository.getOne(userid), UserVo.class);		
	}
}
