package pw.marques.squash.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

import pw.marques.squash.util.DateStatus;

/**
 * 
 * @author Raphael Marques
 *
 */
@Entity
public class Dates {
	@Id  
	@GeneratedValue
	private long dateID;
	@Column
	private Date date;
	@Column
	@Enumerated(EnumType.STRING)
	private DateStatus status;
	@Column
	private User origin;
	
	@ManyToOne
	private Match match;

	public long getDateID() {
		return dateID;
	}

	public void setDateID(long dateID) {
		this.dateID = dateID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DateStatus getStatus() {
		return status;
	}

	public void setStatus(DateStatus status) {
		this.status = status;
	}

	public User getOrigin() {
		return origin;
	}

	public void setOrigin(User origin) {
		this.origin = origin;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	

}
