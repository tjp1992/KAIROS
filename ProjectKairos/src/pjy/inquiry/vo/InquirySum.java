package pjy.inquiry.vo;

import admin.mypage.model.vo.InquiryAnswer;

public class InquirySum {
	private Inquiry i;
	private InquiryAnswer ia;
	public InquirySum() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InquirySum(Inquiry i, InquiryAnswer ia) {
		super();
		this.i = i;
		this.ia = ia;
	}
	public Inquiry getI() {
		return i;
	}
	public void setI(Inquiry i) {
		this.i = i;
	}
	public InquiryAnswer getIa() {
		return ia;
	}
	public void setIa(InquiryAnswer ia) {
		this.ia = ia;
	}
	
	
}
