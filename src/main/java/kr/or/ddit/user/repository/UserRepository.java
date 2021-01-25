package kr.or.ddit.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.User;

//https://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.sample-app.finders.strategies
//https://javacan.tistory.com/entry/SpringDataJPA-Specifcation-Usage
//https://gunju-ko.github.io/spring/2018/05/01/Spring-Data-JPA-Paging.html
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	List<User> findByOrderByUseridAsc();
	
	Page<User> findByUseridStartingWith(String userid, Pageable pageable);
	
	Page<User> findByUsernmStartingWith(String usernm, Pageable pageable);
	
	Page<User> findByAliasStartingWith(String usernm, Pageable pageable);
	
	List<User> findAll(Specification<User> spec);
			
}
