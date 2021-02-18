package kr.or.ddit.dept.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import kr.or.ddit.emp.model.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"empList"})
@Data
@Entity
public class Dept {
    
    public Dept(String dname, String loc) {
    	//this(null, dname, loc, null);
    	setDname(dname);
    	setLoc(loc);
    }

    @GeneratedValue
    @Id
    private Long deptno;
   
    private String dname;
    
    private String loc;
    
    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
    private List<Emp> empList = new ArrayList<Emp>();
    
    public void addEmp(Emp emp) {
    	if(empList.contains(emp) == false) {
    		empList.add(emp);
    	}
    }
}
