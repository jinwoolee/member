package kr.or.ddit.user.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.model.model.UserVo;
import kr.or.ddit.model.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
	
	@Resource(name="userService")
	private UserService userService;

	@Test
	public void findByIdTest() {
		/***Given***/
		String userid = "sally";
		
		/***When***/
		UserVo userVo = userService.findById(userid);

		/***Then***/
		assertEquals("샐리", userVo.getUsernm());
	}
	
	@Test
	public void saveTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
	}

}
