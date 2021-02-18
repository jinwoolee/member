package kr.or.ddit.emp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
				= em.createQuery("SELECT e FROM Emp AS e WHERE ename = ?1", Emp.class);
		empTypedQuery.setParameter(1, ename);
		
		return empTypedQuery.getResultList();
	}
	
	
	

}
