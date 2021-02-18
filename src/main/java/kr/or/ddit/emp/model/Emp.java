package kr.or.ddit.emp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.or.ddit.dept.model.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Emp {

	@GeneratedValue
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
		this(null, ename, job, mgrno, hiredate, sal, comm, null);
	}
	
}
