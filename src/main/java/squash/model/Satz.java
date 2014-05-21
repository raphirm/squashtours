package squash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.json.JSONException;
import org.json.JSONObject;


@Entity
public class Satz implements JSONObj{
	@Id
	@GeneratedValue
	private long setID;
	
	@ManyToOne
	private Spiel match;	
	
	@Column 
	private int player1Points;
	
	@Column
	private int player2Points;
	
	@Column
	private int winner;

	
	public long getSetID() {
		return setID;
	}

	public void setSetID(long setID) {
		this.setID = setID;
	}

	public Spiel getMatch() {
		return match;
	}

	public void setMatch(Spiel match) {
		this.match = match;
	}

	public int getPlayer1Points() {
		return player1Points;
	}

	public void setPlayer1Points(int player1Points) {
		this.player1Points = player1Points;
	}

	public int getPlayer2Points() {
		return player2Points;
	}

	public void setPlayer2Points(int player2Points) {
		this.player2Points = player2Points;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	@Override
	public String getJSON() throws JSONException {
		return getJSONObj().toString();
	}

	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(match!=null){
			obj.put("id", match);
		}
		return obj;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", setID);
		obj.put("player1Points", player1Points);
		obj.put("player2Points", player2Points);
		obj.put("winner", winner);
		return obj;
	}

	

	
	

}
