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
 * @author raphael Marques
 *
 */
@Entity
public class Ranking implements JSONObj{
	@Id  
	@GeneratedValue
	private long id;
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
	public long getId() {
		return id;
	}
	public void setId(long rankingID) {
		this.id = rankingID;
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
			obj.put("league", leage.getJSONObj());
			
		}else{
			obj.put("league","");
		}
		if(user!=null){
			obj.put("user", user.getJSONObj());
			
		}else{
			obj.put("user","");
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
		if(user!=null){
		obj.put("user", user.getUsername());
		}
		if(leage!=null){
			obj.put("league", leage.getId());

		}
		return obj;
	}
	
	
	

}
