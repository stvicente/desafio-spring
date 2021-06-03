package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.SellerFollowers;
import bootcamp.desafio.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerFollowersRepository extends JpaRepository<SellerFollowers, Long> {
    @Query(value = "SELECT * FROM  sellers_followers WHERE seller_id = ?1", nativeQuery = true)
    List<SellerFollowers> findAllASellerFollowersById(long userId);
}
