package squash.model;


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

import squash.util.DateStatus;
import squash.util.JSONTools;
/**
 * 
 * @author Raphael Marques
 *
 */
@Entity
public class Dates implements JSONObj{
	@Id  
	@GeneratedValue
	private long id;
	@Column
	private Date date;
	@Column
	@Enumerated(EnumType.STRING)
	private DateStatus status;
	@ManyToOne
	private User origin;
	
	@ManyToOne
	private Spiel match;

	public long getId() {
		return id;
	}

	public void getId(long dateID) {
		this.id = dateID;
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

	public Spiel getSpiel() {
		return match;
	}

	public void setSpiel(Spiel match) {
		this.match = match;
	}
	public String getJSON() throws JSONException{
		JSONObject result = new JSONObject();

		return result.toString();
	}

	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject result = getJSONObjSave();
		if(match!=null){
			result.put("match", match.getJSONObj());
		}else{
			result.put("match", "");
		}
		if(origin!=null){
			result.put("origin", origin.getJSONObj());
		}else{
			result.put("origin", "");
		}
		
		return result;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject result = new JSONObject();
		result.put("id", id);
		result.put("date", date);
		result.put("status", status);		
		return result;
	}


	
	

}
