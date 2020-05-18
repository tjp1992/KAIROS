package manageMusic.model.vo;

public class Album {
	private int albumNo;
	private String albumOwner;
	private String albumName;
	private String albumPath;
	
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(int albumNo, String albumOwner, String albumName, String albumPath) {
		super();
		this.albumNo = albumNo;
		this.albumOwner = albumOwner;
		this.albumName = albumName;
		this.albumPath = albumPath;
	}

	public int getAlbumNo() {
		return albumNo;
	}

	public void setAlbumNo(int albumNo) {
		this.albumNo = albumNo;
	}

	public String getAlbumOwner() {
		return albumOwner;
	}

	public void setAlbumOwner(String albumOwner) {
		this.albumOwner = albumOwner;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}
	
	
	
}
