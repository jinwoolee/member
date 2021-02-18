package kr.or.ddit.emp.repository;

import java.util.List;

import kr.or.ddit.emp.model.Emp;

public interface EmpRepository {
	
	Emp findEmp(Long empno);
	
	List<Emp> findAll();
	
	void save(Emp emp);
	
	void delete(Emp emp);
	
	void deleteAll();
	
	long totalEmpCnt();
	
	List<Emp> findByEname(String ename);
}
