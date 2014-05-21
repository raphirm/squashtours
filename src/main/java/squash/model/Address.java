package squash.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to demonstrate form validation in controller PRGValidationDTOController
 * @author chad
 *
 */
@Entity
public class Address implements JSONObj{
	
	@Id  
	@GeneratedValue
	private Long id;
	@Column
	private String street1;
	@Column
	private String street2;
	@Column
	private String city;
	@Column
	private String zip;
	

	public Long getId() {
		return id;
	}
	public void setId(Long addressID) {
		this.id = addressID;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String toString() {
		return "address1="+getStreet1()+" address2="+getStreet2()+" city="+getCity()+" zip="+getZip();
	}
	public String getJSON() throws JSONException{
		JSONObject obj = getJSONObj();
		return obj.toString();
	}
	public JSONObject getJSONObj() throws JSONException{
		JSONObject obj = getJSONObjSave();
		return obj;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("street1", street1);
		obj.put("street2", street2);
		obj.put("city", city);
		obj.put("zip", zip);
		return obj;
	}
	

	
}
