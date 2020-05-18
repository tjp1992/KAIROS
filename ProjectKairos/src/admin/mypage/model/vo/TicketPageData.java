package admin.mypage.model.vo;

import java.util.ArrayList;

public class TicketPageData {
	private ArrayList<PurchaseLog> list;
	private String pageNavi;
	public TicketPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketPageData(ArrayList<PurchaseLog> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<PurchaseLog> getList() {
		return list;
	}
	public void setList(ArrayList<PurchaseLog> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
