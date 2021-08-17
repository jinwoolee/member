package kr.or.lgdlab.member.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class MemberSpecs {
	public static Specification<Member> usernmLike(final String keyword){
		return new Specification<Member>() {

			@Override
			public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get(Member._membernm), "%" + keyword + "%");
			}
		};
	}
}
