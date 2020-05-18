package manageMusic.model.vo;

public class AlbumDesc {
	int songNo;
	String songTitle;
	String albumPath;
	public AlbumDesc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumDesc(int songNo, String songTitle, String albumPath) {
		super();
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.albumPath = albumPath;
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
	public String getAlbumPath() {
		return albumPath;
	}
	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	
}
