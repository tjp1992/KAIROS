package admin.mypage.model.vo;

import java.sql.Date;

public class PurchaseLog {
	private String voucherName;
	private int purchaseNo;
	private int voucherNo;
	private String userId;
	private Date purchaseDate;
	private Date beginDate;
	private Date expiredDate;
	public PurchaseLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseLog(int purchaseNo, int voucherNo, String userId, Date purchaseDate, Date beginDate,
			Date expiredDate, String voucherName) {
		super();
		this.purchaseNo = purchaseNo;
		this.voucherNo = voucherNo;
		this.userId = userId;
		this.purchaseDate = purchaseDate;
		this.beginDate = beginDate;
		this.expiredDate = expiredDate;
		this.voucherName = voucherName;
	}
	public int getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public int getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(int voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getVoucherName() {
		return voucherName;
	}
	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}
	
}
