package pw.marques.squash.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Ranking {
	@Id  
	@GeneratedValue
	private long rankingID;
	@Column
	@OneToOne
	private League leage;
	@Column
	@OneToOne
	private User user;
	@Column
	private int wins;
	@Column
	private int loss;
	@Column
	private int draws;
	@Column
	private int points;
	@Column
	private int rank;
	public long getRankingID() {
		return rankingID;
	}
	public void setRankingID(long rankingID) {
		this.rankingID = rankingID;
	}
	public League getLeage() {
		return leage;
	}
	public void setLeage(League leage) {
		this.leage = leage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	

}
