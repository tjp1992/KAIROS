package admin.mypage.model.vo;

import java.util.ArrayList;

public class QuestionPageData {
	private ArrayList<Inquiry> list;
	private String pageNavi;
	public QuestionPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionPageData(ArrayList<Inquiry> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Inquiry> getList() {
		return list;
	}
	public void setList(ArrayList<Inquiry> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
