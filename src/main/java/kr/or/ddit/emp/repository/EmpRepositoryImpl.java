package kr.or.ddit.emp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import kr.or.ddit.config.entity.EntityManagerUtil;
import kr.or.ddit.emp.model.Emp;

public class EmpRepositoryImpl implements EmpRepository{
	
	private EntityManager em;
	
	public EmpRepositoryImpl() {
		this.em = EntityManagerUtil.getEm();
	}
	

	@Override
	public Emp findEmp(Long empno) {
		return em.find(Emp.class, empno);
	}

	@Override
	public List<Emp> findAll() {
		return em.createQuery("from Emp").getResultList();
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
	
	
	

}
