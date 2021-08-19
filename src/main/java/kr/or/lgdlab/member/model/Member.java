package kr.or.lgdlab.member.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="member")
public class Member {
	
	static String _memberid = "memberid";
	static String _membernm = "membernm";
	static String _alias  = "alias";
	
	public Member() {}
	
	public Member(String memberid, String pass, String membernm, String alias,
				  String addr1, String addr2, String zipcode, Date reg_dt) {
		this.memberid = memberid;
		this.pass = pass;
		this.membernm = membernm;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.reg_dt = reg_dt;
	}

	@Id
	private String memberid;
	
	private String pass;
	
	private String membernm;
	private String alias;
	private String addr1;
	private String addr2;
	private String zipcode;
	private Date reg_dt;
	
	private String filename;
	private String realFilename;
	
	public String getMemberid() {
		return memberid;
	}

	public String getPass() {
		return pass;
	}

	public String getMembernm() {
		return membernm;
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
	
	

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setMembernm(String membernm) {
		this.membernm = membernm;
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
		return "Member [memberid=" + memberid + ", pass=" + pass + ", membernm=" + membernm + ", alias=" + alias + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", reg_dt=" + reg_dt + ", filename=" + filename
				+ ", realFilename=" + realFilename + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberid == null) ? 0 : memberid.hashCode());
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
		Member other = (Member) obj;
		if (memberid == null) {
			if (other.memberid != null)
				return false;
		} else if (!memberid.equals(other.memberid))
			return false;
		return true;
	}


}
