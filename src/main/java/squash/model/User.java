package squash.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import squash.util.JSONTools;

@Entity
public class User implements UserDetails, JSONObj {

	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue
	private Long id;
	@NotEmpty(message="Username should not be empty.")
	@Column(unique = true, nullable = false)
	private String username;
	@NotEmpty(message="Password should not be empty.")
	@Column
	private String password;
	@Column
	private String salt;
	@Column
	private boolean enabled = true;
	@Column
	private boolean accountNonExpired = true;
	@Column
	private boolean credentialsNonExpired = true;
	@Column
	private boolean accountNonLocked = true;
	@Email(message="This E-Mail is not valid.")
	@NotEmpty(message="E-Mail should not be empty.")
	@Column(unique = true, nullable = false)
	private String eMail;
	@Column
	private String firstName;
	@Column
	private String lastName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressID")
	private Address addressID;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "leagueID")
	private List<League> league;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Group> groups;
	
	@OneToMany
	private List<Ranking> rankings;
	
	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	public List<League> getLeague() {
		return league;
	}

	public void setLeague(List<League> league) {
		this.league = league;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddressID() {
		return addressID;
	}

	public void setAddressID(Address addressID) {
		this.addressID = addressID;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>(groups.size());
		for ( Group group : groups ) {
			auths.add( (GrantedAuthority)group );
		}
		return auths;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String getJSON() throws JSONException {
		JSONObject obj = getJSONObj();
		return obj.toString();
	}

	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(league!=null){
			obj.put("League", JSONTools.getJSONArray(league));
		}else{
			obj.put("League", "");
		}
		if(rankings!=null){
			obj.put("Rankings", JSONTools.getJSONArray(rankings));
		}else{
			obj.put("Rankings", "");
		}
		if(groups!=null){
			obj.put("Groups", JSONTools.getJSONArray(groups));
		}else{
			obj.put("Groups", "");
		}
		if(addressID!=null){
			obj.put("Address", addressID.getJSONObj());
		}else{
			obj.put("Address", "");
		}
		return obj;
	}

	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("enabled", enabled);
		obj.put("accountNonExpired", accountNonExpired);
		obj.put("credentialsNonExpired", credentialsNonExpired);
		obj.put("username", username);
		obj.put("eMail", eMail);
		obj.put("FirstName", firstName);
		obj.put("LastName", lastName);
		return obj;
	}
	
	
}