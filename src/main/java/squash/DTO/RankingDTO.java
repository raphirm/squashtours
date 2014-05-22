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
public class RankingDTO {
	
	
	public void update(JSONObject obj, Ranking ranking, RankingService rankingService, UserService userService, LeagueService leagueService) throws JSONException {
		if(obj.has("draws")){
			ranking.setDraws(obj.getInt("draws"));
		}
		if(obj.has("loss")){
			ranking.setLoss(obj.getInt("loss"));
		}
		if(obj.has("wins")){
			ranking.setWins(obj.getInt("wins"));
		}
		if(obj.has("points")){
			ranking.setPoints(obj.getInt("points"));
		}
		if(obj.has("rank")){
			ranking.setRank(obj.getInt("rank"));
		}

		if(obj.has("league")){
			ranking.setLeage(leagueService.findOne(obj.getJSONObject(("league")).getLong("id")));
		}
		if(obj.has("user")){
			ranking.setUser(userService.findOne(obj.getJSONObject(("user")).getLong("id")));
		}
		
		
		rankingService.save(ranking);
	}
	
	public void create(Ranking ranking, RankingService rankingService, UserService userService, LeagueService leagueService){

		if(ranking.getUser()!=null){
			ranking.setUser(userService.findOne(ranking.getUser().getId()));
		}
		if(ranking.getLeage()!=null){
			ranking.setLeage(leagueService.findOne(ranking.getLeage().getId()));
		}
		rankingService.save(ranking);
	}
	
}
