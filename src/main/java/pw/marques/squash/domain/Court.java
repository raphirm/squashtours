package pw.marques.squash.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Court {
	
	@Id  
	@GeneratedValue
	private Long courtID;
	
	@Column
	private String courtName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressID", nullable = false)
	private Address address;

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
	

}
