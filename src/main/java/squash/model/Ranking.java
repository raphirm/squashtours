package squash.model;


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

import squash.util.JSONTools;

/**
 * This class is used to demonstrate form validation in controller PRGValidationDTOController
 * @author chad
 *
 */
@Entity
public class Ranking implements JSONObj{
	@Id  
	@GeneratedValue
	private long rankingID;
	@ManyToOne
	private League leage;
	
	@ManyToOne
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
	@Override
	public String getJSON() throws JSONException {
		return getJSONObj().toString();
	}
	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(leage!=null){
			obj.put("League", leage.getJSONObj());
			
		}else{
			obj.put("League","");
		}
		if(user!=null){
			obj.put("User", user.getJSONObj());
			
		}else{
			obj.put("User","");
		}
		return obj;
	}
	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", rank);
		obj.put("wins", wins);
		obj.put("loss", loss);
		obj.put("draws", draws);
		obj.put("points", points);
		obj.put("rank", rank);
		
		return obj;
	}
	@Override
	public void update(JSONObj obj) {
		// TODO Auto-generated method stub
		
	}
	
	

}
