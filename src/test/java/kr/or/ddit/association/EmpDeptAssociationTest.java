package kr.or.ddit.association;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.config.entity.EntityManagerUtil;
import kr.or.ddit.dept.model.Dept;
import kr.or.ddit.emp.model.Emp;
import kr.or.ddit.emp.repository.EmpRepository;
import kr.or.ddit.emp.repository.EmpRepositoryImpl;

class EmpDeptAssociationTest {

	private static final Logger logger = LoggerFactory.getLogger(EmpDeptAssociationTest.class);
	
	private EntityManager em; 
	private EmpRepository empRepository;
	
	@BeforeEach
	public void setup() throws ParseException {
		em = EntityManagerUtil.getEm();
		
		empRepository = new EmpRepositoryImpl(em);
		
		empRepository.deleteAll();
	}
	
	@Test
	public void emp_deptPersist_findEmpTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		EntityTransaction tx =  em.getTransaction();
		
		tx.begin();
		em.persist(dept);
		em.persist(emp);
		tx.commit();
		
		em.clear();
		Long empno = emp.getEmpno();
		
		Emp findEmp = em.find(Emp.class, empno);

		/***Then***/
		logger.debug("{}", findEmp);
	}
	
	@Test
	public void emp_deptPersist_findDeptTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		EntityTransaction tx =  em.getTransaction();
		
		tx.begin();
		em.persist(dept);
		em.persist(emp);
		tx.commit();
		
		em.clear();
		Long deptno = dept.getDeptno();
		
		Dept findDept = em.find(Dept.class, deptno);

		/***Then***/
		logger.debug("{}", findDept);
		logger.debug("empList : {}", findDept.getEmpList());
	}
	
	
	@Test
	public void mappedByNotCascadeTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		EntityTransaction tx =  em.getTransaction();
		
		tx.begin();
		logger.debug("dept.getEmpList() : {}", dept.getEmpList());
		
		em.persist(dept);
		em.persist(emp);
		
		em.flush();
		em.clear();
		
		logger.debug("contain emp : {}, contain dept : {}", em.contains(emp), em.contains(dept));
		logger.debug("emp.getDept() : {}", emp.getDept());
		
		logger.debug("dept.getEmpList() : {}", em.find(Dept.class, dept.getDeptno()).getEmpList() );		
		tx.commit();
	}
	
	@Test
	public void mappedByCascadeTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		EntityTransaction tx =  em.getTransaction();
		
		tx.begin();
		logger.debug("dept.getEmpList() : {}", dept.getEmpList());
		
		//em.persist(dept);
		em.persist(emp);
		
		em.flush();
		em.clear();
		
		logger.debug("contain emp : {}, contain dept : {}", em.contains(emp), em.contains(dept));
		logger.debug("emp.getDept() : {}", emp.getDept());
		
		logger.debug("dept.getEmpList() : {}", em.find(Dept.class, dept.getDeptno()).getEmpList() );		
		tx.commit();
	}
	
	@Test
	public void cascadeDeptTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		dept.addEmp(emp);
		
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(dept);
		tx.commit();
		
		em.clear();
		
		logger.debug("emp : {} ", em.find(Emp.class, emp.getEmpno()));
		logger.debug("dept : {} ", em.find(Dept.class, dept.getDeptno()));
	}
	
	@Test
	public void empDeptImplictJoinTest() throws ParseException {
		/*****GIVEN*****/
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		Dept dept = new Dept("LINE", "판교");
		emp.setDept(dept);
		
		/*****WHEN*****/
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(emp);
		tx.commit();
		
		em.clear();
		
		TypedQuery<Object[]> typedQuery = em.createQuery("SELECT e, e.dept FROM Emp e", Object[].class);
		
		Object[] result = typedQuery.getSingleResult();
		
		logger.debug("emp : {}", result[0]);
		logger.debug("dept : {}", result[1]);
		
		/*****THEN*****/
		assertEquals(2, result.length);
	}
	
	@Test
	public void empDeptExplictJoinTest() throws ParseException {
		/*****GIVEN*****/
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		Dept dept = new Dept("LINE", "판교");
		emp.setDept(dept);
		
		/*****WHEN*****/
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(emp);
		tx.commit();
		
		em.clear();
		
		TypedQuery<Object[]> typedQuery = em.createQuery("SELECT e, d FROM Emp e INNER JOIN e.dept d", Object[].class);
		
		Object[] result = typedQuery.getSingleResult();
		
		logger.debug("emp : {}", result[0]);
		logger.debug("dept : {}", result[1]);
		
		/*****THEN*****/
		assertEquals(2, result.length);
	}
	
	@Test
	public void empDeptThetaJoinTest() throws ParseException {
		/*****GIVEN*****/
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		Dept dept = new Dept("LINE", "판교");
		emp.setDept(dept);
		
		/*****WHEN*****/
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(emp);
		tx.commit();
		
		em.clear();
		
		TypedQuery<Object[]> typedQuery = em.createQuery("SELECT e, d FROM Emp e, Dept d WHERE e.dept = d.deptno", Object[].class);
		
		Object[] result = typedQuery.getSingleResult();
		
		logger.debug("emp : {}", result[0]);
		logger.debug("dept : {}", result[1]);
		
		/*****THEN*****/
		assertEquals(2, result.length);
	}
	
	@Test
	public void empDeptNoJoinFetchTest() throws ParseException {
		/*****GIVEN*****/
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		Dept dept = new Dept("LINE", "판교");
		emp.setDept(dept);
		
		/*****WHEN*****/
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(emp);
		tx.commit();
		
		em.clear();
		
		TypedQuery<Emp> typedQuery = em.createQuery("SELECT e FROM Emp e", Emp.class);
		
		Emp findedEmp = typedQuery.getSingleResult();
		
		logger.debug("emp.getDept() : {}", findedEmp.getDept());
		
		
		/*****THEN*****/
		assertEquals("brown", findedEmp.getEname());
	}
	
	@Test
	public void empDeptJoinFetchTest() throws ParseException {
		/*****GIVEN*****/
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		Dept dept = new Dept("LINE", "판교");
		emp.setDept(dept);
		
		/*****WHEN*****/
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		em.persist(emp);
		tx.commit();
		
		em.clear();
		
		TypedQuery<Emp> typedQuery = em.createQuery("SELECT e FROM Emp e JOIN FETCH e.dept", Emp.class);
		
		Emp joinFetchEmp = typedQuery.getSingleResult();
		
		logger.debug("emp : {}", joinFetchEmp);
		
		
		/*****THEN*****/
		assertEquals("brown", joinFetchEmp.getEname());
	}
}



