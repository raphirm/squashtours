package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.Address;
import squash.model.Spiel;
import squash.model.User;

public interface SpielService extends CrudRepository<Spiel, Long> {

    
}