package kr.or.ddit.emp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Emp {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long empno;
	
	private String ename;
	private String job;
	private Long mgrno;
	private Date hiredate;
	private Long sal;
	private Long comm;
	private Long deptno;
	
	public Emp(String ename, String job, Long mgrno, Date hiredate, Long sal, Long comm) {
		this(null, ename, job, mgrno, hiredate, sal, comm, null);
	}
	
}
