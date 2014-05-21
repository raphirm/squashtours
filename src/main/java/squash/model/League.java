package squash.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class League implements JSONObj{
	@Id  
	@GeneratedValue
	private long leagueID;
	
	@OneToMany
	private List<Ranking> ranking;
	
	@ManyToMany
	private List<User> user;
	
	
	@OneToMany
	private List<Spiel> match;
	
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
	public List<Ranking> getRanking() {
		return ranking;
	}
	public void setRanking(List<Ranking> ranking) {
		this.ranking = ranking;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<Spiel> getSpiel() {
		return match;
	}
	public void setSpiel(List<Spiel> match) {
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
	@Override
	public String getJSON() throws JSONException {
		return getJSONObj().toString();
	}
	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(ranking!=null){
			obj.put("Ranking", JSONTools.getJSONArray(ranking));
			
		}else{
			obj.put("Ranking","");
		}
		if(user!=null){
			obj.put("user", JSONTools.getJSONArray(user));
			
		}else{
			obj.put("user","");
		}
		if(match!=null){
			obj.put("match", JSONTools.getJSONArray(match));
			
		}else{
			obj.put("match","");
		}
		return obj;
	}
	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", leagueID);
		obj.put("title", title);
		obj.put("description", description);
		
		return obj;
	}
	
	
	
	

}
