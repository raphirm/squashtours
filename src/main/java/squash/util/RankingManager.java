package squash.util;

import java.util.ArrayList;
import java.util.List;

import squash.model.League;
import squash.model.Ranking;
import squash.model.Satz;
import squash.model.Spiel;
import squash.model.User;
import squash.service.LeagueService;
import squash.service.RankingService;
import squash.service.UserService;

public class RankingManager {

	public static void addMatchToRanking(Spiel spiel, RankingService rankingService) {
		League league = spiel.getLeague();
		User player1 = spiel.getPlayer1();
		User player2 = spiel.getPlayer2();
		List<Satz> sets = spiel.getSets();
		int p1win = 0;
		int p2win = 0;
		for (int i = 0; i < sets.size(); i++) {
			Satz satz = sets.get(i);
			System.out.println("p1:"+satz.getPlayer1Points()+" p2:"+satz.getPlayer2Points());
			if(satz.getPlayer1Points()>satz.getPlayer2Points()){

				p1win = p1win+1;

				System.out.println("Satz für P1!!! "+p1win);

			}
			if(satz.getPlayer1Points() < satz.getPlayer2Points()){
				p2win = p2win+1;
				System.out.println("Satz für P2!!! "+p2win);

			}
		}
		System.out.println("p1win:"+p1win+" p2win:"+p2win);

		Ranking p1ranking = rankingService.findByUserAndLeage(player1, league);
		Ranking p2ranking = rankingService.findByUserAndLeage(player2, league);
		if(p1win>p2win){
			p1ranking.setWins(p1ranking.getWins()+1);
			p2ranking.setLoss(p2ranking.getLoss()+1);
			p1ranking.setPoints(p1ranking.getPoints()+3);
		}else if(p2win>p1win){
			p2ranking.setWins(p2ranking.getWins()+1);
			p1ranking.setLoss(p1ranking.getLoss()+1);
			p2ranking.setPoints(p2ranking.getPoints()+3);
			
		}else{
			p1ranking.setDraws(p1ranking.getDraws()+1);
			p2ranking.setDraws(p2ranking.getDraws()+1);
			p1ranking.setPoints(p1ranking.getPoints()+1);
			p2ranking.setPoints(p2ranking.getPoints()+1);

		}
		rankingService.save(p1ranking);
		rankingService.save(p2ranking);
		
		
	}

	public static void createRanking(User user, League league, UserService userService, LeagueService leagueService, RankingService rankingService) {
		Ranking ranking = new Ranking();
		ranking.setUser(user);
		ranking.setLeage(league);
		ranking.setLoss(0);
		ranking.setWins(0);
		ranking.setPoints(0);
		ranking.setDraws(0);
		if(user.getRankings()!=null){
			user.getRankings().add(ranking);
		}else{
			List<Ranking> rankings = new ArrayList<Ranking>();
			rankings.add(ranking);
			user.setRankings(rankings);
		}
		if(league.getRanking()!=null){
			league.getRanking().add(ranking);
		}else{
			List<Ranking> rankings = new ArrayList<Ranking>();
			rankings.add(ranking);
			league.setRanking(rankings);
		}
		rankingService.save(ranking);
		userService.save(user);
		leagueService.save(league);
		
	}

}
