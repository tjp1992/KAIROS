package search.model.vo;

import java.util.ArrayList;

public class SearchResult {
	private ArrayList<SearchSong> list;
	private int totalResult;
	public SearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SearchResult(ArrayList<SearchSong> list, int totalResult) {
		super();
		this.list = list;
		this.totalResult = totalResult;
	}


	public ArrayList<SearchSong> getList() {
		return list;
	}
	public void setList(ArrayList<SearchSong> list) {
		this.list = list;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}	
	
	
	
}
