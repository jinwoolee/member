package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.common.SearchType;
import kr.or.ddit.member.model.User;
import kr.or.ddit.member.model.UserVo;
import kr.or.ddit.member.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
	
	private static final String INSERT_USERID = "insertTest";
	
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
		User user = new User(INSERT_USERID, "insertTestPass", "입력테스트", "입력별명", "대전시 중구 중앙로 76", "4층", " 34540", new Date());
		
		/***When***/
		userService.save(user);

		/***Then***/
		UserVo findUser = userService.findById(INSERT_USERID);
		assertEquals(user.getUserid(), findUser.getUserid());
	}
	
	@Test
	public void userPagingListTest() {
		/***Given***/
		SearchCondition searchCondition = new SearchCondition(0, 5, SearchType.none);
		
		/***When***/
		List<UserVo> userList = userService.userPagingList(searchCondition);

		/***Then***/
		assertEquals(5, userList.size());
	}
	
	@Test
	public void userPagingListSearchConditionUseridTest() {
		/***Given***/
		SearchCondition searchCondition = new SearchCondition(0, 5, SearchType.userid, "b");
		
		/***When***/
		List<UserVo> userList = userService.userPagingList(searchCondition);

		/***Then***/
		assertEquals(2, userList.size());
	}
	
	@Test
	public void userPagingListSearchConditionUsernmTest() {
		/***Given***/
		SearchCondition searchCondition = new SearchCondition(0, 5, SearchType.usernm, "브");
		
		/***When***/
		List<UserVo> userList = userService.userPagingList(searchCondition);

		/***Then***/
		assertEquals(1, userList.size());
	}
	
	@Test
	public void userPagingListSearchConditionAliasTest() {
		/***Given***/
		SearchCondition searchCondition = new SearchCondition(0, 5, SearchType.alias, "사람");
		
		/***When***/
		List<UserVo> userList = userService.userPagingList(searchCondition);

		/***Then***/
		assertEquals(2, userList.size());
	}

}
