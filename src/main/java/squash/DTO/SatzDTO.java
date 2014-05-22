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
import squash.model.Satz;
import squash.model.User;
import squash.service.AddressService;
import squash.service.GroupService;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.SatzService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.JSONTools;
public class SatzDTO {
	
	
	public void update(JSONObject obj, Satz set, SatzService satzService, SpielService spielService) throws JSONException {
		if(obj.has("player1Points")){
			set.setPlayer1Points(obj.getInt("player1Points"));
		}
		if(obj.has("player2Points")){
			set.setPlayer2Points(obj.getInt("player2Points"));
		}
		if(obj.has("winner")){
			set.setWinner(obj.getInt("winner"));
		}
		if(obj.has("match")){
			set.setMatch(spielService.findOne(obj.getJSONObject(("match")).getLong("id")));
		}
		satzService.save(set);
	}
	
	public void create(Satz set, SatzService satzService, SpielService spielService){

		if(set.getMatch()!=null){
			set.setMatch(spielService.findOne(set.getMatch().getMatchID()));
		}
		
		satzService.save(set);
	}
	
}
