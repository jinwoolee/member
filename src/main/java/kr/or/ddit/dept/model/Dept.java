package kr.or.ddit.dept.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Dept {
    
    public Dept(String dname, String loc) {
	this(null, dname, loc);	
    }
    
    @Id
    private Long deptno;
   
    private String dname;
    
    private String loc;
    
}
