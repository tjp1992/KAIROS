package admin.mypage.model.vo;

import java.sql.Date;

public class WebUser {
	private String userId;
	private String userPw;
	private String userName;
	private String userNickName;
	private String phone;
	private String address;
	private Date expiredDate;
	private int conAgree;
	public WebUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WebUser(String userId, String userPw, String userName, String userNickName, String phone, String address,
			Date expiredDate, int conAgree) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNickName = userNickName;
		this.phone = phone;
		this.address = address;
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
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
