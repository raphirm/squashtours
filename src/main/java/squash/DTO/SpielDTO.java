package squash.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import squash.model.Address;
import squash.model.Dates;
import squash.model.Group;
import squash.model.League;
import squash.model.Ranking;
import squash.model.Satz;
import squash.model.Spiel;
import squash.model.User;
import squash.service.AddressService;
import squash.service.DatesService;
import squash.service.GroupService;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.SatzService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.DateStatus;
import squash.util.JSONTools;
import squash.util.RankingManager;
public class SpielDTO {
	
	
	public void update(JSONObject obj, Spiel match, SpielService spielService, UserService userService, DatesService datesService, SatzService satzService, RankingService rankingService) throws JSONException {
		if(obj.has("player1")){
			match.setPlayer1(userService.findOne(obj.getJSONObject(("player1")).getLong("id")));
		}
		if(obj.has("player2")){
			match.setPlayer2(userService.findOne(obj.getJSONObject(("player2")).getLong("id")));
		}
		if(obj.has("date")){
			match.setDate((List<Dates>) datesService.findAll(JSONTools.getJSONArray(obj.getJSONArray("date"))));
			for (Dates date : match.getDate()) {
				date.setStatus(DateStatus.PROPOSED);
				date.setSpiel(match);
				
			}
		}
		if(obj.has("sets")){
			match.setSets((List<Satz>) satzService.findAll(JSONTools.getJSONArray(obj.getJSONArray("sets"))));
		}
		if(obj.has("closed")){
			match.setClosed(obj.getBoolean("closed"));
			if(obj.getBoolean("closed")){
				RankingManager.addMatchToRanking(match, rankingService);
			}
		}
		
		
		spielService.save(match);
		
	}
	
	public void create(Spiel match, SpielService spielService, UserService userService, DatesService datesService, SatzService satzService, LeagueService leagueService){
		League league = new League();
		if(match.getPlayer1().getId()!=null){
			match.setPlayer1(userService.findOne(match.getPlayer1().getId()));
		}
		if(match.getPlayer1().geteMail()!=null){
			match.setPlayer1(userService.findByEMail(match.getPlayer1().geteMail()));
		}
		if(match.getPlayer2().getId()!=null){
			match.setPlayer2(userService.findOne(match.getPlayer2().getId()));
		}
		if(match.getLeague()!=null){
			league = leagueService.findOne(match.getLeague().getId());
			match.setLeague(league);
			if(league.getSpiel()!=null){
				league.getSpiel().add(match);
			}else{
				List<Spiel> spiele = new ArrayList<Spiel>();
				spiele.add(match);
				league.setSpiel(spiele);
				
			}
			
		}
		if(match.getPlayer2().getUsername()!=null){
			match.setPlayer2(userService.findByUsername(match.getPlayer2().getUsername()));
		}
		if(match.getDate()!=null){
			List<Long> dateList = new ArrayList<Long>();
			for (Dates date : match.getDate()) {
				dateList.add(date.getId());
			}
			match.setDate((List<Dates>) datesService.findAll(dateList));
			for (Dates date : match.getDate()) {
				date.setStatus(DateStatus.PROPOSED);
				date.setSpiel(match);
				
			}
		}
		if(match.getSets()!=null){
			List<Long> setList = new ArrayList<Long>();
			for (Satz set : match.getSets()) {
				setList.add(set.getId());
			}
			match.setSets((List<Satz>) satzService.findAll(setList));
		}
	
		
		spielService.save(match);
		if(match.getLeague()!=null){
			leagueService.save(league);

		}
	}
	
}
