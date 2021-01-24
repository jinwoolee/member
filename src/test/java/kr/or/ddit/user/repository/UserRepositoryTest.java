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
		String userid = "br";
		
		Pageable pageable = PageRequest.of(1, 5, Sort.by(Sort.Direction.ASC, "userid"));
		
		/*****WHEN*****/
		Page<User> userList =userRepository.findByUseridStartingWith(userid, pageable);

		/*****THEN*****/
		assertEquals(1, userList.getSize());
	}
	
	@Test
	public void findByUsernmStartingWith() {
		/*****GIVEN*****/
		String usernm = "브";
		
		Pageable pageable = PageRequest.of(1, 5, Sort.by(Sort.Direction.ASC, "usernm"));
		
		/*****WHEN*****/
		Page<User> userList =userRepository.findByUsernmStartingWith(usernm, pageable);

		/*****THEN*****/
		assertEquals(1, userList.getSize());
	}
	
	@Test
	public void findByAliasStartingWith() {
		/*****GIVEN*****/
		String alias = "사람";
		
		Pageable pageable = PageRequest.of(1, 5, Sort.by(Sort.Direction.ASC, "alias"));
		
		/*****WHEN*****/
		Page<User> userList =userRepository.findByAliasStartingWith(alias, pageable);

		/*****THEN*****/
		assertEquals(2, userList.getSize());
	}
	
	@Test
	public void findAll() {
		/*****GIVEN*****/

		/*****WHEN*****/
		List<User> userList = userRepository.findAll();

		/*****THEN*****/
		assertEquals(11, userList.size());
	}

}
