package squash.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import squash.model.Address;
import squash.model.Group;
import squash.model.League;
import squash.model.Ranking;
import squash.model.User;
import squash.service.AddressService;
import squash.service.GroupService;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.UserService;
import squash.util.JSONTools;
public class GroupDTO {
	
	
	public void update(JSONObject obj, Group group, GroupService groupService, UserService userService) throws JSONException {
		if(obj.has("name")){
			group.setName(obj.getString("name"));
		}
		
		if(obj.has("users")){
			group.setUsers((List<User>) userService.findAll(JSONTools.getJSONArray(obj.getJSONArray("groups"))));
		}
		
		
		groupService.save(group);
		
	}
	
	public void create(Group group, UserService userService, GroupService groupService){
		if(group.getUsers()!=null){
			List<Long> userList = new ArrayList<Long>();
			for (User user : group.getUsers()) {
				userList.add(user.getId());
			}
			group.setUsers((List<User>) userService.findAll(userList));
		}
		
		groupService.save(group);
	}
	
}
