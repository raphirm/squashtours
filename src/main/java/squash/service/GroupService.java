package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.Group;
import squash.model.League;
import squash.model.Ranking;
import squash.model.Satz;
import squash.model.Spiel;
import squash.model.User;

public interface GroupService extends CrudRepository<Group, Long> {

    
}