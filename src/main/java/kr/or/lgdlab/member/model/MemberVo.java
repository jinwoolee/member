package kr.or.lgdlab.member.model;

import java.util.Date;

public class MemberVo {
	
	private String memberid;
	private String pass;
	private String membernm;
	private String alias;
	private String addr1;
	private String addr2;
	private Date reg_dt;
	private String zipcode;
	private String filename;
	private String realFilename;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMembernm() {
		return membernm;
	}
	public void setMembernm(String membernm) {
		this.membernm = membernm;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRealFilename() {
		return realFilename;
	}
	public void setRealFilename(String realFilename) {
		this.realFilename = realFilename;
	}
	
	@Override
	public String toString() {
		return "MemberVo [memberid=" + memberid + ", pass=" + pass + ", membernm=" + membernm + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", reg_dt=" + reg_dt + ", zipcode=" + zipcode + ", filename=" + filename
				+ ", realFilename=" + realFilename + "]";
	}
}
