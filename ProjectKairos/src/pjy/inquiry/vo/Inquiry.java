package pjy.inquiry.vo;

import java.sql.Date;

public class Inquiry {
	private int inqNo;
	private String userId;
	private String inqTitle;
	private String inqContent;
	private Date inqDate;
	private int inqAnsNo;
	private String inqFilename;
	private String inqFilepath;
	public Inquiry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inquiry(int inqNo, String userId, String inqTitle, String inqContent, Date inqDate, int inqAnsNo,
			String inqFilename, String inqFilepath) {
		super();
		this.inqNo = inqNo;
		this.userId = userId;
		this.inqTitle = inqTitle;
		this.inqContent = inqContent;
		this.inqDate = inqDate;
		this.inqAnsNo = inqAnsNo;
		this.inqFilename = inqFilename;
		this.inqFilepath = inqFilepath;
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
	public String getInqFilename() {
		return inqFilename;
	}
	public void setInqFilename(String inqFilename) {
		this.inqFilename = inqFilename;
	}
	public String getInqFilepath() {
		return inqFilepath;
	}
	public void setInqFilepath(String inqFilepath) {
		this.inqFilepath = inqFilepath;
	}

	
	
}
