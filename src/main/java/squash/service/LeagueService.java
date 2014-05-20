package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.League;
import squash.model.User;

public interface LeagueService extends CrudRepository<League, Long> {

    
}