package squash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.json.JSONException;
import org.json.JSONObject;
@Entity
public class Court implements JSONObj{
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "courtID")
	private Long courtID;
	
	@Column
	private String courtName;
	@Column
	private String telephoneNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressID")
	private Address address;

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Long getCourtID() {
		return courtID;
	}

	public void setCourtID(Long courtID) {
		this.courtID = courtID;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String getJSON() throws JSONException {
		JSONObject obj = getJSONObj();
		return obj.toString();
	}

	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(address!=null){
			obj.put("Address", address.getJSONObj());
		}
		return obj;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", courtID);
		obj.put("Name", courtName);
		obj.put("tel", telephoneNumber);
		return obj;
	}
	

}
