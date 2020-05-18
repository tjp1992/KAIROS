package admin.mypage.model.vo;

import java.sql.Date;

public class InquiryAnswer {
	private int inqAnsNo;
	private int inqNo;
	private String inqAnsTitle;
	private String inqAnsContent;
	private Date inqAnsDate;
	public InquiryAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InquiryAnswer(int inqAnsNo, int inqNo, String inqAnsTitle, String inqAnsContent, Date inqAnsDate) {
		super();
		this.inqAnsNo = inqAnsNo;
		this.inqNo = inqNo;
		this.inqAnsTitle = inqAnsTitle;
		this.inqAnsContent = inqAnsContent;
		this.inqAnsDate = inqAnsDate;
	}
	public int getInqAnsNo() {
		return inqAnsNo;
	}
	public void setInqAnsNo(int inqAnsNo) {
		this.inqAnsNo = inqAnsNo;
	}
	public int getInqNo() {
		return inqNo;
	}
	public void setInqNo(int inqNo) {
		this.inqNo = inqNo;
	}
	public String getInqAnsTitle() {
		return inqAnsTitle;
	}
	public void setInqAnsTitle(String inqAnsTitle) {
		this.inqAnsTitle = inqAnsTitle;
	}
	public String getInqAnsContent() {
		return inqAnsContent;
	}
	public void setInqAnsContent(String inqAnsContent) {
		this.inqAnsContent = inqAnsContent;
	}
	public Date getInqAnsDate() {
		return inqAnsDate;
	}
	public void setInqAnsDate(Date inqAnsDate) {
		this.inqAnsDate = inqAnsDate;
	}
	
}
