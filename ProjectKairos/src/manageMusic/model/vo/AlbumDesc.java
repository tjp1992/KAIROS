package manageMusic.model.vo;

public class AlbumDesc {
	int songNo;
	String songTitle;
	String albumPath;
	String filepath;
	
	public AlbumDesc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlbumDesc(int songNo, String songTitle, String albumPath, String filepath) {
		super();
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.albumPath = albumPath;
		this.filepath = filepath;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
}
