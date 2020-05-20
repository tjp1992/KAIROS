package search.model.vo;

import java.util.ArrayList;

import song.vo.SearchSong;

public class MyListPageData {
	String pageNavi;
	ArrayList<SearchSong> list;
	public MyListPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyListPageData(String pageNavi, ArrayList<SearchSong> list) {
		super();
		this.pageNavi = pageNavi;
		this.list = list;
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
