package pw.marques.squash.domain;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue
	private Long id;
	@Column
	private String username;
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
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
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
	
}
