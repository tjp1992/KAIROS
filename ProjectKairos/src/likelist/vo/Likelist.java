package likelist.vo;

public class Likelist {
	private String userId;
	private int likedSongNo;
	public Likelist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Likelist(String userId, int likedSongNo) {
		super();
		this.userId = userId;
		this.likedSongNo = likedSongNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getLikedSongNo() {
		return likedSongNo;
	}
	public void setLikedSongNo(int likedSongNo) {
		this.likedSongNo = likedSongNo;
	}
	
	
}
