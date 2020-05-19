package admin.mypage.model.vo;

import java.sql.Date;

public class Inquiry {
	private int inqNo;
	private Date inqAnsDate;
	private String userId;
	private String inqTitle;
	private String inqContent;
	private Date inqDate;
	private int inqAnsNo;
	private String inqFileName;
	private String inqFilePath;
	public Inquiry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inquiry(Date inqAnsDate, int inqNo, String userId, String inqTitle, String inqContent, Date inqDate, int inqAnsNo,
			String inqFileName, String inqFilePath) {
		super();
		this.inqAnsDate = inqAnsDate;
		this.inqNo = inqNo;
		this.userId = userId;
		this.inqTitle = inqTitle;
		this.inqContent = inqContent;
		this.inqDate = inqDate;
		this.inqAnsNo = inqAnsNo;
		this.inqFileName = inqFileName;
		this.inqFilePath = inqFilePath;
	}
	public int getInqNo() {
		return inqNo;
	}
	public void setInqNo(int inqNo) {
		this.inqNo = inqNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInqTitle() {
		return inqTitle;
	}
	public void setInqTitle(String inqTitle) {
		this.inqTitle = inqTitle;
	}
	public String getInqContent() {
		return inqContent;
	}
	public void setInqContent(String inqContent) {
		this.inqContent = inqContent;
	}
	public Date getInqDate() {
		return inqDate;
	}
	public void setInqDate(Date inqDate) {
		this.inqDate = inqDate;
	}
	public int getInqAnsNo() {
		return inqAnsNo;
	}
	public void setInqAnsNo(int inqAnsNo) {
		this.inqAnsNo = inqAnsNo;
	}
	public String getInqFileName() {
		return inqFileName;
	}
	public void setInqFileName(String inqFileName) {
		this.inqFileName = inqFileName;
	}
	public String getInqFilePath() {
		return inqFilePath;
	}
	public void setInqFilePath(String inqFilePath) {
		this.inqFilePath = inqFilePath;
	}
	public Date getInqAnsDate() {
		return inqAnsDate;
	}
	public void setInqAnsDate(Date inqAnsDate) {
		this.inqAnsDate = inqAnsDate;
	}
}
