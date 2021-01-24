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

import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserRepository;

@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService, InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource(name="userRepository")
	private UserRepository userRepository;

	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserVo getUser(String userid) {	
		return modelMapper.map(userRepository.getOne(userid), UserVo.class);		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//test 자료
		userRepository.save(new User("james", "jamesPass", "제임스", "사람", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("moon", "moonPass", "문", "달", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("brown", "brownPass", "브라운", "곰", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("sally", "sallyPass", "샐리", "병아리", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("cony", "conyPass", "코니", "토끼", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("leonard", "leonardPass", "레너드", "개구리", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("edward", "edwardPass", "에드워드", "애벌레", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("jessica", "jessicaPass", "제시카", "고양이", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("boss", "bossPass", "보스", "사람", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("choco", "chocoPass", "초코", "곰2", "대전시 중구 중앙로 76", "4층", new Date()));
		userRepository.save(new User("pangyo", "pangyoPass", "팡요", "판다", "대전시 중구 중앙로 76", "4층", new Date()));
	}
}
