package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Autowired
//    EntityManager entityManager = null;
//    List<BaseDTO> q = entityManager.createNativeQuery("SELECT id, name FROM user WHERE id = ?1 AND is_followable = true").getResultList();
//    List<Object[]> authors = q.getResult();
//    HashMap<Long, String> result = new Map<Long, String>;
//    @Query(value = "SELECT id, name FROM user WHERE id = ?1 AND is_followable = true", nativeQuery = true)
//    ArrayList<String> findAllASellerFollowersById(long userId);
//    @Query(value = "SELECT name FROM user WHERE id = ?1 AND is_followable = true", nativeQuery = true)
//    String findAllASellerFollowersById(long userId);

    @Query(value = "SELECT client_id FROM sellers_followers WHERE seller_id = ?1", nativeQuery = true)
    List<Long> findFollowers(long id);

    @Query(value = "SELECT seller_id FROM sellers_followers WHERE client_id = ?1", nativeQuery = true)
    List<Long> findFollowed(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sellers_followers WHERE client_id = ?1 AND seller_id = ?2", nativeQuery = true)
    List<Long> unfollow(long clientId, long sellerId);


//
//    public interface Test {
//        long getId();
//        String getName();
//    }

//    public static interface Test {
//
//        HashMap<Long, String> teste = new HashMap<>();
//
//    }

}
