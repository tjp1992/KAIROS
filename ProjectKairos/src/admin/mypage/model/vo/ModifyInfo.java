package admin.mypage.model.vo;

public class ModifyInfo {
	private int noticeNo;
	private int reqPage;
	private int mofi;
	private String title;
	public ModifyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ModifyInfo(int noticeNo, int reqPage, int mofi, String title) {
		super();
		this.noticeNo = noticeNo;
		this.reqPage = reqPage;
		this.mofi = mofi;
		this.title = title;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public int getMofi() {
		return mofi;
	}
	public void setMofi(int mofi) {
		this.mofi = mofi;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
