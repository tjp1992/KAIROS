package search.model.vo;

public class ReqMyList {
	String userNick;
	int reqPage;
	int numPerPage;
	String reSearch;
	public ReqMyList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReqMyList(String userNick, int reqPage, int numPerPage, String reSearch) {
		super();
		this.userNick = userNick;
		this.reqPage = reqPage;
		this.numPerPage = numPerPage;
		this.reSearch = reSearch;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public String getReSearch() {
		return reSearch;
	}
	public void setReSearch(String reSearch) {
		this.reSearch = reSearch;
	}
	
	
	
}
