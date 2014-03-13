package pw.marques.squash.domain;


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
public class Address {
	
	@Id  
	@GeneratedValue
	private Long addressID;

	private String firstName;
	private String lastName;
	@Column
	private String street1;
	@Column
	private String street2;
	@NotEmpty(message="{address.city}")
	private String city;
	private String state;
	private String zip;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getName(){
		return firstName + " " + lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setName(String name){
		String[] split = name.split(" ");
		firstName = split[0];
		lastName = split[1];	
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String toString() {
		return "name="+getFirstName()+", "+getLastName()+" address1="+getStreet1()+" address2="+getStreet2()+" city="+getCity()+" state="+getState()+" zip="+getZip();
	}
	public String getJSON() throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("Street1", street1);
		return obj.toString();
	}

	
}
