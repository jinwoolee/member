package kr.or.ddit.association;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.config.entity.EntityManagerUtil;
import kr.or.ddit.config.spring.JPAContext;
import kr.or.ddit.config.spring.TxContext;
import kr.or.ddit.dept.model.Dept;
import kr.or.ddit.emp.model.Emp;
import kr.or.ddit.emp.repository.EmpRepository;
import kr.or.ddit.emp.repository.EmpRepositoryImpl;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {JPAContext.class, TxContext.class})
class EmpDeptAssociationTest {

	private static final Logger logger = LoggerFactory.getLogger(EmpDeptAssociationTest.class);
	
	@Resource(name="empRepository")
	private EmpRepository empRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@BeforeEach
	public void setup() throws ParseException {	
		empRepository.deleteAll();
	}
	
	@Test
	public void emp_deptPersist_findEmpTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		
		em.persist(dept);
		em.persist(emp);
		
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
		em.persist(dept);
		em.persist(emp);
		
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
		logger.debug("dept.getEmpList() : {}", dept.getEmpList());
		
		em.persist(dept);
		em.persist(emp);
		
		em.flush();
		em.clear();
		
		logger.debug("contain emp : {}, contain dept : {}", em.contains(emp), em.contains(dept));
		logger.debug("emp.getDept() : {}", emp.getDept());
		
