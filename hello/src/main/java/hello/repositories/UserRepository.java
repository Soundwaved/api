package hello.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.YUser;

@Repository
public interface UserRepository 
	extends CrudRepository<YUser, Integer> {

}


