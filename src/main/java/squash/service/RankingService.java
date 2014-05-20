package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.Ranking;
import squash.model.User;

public interface RankingService extends CrudRepository<Ranking, Long> {

    
}