		logger.debug("dept.getEmpList() : {}", em.find(Dept.class, dept.getDeptno()).getEmpList() );		
	}
	
	@Test
	public void mappedByCascadeTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		/***When***/
		logger.debug("dept.getEmpList() : {}", dept.getEmpList());
		
		//em.persist(dept);
		em.persist(emp);
		
		em.flush();
		em.clear();
		
		logger.debug("contain emp : {}, contain dept : {}", em.contains(emp), em.contains(dept));
		logger.debug("emp.getDept() : {}", emp.getDept());
		
		logger.debug("dept.getEmpList() : {}", em.find(Dept.class, dept.getDeptno()).getEmpList() );		
	}
	
	@Test
	public void cascadeDeptTest() throws ParseException {
		/***Given***/
		Dept dept = new Dept("LINE", "판교");
		
		Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		emp.setDept(dept);
		
		dept.addEmp(emp);
		
		em.persist(dept);
		
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
		em.persist(emp);
		
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
		em.persist(emp);
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
		em.persist(emp);
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
		em.persist(emp);
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
		em.persist(emp);
		em.clear();
		
		TypedQuery<Emp> typedQuery = em.createQuery("SELECT e FROM Emp e JOIN FETCH e.dept", Emp.class);
		
		Emp joinFetchEmp = typedQuery.getSingleResult();
		
		logger.debug("emp : {}", joinFetchEmp);
		
		/*****THEN*****/
		assertEquals("brown", joinFetchEmp.getEname());
	}
	
	@Test
	public void empDeptCriteriaImplictJoinTest() throws ParseException {
	    /***given***/
	    Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    emp.setDept(dept);
	    
	    em.persist(emp);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
	    Root<Emp> root = query.from(Emp.class);
	    
	    query.multiselect(root.get("empno"), root.get("ename"), root.get("dept").get("dname"));
	    
	    List<Object[]> list = em.createQuery(query).getResultList();
	    for(Object[] obj : list) {
		logger.debug("obj : {}", Arrays.toString(obj));
	    }

	    /***then***/
	}
	
	@Test
	public void empDeptCriteriaExplictJoinTest() throws ParseException {
	    /***given***/
	    Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    emp.setDept(dept);
	    
	    em.persist(emp);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
	    Root<Emp> root = query.from(Emp.class);
	    
	    Join<Emp, Dept> joinDept = root.join("dept");
	    
	    query.multiselect(root.get("empno"), root.get("ename"), joinDept.get("dname"));
	    
	    List<Object[]> list = em.createQuery(query).getResultList();
	    for(Object[] obj : list) {
		logger.debug("obj : {}", Arrays.toString(obj));
	    }

	    /***then***/
	}
	
	@Test
	public void empDeptCriteriaExplictJoinNoRelationTest() throws ParseException {
	    /***given***/
	    Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    emp.setDept(dept);
	    
	    em.persist(emp);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
	    Root<Emp> empRoot = query.from(Emp.class);
	    Root<Dept> deptRoot = query.from(Dept.class);
	    
	    query.where(builder.equal(empRoot.get("dept").get("deptno"), deptRoot.get("deptno")));
	    
	    query.multiselect(empRoot.get("empno"), empRoot.get("ename"), deptRoot.get("dname"));
	    
	    List<Object[]> list = em.createQuery(query).getResultList();
	    for(Object[] obj : list) {
		logger.debug("obj : {}", Arrays.toString(obj));
	    }

	    /***then***/
	}
	
	@Test
	public void empDeptCriteriaJoinFetchTest() throws ParseException {
	    /***given***/
	    Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    emp.setDept(dept);
	    
	    em.persist(emp);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Dept> query = builder.createQuery(Dept.class);
	    
	    Root<Dept> deptRoot = query.from(Dept.class);
	    query.select(deptRoot);
	    
	    deptRoot.fetch("empList");
	    
	    List<Dept> list = em.createQuery(query).getResultList();
	    for(Dept deptObj : list) {
		logger.debug("obj : {}", deptObj);
	    }

	    /***then***/
	}
	
	@Test
	public void predicateTest() throws ParseException {
	    /***given***/
	    Emp emp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    emp.setDept(dept);
	    
	    em.persist(emp);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Dept> query = builder.createQuery(Dept.class);
	    
	    Root<Dept> deptRoot = query.from(Dept.class);
	    
	    Predicate[] predicate = {	builder.equal(deptRoot.get("dname"), "LINE"),
		    			builder.equal(deptRoot.get("loc"), "판교") };
	    query.where(builder.and(predicate));
	    
	    deptRoot.fetch("empList");
	    
	    List<Dept> list = em.createQuery(query).getResultList();
	    for(Dept deptObj : list) {
		logger.debug("obj : {}", deptObj);
	    }

	    /***then***/
	}
	
	@Test
	public void criteriaGroupByTest() throws ParseException {
	    /***given***/
	    Emp brown = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Emp sally = new Emp("sally", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    brown.setDept(dept);
	    sally.setDept(dept);
	    
	    em.persist(brown);
	    em.persist(sally);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
	    
	    Root<Emp> empRoot = query.from(Emp.class);
	    
	    query.groupBy(empRoot.get("dept").get("deptno"));
	    
	    query.multiselect(empRoot.get("dept").get("deptno"), 
		    	      builder.count(empRoot),
		    	      builder.sum(empRoot.<Double>get("sal")));
	    
	    Object[] obj = em.createQuery(query).getSingleResult();
	    logger.debug("obj : {}", Arrays.toString(obj));

	    /***then***/
	}
	
	@Test
	public void criteriaOrderByTest() throws ParseException {
	    /***given***/
	    Emp brown = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Emp sally = new Emp("sally", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    brown.setDept(dept);
	    sally.setDept(dept);
	    
	    em.persist(brown);
	    em.persist(sally);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Emp> query = builder.createQuery(Emp.class);
	    
	    Root<Emp> empRoot = query.from(Emp.class);
	    
	    query.orderBy(builder.desc(empRoot.get("ename")));
	    
	    List<Emp> empList = em.createQuery(query).getResultList();
	    for(Emp empObj : empList) {
		logger.debug("emp : {}", empObj);
	    }

	    /***then***/
	}
	
	@Test
	public void criteriaSubqueryTest() throws ParseException {
	    /***given***/
	    Emp brown = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Emp sally = new Emp("sally", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
	    Dept dept = new Dept("LINE", "판교");
	    brown.setDept(dept);
	    sally.setDept(dept);
	    
	    em.persist(brown);
	    em.persist(sally);
	    em.clear();
	
	    /***when***/
	    CriteriaBuilder builder = em.getCriteriaBuilder();
	    
	    CriteriaQuery<Emp> query = builder.createQuery(Emp.class);
	    Root<Emp> empRoot = query.from(Emp.class);
	    
	    Subquery<Dept> subquery = query.subquery(Dept.class);
	    Root<Dept> deptRoot = subquery.from(Dept.class);
	    subquery.select(deptRoot.get("deptno"));
	    
	    query.where(builder.in(empRoot.get("dept").get("deptno")).value(subquery));
	    
	    List<Emp> empList = em.createQuery(query).getResultList();
	    for(Emp empObj : empList) {
		logger.debug("emp : {}", empObj);
	    }

	    /***then***/
	}
}






