package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
