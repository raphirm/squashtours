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
public class UserDTO {
	
	
	public void update(JSONObject obj, User user, UserService userService, GroupService groupService, LeagueService leagueService, RankingService rankingService, AddressService addressService) throws JSONException {
		if(obj.has("firstName")){
			user.setFirstName(obj.getString("firstName"));
		}
		if(obj.has("lastName")){
			user.setLastName(obj.getString("lastName"));
		}
		if(obj.has("username")){
			System.out.println("Setting Username");
			user.setUsername(obj.getString("username"));
		}
		if(obj.has("enabled")){
			user.setEnabled(obj.getBoolean(("enabled")));
		}
		if(obj.has("eMail")){
			user.seteMail(obj.getString("eMail"));
		}
		if(obj.has("credentialsNonExpired")){
			user.setCredentialsNonExpired(obj.getBoolean("credentialsNonExpired"));
		}
		if(obj.has("accountNonExpired")){
			user.setAccountNonExpired(obj.getBoolean("accountNonExpired"));
		}
		if(obj.has("groups")){
			user.setGroups((List<Group>) groupService.findAll(JSONTools.getJSONArray(obj.getJSONArray("groups"))));
		}
		if(obj.has("league")){
			user.setLeague((List<League>) leagueService.findAll(JSONTools.getJSONArray(obj.getJSONArray("league"))));
		}
		if(obj.has("ranking")){
			user.setRankings((List<Ranking>) rankingService.findAll(JSONTools.getJSONArray(obj.getJSONArray("ranking"))));
		}
		if(obj.has("address")){
			user.setAddressID(addressService.findOne(obj.getJSONObject(("address")).getLong("id")));
		}
		if(obj.has("password")){
			user.setPassword(obj.getString("password"));
		}
		
		userService.save(user);
		
	}
	
	public void create(User user, UserService userService, GroupService groupService, LeagueService leagueService, RankingService rankingService, AddressService addressService){
		if(user.getGroups()!=null){
			List<Long> groupList = new ArrayList<Long>();
			for (Group group : user.getGroups()) {
				groupList.add(group.getId());
			}
			user.setGroups((List<Group>) groupService.findAll(groupList));
		}
		if(user.getLeague()!=null){
			List<Long> leagueList = new ArrayList<Long>();
			for (League league : user.getLeague()) {
				leagueList.add(league.getId());
			}
			user.setLeague(((List<League>) leagueService.findAll(leagueList)));
		}
		if(user.getRankings()!=null){
			List<Long> rankingList = new ArrayList<Long>();
			for (Ranking ranking : user.getRankings()) {
				rankingList.add(ranking.getId());
			}
			user.setRankings(((List<Ranking>) rankingService.findAll(rankingList)));
		}
		if(user.getAddressID()!=null){
			user.setAddressID(addressService.findOne(user.getAddressID().getId()));
		}
		userService.save(user);
	}
	
}
