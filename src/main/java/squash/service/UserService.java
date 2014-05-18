package squash.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import squash.model.User;

public interface UserService extends CrudRepository<User, Long> {

    List<User> findByLastName(String username);
    User findByUsername(String username);
    
}