package kr.or.ddit.member.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	
	static String _userid = "userid";
	static String _usernm = "usernm";
	static String _alias  = "alias";
	
	public User() {}
	
	public User(String userid, String pass, String usernm, String alias, 
				String addr1, String addr2, String zipcode, Date reg_dt) {
		this.userid = userid;
		this.pass = pass;
		this.usernm = usernm;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.reg_dt = reg_dt;
	}

	@Id
	private String userid;
	
	private String pass;
	
	private String usernm;
	private String alias;
	private String addr1;
	private String addr2;
	private String zipcode;
	private Date reg_dt;
	
	private String filename;
	private String realFilename;
	
	public String getUserid() {
		return userid;
	}

	public String getPass() {
		return pass;
	}

	public String getUsernm() {
		return usernm;
	}

	public String getAlias() {
		return alias;
	}

	public String getAddr1() {
		return addr1;
	}

	public String getAddr2() {
		return addr2;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public Date getReg_dt() {
		return reg_dt;
	}
	
	public String getFilename() {
		return filename;
	}

	public String getRealFilename() {
		return realFilename;
	}
	
	

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", pass=" + pass + ", usernm=" + usernm + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", reg_dt=" + reg_dt + ", filename=" + filename
				+ ", realFilename=" + realFilename + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}


}
