package playlist.vo;

public class Playlist {
	private String userId;
	private int listedSongNo;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(String userId, int listedSongNo) {
		super();
		this.userId = userId;
		this.listedSongNo = listedSongNo;
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
	
	
}
