package kr.or.ddit.login.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	public User() {}
	
	public User(String userid, String pass, String usernm, String alias, 
				String addr1, String addr2, Date reg_dt) {
		this.userid = userid;
		this.pass = pass;
		this.usernm = usernm;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.reg_dt = reg_dt;
	}

	@Id
	private String userid;
	
	private String pass;
	
	private String usernm;
	private String alias;
	private String addr1;
	private String addr2;
	private Date reg_dt;
	
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

	public Date getReg_dt() {
		return reg_dt;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", pass=" + pass + ", usernm=" + usernm + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", reg_dt=" + reg_dt + "]";
	}	
}
