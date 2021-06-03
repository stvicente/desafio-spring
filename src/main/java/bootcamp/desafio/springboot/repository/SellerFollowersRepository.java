package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SellerFollowersRepository extends JpaRepository<User, User> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO seller_follower (client_id, seller_id) VALUES (?1, ?2)", nativeQuery = true)
    void follow(long client, long seller);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM seller_follower WHERE client_id = ?1 AND seller_id = ?2", nativeQuery = true)
     void unfollow(long clientId, long sellerId);

    @Query(value = "SELECT client_id FROM seller_follower WHERE seller_id = ?1", nativeQuery = true)
    List<Long> findFollowers(long id);

//    @Query(value = "SELECT seller_id FROM seller_follower WHERE client_id = ?1", nativeQuery = true)
//    List<Long> findFollowed(long id);
}
