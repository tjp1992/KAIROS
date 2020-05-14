package song.vo;

import java.util.ArrayList;

public class RankingPageData {
	private ArrayList<RankingSong> list;
	private String pageNavi;
	
	public RankingPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RankingPageData(ArrayList<RankingSong> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	/**
	 * @return the list
	 */
	public ArrayList<RankingSong> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<RankingSong> list) {
		this.list = list;
	}
	/**
	 * @return the pageNavi
	 */
	public String getPageNavi() {
		return pageNavi;
	}
	/**
	 * @param pageNavi the pageNavi to set
	 */
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
