package playlist.vo;

public class Playlist {
	private int orderNo;
	private String songTitle;
	private String songArtist;
	private String albumName;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int orderNo, String songTitle, String songArtist, String albumName) {
		super();
		this.orderNo = orderNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
		this.albumName = albumName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	public String getSongArtist() {
		return songArtist;
	}
	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	
	
}
