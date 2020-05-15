package playlist.vo;

public class Playlist {
	private int orderNo;
	private int songNo;
	private String songTitle;
	private String songArtist;
	private String albumName;
	private int liked; //0이면 좋아요 안되있던거 1이면 좋아요상태
	private String filepath;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int orderNo, int songNo, String songTitle, String songArtist, String albumName, int liked,
			String filepath) {
		super();
		this.orderNo = orderNo;
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
		this.albumName = albumName;
		this.liked = liked;
		this.filepath = filepath;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getSongNo() {
		return songNo;
	}
	public void setSongNo(int songNo) {
		this.songNo = songNo;
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
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}
