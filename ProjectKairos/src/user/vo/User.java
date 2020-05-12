package user.vo;

import java.sql.Date;

public class User {
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private String phone;
	private String email;
	private String addr;
	private Date expiredDate;
	private int conAgree;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, String userPw, String userName, String userNick, String phone, String email, String addr,
			Date expiredDate, int conAgree) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNick = userNick;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.expiredDate = expiredDate;
		this.conAgree = conAgree;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public int getConAgree() {
		return conAgree;
	}
	public void setConAgree(int conAgree) {
		this.conAgree = conAgree;
	}
	
	
}
