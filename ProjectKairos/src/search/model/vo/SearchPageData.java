package search.model.vo;

import java.util.ArrayList;

public class SearchPageData {
	int totalCount;
	String pageNavi;
	ArrayList<SearchSong> list;
	public SearchPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchPageData(int totalCount, String pageNavi, ArrayList<SearchSong> list) {
		super();
		this.totalCount = totalCount;
		this.pageNavi = pageNavi;
		this.list = list;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public ArrayList<SearchSong> getList() {
		return list;
	}
	public void setList(ArrayList<SearchSong> list) {
		this.list = list;
	}
	
	
}
