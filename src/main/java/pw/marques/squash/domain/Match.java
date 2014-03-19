package pw.marques.squash.domain;


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

import pw.marques.squash.util.MatchStatus;

/**
 * This class is used to demonstrate form validation in controller PRGValidationDTOController
 * @author chad
 *
 */
@Entity
public class Match {
	@Id  
	@GeneratedValue
	private long matchID;
	
	@ManyToOne
	private User player1;
	
	@ManyToOne
	private User player2;
	
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<Dates> date;
	
	@Column
	private int player1Set;
	
	@Column
	private int player2Set;
	
	@Column
	private int player1Point;
	
	@Column
	private int player2Point;
	
	@Column
	@Enumerated(EnumType.STRING)
	private MatchStatus player1Status;
	
	@Column
	@Enumerated(EnumType.STRING)
	private MatchStatus player2Status;

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

	public int getPlayer1Set() {
		return player1Set;
	}

	public void setPlayer1Set(int player1Set) {
		this.player1Set = player1Set;
	}

	public int getPlayer2Set() {
		return player2Set;
	}

	public void setPlayer2Set(int player2Set) {
		this.player2Set = player2Set;
	}

	public int getPlayer1Point() {
		return player1Point;
	}

	public void setPlayer1Point(int player1Point) {
		this.player1Point = player1Point;
	}

	public int getPlayer2Point() {
		return player2Point;
	}

	public void setPlayer2Point(int player2Point) {
		this.player2Point = player2Point;
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
	
	
}
