package squash.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

import squash.util.JSONTools;
import squash.util.MatchStatus;


/**
 * This class is used to demonstrate form validation in controller PRGValidationDTOController
 * @author chad
 *
 */
@Entity
public class Spiel implements JSONObj{
	@Id  
	@GeneratedValue
	private long matchID;
	
	@ManyToOne
	private User player1;
	
	@ManyToOne
	private User player2;
	
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Dates> date;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Satz> sets;
	
	
	@Column
	@Enumerated(EnumType.STRING)
	private squash.util.MatchStatus player1Status;
	
	@Column
	@Enumerated(EnumType.STRING)
	private MatchStatus player2Status;

	public List<Satz> getSets() {
		return sets;
	}

	public void setSets(List<Satz> sets) {
		this.sets = sets;
	}

	public long getMatchID() {
		return matchID;
	}

	public void setMatchID(long matchID) {
		this.matchID = matchID;
	}

	public User getPlayer1() {
		return player1;
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public User getPlayer2() {
		return player2;
	}

	public void setPlayer2(User player2) {
		this.player2 = player2;
	}

	public List<Dates> getDate() {
		return date;
	}

	public void setDate(List<Dates> date) {
		this.date = date;
	}

	

	public MatchStatus getPlayer1Status() {
		return player1Status;
	}

	public void setPlayer1Status(MatchStatus player1Status) {
		this.player1Status = player1Status;
	}

	public MatchStatus getPlayer2Status() {
		return player2Status;
	}

	public void setPlayer2Status(MatchStatus player2Status) {
		this.player2Status = player2Status;
	}

	@Override
	public String getJSON() throws JSONException {
		return getJSONObj().toString();
	}

	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(date!=null){
			obj.put("date", JSONTools.getJSONArray(date));
		}
		else{
			obj.put("date", "");
		}
		if(player1!=null){
			obj.put("player1", player1.getJSONObj());
		}else{
			obj.put("player1", "");
		}
		if(player2!=null){
			obj.put("player2", player2.getJSONObj());
		}else{
			obj.put("player2", "");
		}
		return obj;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", matchID);
		
		if(player1Status!=null){
			obj.put("player1Status", player1Status.toString());
		}else{
			obj.put("player1Status", "");
		}
		if(player2Status!=null){
		obj.put("player2Status", player2Status.toString());
		}else{
			obj.put("player2Status", "");
		}
		return obj;
	}
	
	
}