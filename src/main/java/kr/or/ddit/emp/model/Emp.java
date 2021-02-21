package kr.or.ddit.emp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.or.ddit.dept.model.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
//@NamedQueries(value = {@NamedQuery(name = "Emp.searchByEmpno", 
//								   query="SELECT e FROM Emp e WHERE e.empno = :empno ")})
public class Emp {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long empno;
	
	private String ename;
	private String job;
	private Long mgrno;
	private Date hiredate;
	private Long sal;
	private Long comm;
	
	@JoinColumn(name = "deptno")
	@ManyToOne(cascade = CascadeType.ALL)
	private Dept dept;
	
	public Emp(String ename, String job, Long mgrno, Date hiredate, Long sal, Long comm) {
	    setEname(ename);
	    setJob(job);
	    setMgrno(mgrno);
	    setHiredate(hiredate);
	    setSal(sal);
	    setComm(comm);
	}
	
}
