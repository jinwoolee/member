package kr.or.ddit.emp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import kr.or.ddit.config.entity.EntityManagerUtil;
import kr.or.ddit.emp.model.Emp;

public class EmpRepositoryImpl implements EmpRepository{
	
	private EntityManager em;
	
	public EmpRepositoryImpl() {
		this.em = EntityManagerUtil.getEm();
	}

	public EmpRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Emp findEmp(Long empno) {
		return em.find(Emp.class, empno);
	}

	@Override
	public List<Emp> findAll() {
		return em.createQuery("from Emp", Emp.class).getResultList();
	}

	@Override
	public void save(Emp emp) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(emp);
		tx.commit();
	}

	@Override
	public void delete(Emp emp) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(emp);
		tx.commit();
	}


	@Override
	public void deleteAll() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.createQuery("delete From Emp").executeUpdate();
		tx.commit();
	}

	@Override
	public long totalEmpCnt() {
		
		TypedQuery<Long> totalCnt = em.createQuery("SELECT COUNT(*) FROM Emp AS e", Long.class);
		
		return totalCnt.getSingleResult();
	}

	@Override
	public List<Emp> findByEname(String ename) {
		
		TypedQuery<Emp> empTypedQuery 
		= em.createQuery("SELECT e FROM Emp AS e WHERE ename = :ename", Emp.class);
		empTypedQuery.setParameter("ename", ename);
		
		return empTypedQuery.getResultList();
	}

	@Override
	public List<Emp> empPaingList(int page, int pageSize) {
		TypedQuery<Emp> empTypedQuery 
		= em.createQuery("SELECT e FROM Emp AS e ORDER BY e.empno", Emp.class);
		
		empTypedQuery.setFirstResult( (page-1) * pageSize);
		empTypedQuery.setMaxResults(page * pageSize);
		
		return empTypedQuery.getResultList();
	}

	@Override
	public Emp serarchByEmpno(Long empno) {
		TypedQuery<Emp> empTypedQuery 
			= em.createNamedQuery("Emp.searchByEmpno", Emp.class);
		
		empTypedQuery.setParameter("empno", empno);
		
		return empTypedQuery.getSingleResult();
	}

	@Override
	public Emp serarchByEmpnoCriteria(Long empno) {
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    
	    //검색할 타입의 객체
	    CriteriaQuery<Emp> query = builder.createQuery(Emp.class);
	    
	    //from
	    Root<Emp> root = query.from(Emp.class);
	    
	    //select (전체 컬럼 조회시 생략 가능)
	    //query.multiselect(root.get("empno"), root.get("ename"));
	    query.select(root);
	    
	    //where
	    query.where(builder.equal(root.get("empno"), empno));
	    
	    return em.createQuery(query).getSingleResult();
	}
}






