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

import kr.or.ddit.emp.repository.EmpRepository;
import kr.or.ddit.emp.repository.EmpRepositoryImpl;

public class EmpTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpTest.class);
	
	private EmpRepository empRepository; 

	@BeforeEach
	public void setup() throws ParseException {
		
		empRepository = new EmpRepositoryImpl();
		
		empRepository.deleteAll();
		empRepository.save(new Emp("brown", "ranger", null, new SimpleDateFormat("yyyyMMdd").parse("20200808"), 1000L, 500L));
	}

	@Test
	public void findTest(){
		/***Given***/
		Long empno = 1L;

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
