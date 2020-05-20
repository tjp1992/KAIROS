package song.vo;

public class RankingSong {
	private int rankNo;
	private int songNo;
	private String songTitle;
	private String songArtist;
	private String songGenre;
	private String albumName;
	private int playCount;
	private int likeCount;
	private String filename;
	private String filepath;
	private int licensed;
	private int liked;
	
	
	
	public RankingSong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public RankingSong(int rankNo, int songNo, String songTitle, String songArtist, String songGenre, String albumName,
			int playCount, int likeCount, String filename, String filepath, int licensed, int liked) {
		super();
		this.rankNo = rankNo;
		this.songNo = songNo;
		this.songTitle = songTitle;
		this.songArtist = songArtist;
		this.songGenre = songGenre;
		this.albumName = albumName;
		this.playCount = playCount;
		this.likeCount = likeCount;
		this.filename = filename;
		this.filepath = filepath;
		this.licensed = licensed;
		this.liked = liked;
	}




	/**
	 * @return the liked
	 */
	public int getLiked() {
		return liked;
	}

	/**
	 * @param liked the liked to set
	 */
	public void setLiked(int liked) {
		this.liked = liked;
	}

	/**
	 * @return the rankNo
	 */
	public int getRankNo() {
		return rankNo;
	}
	/**
	 * @param rankNo the rankNo to set
	 */
	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}
	/**
	 * @return the songNo
	 */
	public int getSongNo() {
		return songNo;
	}
	/**
	 * @param songNo the songNo to set
	 */
	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}
	/**
	 * @return the songTitle
	 */
	public String getSongTitle() {
		return songTitle;
	}
	/**
	 * @param songTitle the songTitle to set
	 */
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	/**
	 * @return the songArtist
	 */
	public String getSongArtist() {
		return songArtist;
	}
	/**
	 * @param songArtist the songArtist to set
	 */
	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}
	/**
	 * @return the songGenre
	 */
	public String getSongGenre() {
		return songGenre;
	}
	/**
	 * @param songGenre the songGenre to set
	 */
	public void setSongGenre(String songGenre) {
		this.songGenre = songGenre;
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
	/**
	 * @return the playCount
	 */
	public int getPlayCount() {
		return playCount;
	}
	/**
	 * @param playCount the playCount to set
	 */
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	/**
	 * @return the likeCount
	 */
	public int getLikeCount() {
		return likeCount;
	}
	/**
	 * @param likeCount the likeCount to set
	 */
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the licensed
	 */
	public int getLicensed() {
		return licensed;
	}
	/**
	 * @param licensed the licensed to set
	 */
	public void setLicensed(int licensed) {
		this.licensed = licensed;
	}
	
	
	
}
