package squash.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;

import squash.util.JSONTools;

@Entity
@Table(name="Groups")
public class Group implements GrantedAuthority, JSONObj {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
    @ManyToMany(mappedBy="groups")
	private List<User> users;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthority() {
		return name;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<User> getUsers() {
		return users;
	}
	@Override
	public String getJSON() throws JSONException {
		JSONObject obj = getJSONObj();
		return obj.toString();
	}
	@Override
	public JSONObject getJSONObj() throws JSONException {
		JSONObject obj = getJSONObjSave();
		if(users!=null){
			obj.put("users", JSONTools.getJSONArray(users));
		}else{
			obj.put("users", "");
		}
		return obj;
	}
	@Override
	public JSONObject getJSONObjSave() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("name", name);
		return obj;
	}
	
}
