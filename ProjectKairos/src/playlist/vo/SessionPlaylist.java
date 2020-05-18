package playlist.vo;

public class SessionPlaylist {
	int songNo;
	int orderNo;
	String songArtist;
	String songTitle;
	String filepath;
	String albumPath;
	String albumName;
	int liked;
	
	public SessionPlaylist() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public SessionPlaylist(int songNo, int orderNo, String songArtist, String songTitle, String filepath,
			String albumPath, String albumName, int liked) {
		super();
		this.songNo = songNo;
		this.orderNo = orderNo;
		this.songArtist = songArtist;
		this.songTitle = songTitle;
		this.filepath = filepath;
		this.albumPath = albumPath;
		this.albumName = albumName;
		this.liked = liked;
	}



	/**
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * @param albumName the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getSongNo() {
		return songNo;
	}

	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	
	
}
