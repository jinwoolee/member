package kr.or.ddit.model.model;

import java.util.Date;

public class UserVo {
	
	private String userid;
	private String pass;
	private String usernm;
	private String alias;
	private String addr1;
	private String addr2;
	private Date reg_dt;
	private String zipcode;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", pass=" + pass + ", usernm=" + usernm + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", reg_dt=" + reg_dt + ", zipcode=" + zipcode + "]";
	}	
}
