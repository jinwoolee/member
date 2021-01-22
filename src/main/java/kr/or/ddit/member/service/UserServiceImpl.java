package kr.or.ddit.member.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.SearchCondition;
import kr.or.ddit.common.SearchType;
import kr.or.ddit.login.model.User;
import kr.or.ddit.login.model.UserVo;
import kr.or.ddit.login.repository.UserRepository;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userRepository")
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserVo> userPagingList(SearchCondition sc) {
		//.by(Sort.Direction.DESC, "userid")
		//List<User> userList = userRepository.findAll(Sort.by(Order.asc("alias"), Order.asc("userid")));
		//List<User> userList = userRepository.findByOrderByUseridAsc();
		
		Page<User> userList = null;
		//userRepository.findAll
		
		if(sc.getSearchType() == SearchType.none) {
			userList = userRepository.findAll(PageRequest.of(sc.getPage(), sc.getPageSize()));
		}
		else if(sc.getSearchType() == SearchType.userid) {
			userList = userRepository.findByUseridStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}
		else if(sc.getSearchType() == SearchType.usernm) {
			userList = userRepository.findByUsernmStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}
		else if(sc.getSearchType() == SearchType.alias) {
			userList = userRepository.findByAliasStartingWith(
										sc.getSearchKeyword(), 
										PageRequest.of(sc.getPage(), sc.getPageSize(), 
												Sort.by(Sort.Direction.ASC, sc.getSearchType().toString())));
		}
		
		
		
		//userRepository.findAll(null)
		List<UserVo> userVoList = userList.stream()
											.map(user-> modelMapper.map(user, UserVo.class))
											.collect(Collectors.toList());
		
		return userVoList;
	}
}
