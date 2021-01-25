package kr.or.ddit.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserSpecs;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Resource(name="userRepository")
	private UserRepository userRepository;
	
	@Test
	public void findByOrderByUseridAsc() {
		/*****GIVEN*****/

		/*****WHEN*****/
		List<User> userList = userRepository.findByOrderByUseridAsc();

		/*****THEN*****/
		assertEquals(11, userList.size());
	}
	
	@Test
	public void findByUseridStartingWith() {
		/*****GIVEN*****/
		String userid = "b";
		
		//페이지는 0부터 시작한다
		//Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "userid"));
		Pageable pageable = PageRequest.of(0, 5);
		
		/*****WHEN*****/
		Page<User> usersPage =userRepository.findByUseridStartingWith(userid, pageable);
		log.debug("usersPage.getSize() : {} ", usersPage.getSize());
		log.debug("usersPage.getNumber() : {} ", usersPage.getNumber());
		log.debug("usersPage.getNumberOfElements() : {} ", usersPage.getNumberOfElements());
		log.debug("usersPage.getContent() : {} ", usersPage.getContent());
		List<User> userList = usersPage.getContent();

		/*****THEN*****/
		//userid가 b로 시작하는 사용자는 brown, boss 두명이다
		assertEquals(2, userList.size());
	}
	
	@Test
	public void findByUsernmStartingWith() {
		/*****GIVEN*****/
		String usernm = "브";
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "usernm"));
		
		/*****WHEN*****/
		Page<User> userList =userRepository.findByUsernmStartingWith(usernm, pageable);
		
		for(User user : userList) {
			log.debug("user : {}", user );			
		}

		/*****THEN*****/
		assertEquals(1, userList.getContent().size());
	}
	
	@Test
	public void findByAliasStartingWith() {
		/*****GIVEN*****/
		String alias = "사람";
		
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "alias"));
		
		/*****WHEN*****/
		Page<User> userList =userRepository.findByAliasStartingWith(alias, pageable);

		/*****THEN*****/
		assertEquals(2, userList.getContent().size());
	}
	
	@Test
	public void findAll() {
		/*****GIVEN*****/

		/*****WHEN*****/
		List<User> userList = userRepository.findAll();

		/*****THEN*****/
		assertEquals(11, userList.size());
	}
	
	@Test
	public void findBySpec() {
		/***Given***/
		String usernm = "스";
		
		/***When***/
		List<User> users = userRepository.findAll(UserSpecs.usernmLike(usernm));
		
		/***Then***/
		assertEquals(2, users.size());
	}

}
