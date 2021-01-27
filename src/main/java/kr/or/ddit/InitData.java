package kr.or.ddit;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import kr.or.ddit.member.model.User;
import kr.or.ddit.member.repository.UserRepository;

@Component
public class InitData implements InitializingBean{
	
	@Resource(name="userRepository")
	private UserRepository userRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initData();
	}
	
	public void initData() {
		//test 자료
		userRepository.deleteAll();
		userRepository.save(new User("james", "jamesPass", "제임스", "사람", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("moon", "moonPass", "문", "달", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("brown", "brownPass", "브라운", "곰", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("sally", "sallyPass", "샐리", "병아리", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("cony", "conyPass", "코니", "토끼", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("leonard", "leonardPass", "레너드", "개구리", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("edward", "edwardPass", "에드워드", "애벌레", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("jessica", "jessicaPass", "제시카", "고양이", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("boss", "bossPass", "보스", "사람", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("choco", "chocoPass", "초코", "곰2", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		userRepository.save(new User("pangyo", "pangyoPass", "팡요", "판다", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
	}
}
