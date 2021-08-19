package kr.or.lgdlab.member.repository;

import java.util.List;

import kr.or.lgdlab.member.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//https://docs.spring.io/spring-data/jpa/docs/1.10.1.RELEASE/reference/html/#jpa.sample-app.finders.strategies
//https://javacan.tistory.com/entry/SpringDataJPA-Specifcation-Usage
//https://gunju-ko.github.io/spring/2018/05/01/Spring-Data-JPA-Paging.html
@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	
	List<Member> findByOrderByMemberidAsc();
	
	Page<Member> findByMemberidStartingWith(String memberid, Pageable pageable);
	
	Page<Member> findByMembernmStartingWith(String membernm, Pageable pageable);
	
	Page<Member> findByAliasStartingWith(String alias, Pageable pageable);
	
	List<Member> findAll(Specification<Member> spec);

	long count();

}
