package kr.or.ddit.emp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.dept.repository.DeptRepository;
import kr.or.ddit.dept.repository.DeptRepositoryImpl;
import kr.or.ddit.emp.repository.EmpRepository;
import kr.or.ddit.emp.repository.EmpRepositoryImpl;

public class EmpRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpRepositoryTest.class);
	
	private EmpRepository empRepository;
	private Emp newEmp;
	
	@BeforeEach
	public void setup() throws ParseException {
		
		empRepository = new EmpRepositoryImpl();
		
		empRepository.deleteAll();
		
		newEmp = new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		empRepository.save(newEmp);
	}

	@Test
	public void findTest(){
		/***Given***/
		Long empno = newEmp.getEmpno();

		/***When***/
		Emp emp = empRepository.findEmp(empno);

		/***Then***/
		assertEquals("brown", emp.getEname());
	}
	
	@Test
	public void findAllTest() throws ParseException {
		/***Given***/
		empRepository.save(new Emp("sally", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L));

		/***When***/
		List<Emp> empList = empRepository.findAll();
		for(Emp emp : empList) {
			logger.debug("{}", emp);
		}
		
		/***Then***/
		assertEquals(2, empList.size());
	}
	
	@Test
	public void deleteTest() throws ParseException {
		/***Given***/
		Emp emp = new Emp("sally", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L);
		empRepository.save(emp);
		Long empno = emp.getEmpno();
		logger.debug("empno : {} ", empno);
		
		/***When***/
		empRepository.delete(emp);

		/***Then***/
		assertNull(empRepository.findEmp(empno));
	}
		
	@Test
	public void deleteAllTest() {
		/***Given***/

		/***When***/
		empRepository.deleteAll();

		/***Then***/
		assertEquals(0, empRepository.findAll().size());		
	}
	

}
