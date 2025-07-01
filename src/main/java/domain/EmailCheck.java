package domain;

import java.time.LocalDateTime;

public class EmailCheck {

	private Long eno;
	private String email;
	private String uuid;
	private LocalDateTime voiddate;
	private int check;
	private Long mno;
	
	
	public Long getEno() {
		return eno;
	}
	public void setEno(Long eno) {
		this.eno = eno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public LocalDateTime getVoiddate() {
		return voiddate;
	}
	public void setVoiddate(LocalDateTime voiddate) {
		this.voiddate = voiddate;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public Long getMno() {
		return mno;
	}
	public void setMno(Long mno) {
		this.mno = mno;
	}
	
	
	
	
}
