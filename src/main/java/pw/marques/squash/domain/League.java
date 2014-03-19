package pw.marques.squash.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to demonstrate form validation in controller PRGValidationDTOController
 * @author chad
 *
 */
@Entity
public class League {
	@Id  
	@GeneratedValue
	private long leagueID;
	@Column
	@OneToOne
	private Ranking ranking;
	@Column
	@ManyToMany
	private List<User> user;
	
	@Column
	@ManyToOne
	private List<Match> match;
	
	@Column
	private String title;
	@Column
	private String description;
	public long getLeagueID() {
		return leagueID;
	}
	public void setLeagueID(long leagueID) {
		this.leagueID = leagueID;
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<Match> getMatch() {
		return match;
	}
	public void setMatch(List<Match> match) {
		this.match = match;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
