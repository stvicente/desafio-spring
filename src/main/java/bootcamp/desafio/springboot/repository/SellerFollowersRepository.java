package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.SellerFollowers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerFollowersRepository extends JpaRepository<SellerFollowers, Long> {
}
