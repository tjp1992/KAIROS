package song.vo;

public class RankingSong {
	private int rankNo;
	private int songNo;
	private String songTitle;
	private String songArtist;
	private String songGenre;
	private int albumNo;
	private int playCount;
	private int likeCount;
	private String filename;
	private String filepath;
	private int licensed;
	
	public RankingSong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RankingSong(int rankNo, int songNo, String songTitle, String songArtist, String songGenre, int albumNo,
			int playCount, int likeCount, String filename, String filepath, int licensed) {
		super();
		this.rankNo = rankNo;
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
		this.songGenre = songGenre;
		this.albumNo = albumNo;
		this.playCount = playCount;
		this.likeCount = likeCount;
		this.filename = filename;
		this.filepath = filepath;
		this.licensed = licensed;
	}
	public int getRankNo() {
		return rankNo;
	}
	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
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
	public String getSongGenre() {
		return songGenre;
	}
	public void setSongGenre(String songGenre) {
		this.songGenre = songGenre;
	}
	public int getAlbumNo() {
		return albumNo;
	}
	public void setAlbumNo(int albumNo) {
		this.albumNo = albumNo;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getLicensed() {
		return licensed;
	}
	public void setLicensed(int licensed) {
		this.licensed = licensed;
	}
		
	
	
}
