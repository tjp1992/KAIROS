package manageMusic.model.vo;

public class LicensedArtist {
	String lcnArtistName;
	String lcnCompany;
	String lcnAgentName;
	String lcnAgentPhone;
	public LicensedArtist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LicensedArtist(String lcnArtistName, String lcnCompany, String lcnAgentName, String lcnAgentPhone) {
		super();
		this.lcnArtistName = lcnArtistName;
		this.lcnCompany = lcnCompany;
		this.lcnAgentName = lcnAgentName;
		this.lcnAgentPhone = lcnAgentPhone;
	}
	public String getLcnArtistName() {
		return lcnArtistName;
	}
	public void setLcnArtistName(String lcnArtistName) {
		this.lcnArtistName = lcnArtistName;
	}
	public String getLcnCompany() {
		return lcnCompany;
	}
	public void setLcnCompany(String lcnCompany) {
		this.lcnCompany = lcnCompany;
	}
	public String getLcnAgentName() {
		return lcnAgentName;
	}
	public void setLcnAgentName(String lcnAgentName) {
		this.lcnAgentName = lcnAgentName;
	}
	public String getLcnAgentPhone() {
		return lcnAgentPhone;
	}
	public void setLcnAgentPhone(String lcnAgentPhone) {
		this.lcnAgentPhone = lcnAgentPhone;
	}
	
	
}
