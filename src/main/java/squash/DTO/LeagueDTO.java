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
import squash.model.Spiel;
import squash.model.User;
import squash.service.AddressService;
import squash.service.GroupService;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.JSONTools;
public class LeagueDTO {
	
	
	public void update(JSONObject obj, League league, LeagueService leagueService, UserService userService, RankingService rankingService, SpielService spielService) throws JSONException {
		if(obj.has("title")){
			league.setTitle(obj.getString("title"));
		}
		if(obj.has("description")){
			league.setDescription(obj.getString("description"));
		}

		if(obj.has("match")){
			league.setSpiel((List<Spiel>) spielService.findAll(JSONTools.getJSONArray(obj.getJSONArray("match"))));
		}
		if(obj.has("user")){
			league.setUser((List<User>) userService.findAll(JSONTools.getJSONArray(obj.getJSONArray("user"))));
		}
		if(obj.has("ranking")){
			league.setRanking((List<Ranking>) rankingService.findAll(JSONTools.getJSONArray(obj.getJSONArray("ranking"))));
		}

		
		
		leagueService.save(league);
		
	}
	
	public void create(League league, LeagueService leagueService, UserService userService, RankingService rankingService, SpielService spielService){
		if(league.getUser()!=null){
			List<Long> userList = new ArrayList<Long>();
			for (User user : league.getUser()) {
				userList.add(user.getId());
			}
			league.setUser((List<User>) userService.findAll(userList));
		}

		if(league.getRanking()!=null){
			List<Long> rankingList = new ArrayList<Long>();
			for (Ranking ranking : league.getRanking()) {
				rankingList.add(ranking.getId());
			}
			league.setRanking(((List<Ranking>) rankingService.findAll(rankingList)));
		}
		if(league.getSpiel()!=null){
			List<Long> matchLIst = new ArrayList<Long>();
			for (Spiel match : league.getSpiel()) {
				matchLIst.add(match.getId());
			}
			league.setSpiel((List<Spiel>) spielService.findAll(matchLIst));
		}
		
		leagueService.save(league);
	}
	
}
