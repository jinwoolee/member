package kr.or.lgdlab;

import java.util.Date;

import javax.annotation.Resource;

import kr.or.lgdlab.member.model.Member;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.lgdlab.member.repository.MemberRepository;

@Component
public class InitData implements InitializingBean{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initData();
	}
	
	public void initData() {
		//test 자료
		memberRepository.deleteAll();
		memberRepository.save(new Member("james", "jamesPass", "제임스", "사람", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("moon", "moonPass", "문", "달", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("brown", "brownPass", "브라운", "곰", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("sally", "sallyPass", "샐리", "병아리", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("cony", "conyPass", "코니", "토끼", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("leonard", "leonardPass", "레너드", "개구리", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("edward", "edwardPass", "에드워드", "애벌레", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("jessica", "jessicaPass", "제시카", "고양이", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("boss", "bossPass", "보스", "사람", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("choco", "chocoPass", "초코", "곰2", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
		memberRepository.save(new Member("pangyo", "pangyoPass", "팡요", "판다", "대전시 중구 중앙로 76", "4층", "34540", new Date()));
	}
}
