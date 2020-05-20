package search.model.vo;

public class ReqSearch {
	String userId;
	String keyword;	
	String category;
	String genre;
	int licensed;
	String reSearch;
	int reqPage;
	int numPerPage;
	
	public ReqSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReqSearch(String userId, String keyword, String category, String genre, int licensed, String reSearch,
			int reqPage, int numPerPage) {
		super();
		this.userId = userId;
		this.keyword = keyword;
		this.category = category;
		this.genre = genre;
		this.licensed = licensed;
		this.reSearch = reSearch;
		this.reqPage = reqPage;
		this.numPerPage = numPerPage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getLicensed() {
		return licensed;
	}

	public void setLicensed(int licensed) {
		this.licensed = licensed;
	}

	public String getReSearch() {
		return reSearch;
	}

	public void setReSearch(String reSearch) {
		this.reSearch = reSearch;
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
	
	
	
}
