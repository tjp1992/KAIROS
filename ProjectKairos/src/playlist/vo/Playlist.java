package playlist.vo;

public class Playlist {
	private String userId;
	private int listedSongNo;
	private int orderNo;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(String userId, int listedSongNo, int orderNo) {
		super();
		this.userId = userId;
		this.listedSongNo = listedSongNo;
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getListedSongNo() {
		return listedSongNo;
	}
	public void setListedSongNo(int listedSongNo) {
		this.listedSongNo = listedSongNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
	
}
