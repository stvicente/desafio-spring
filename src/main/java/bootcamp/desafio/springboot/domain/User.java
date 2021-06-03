package bootcamp.desafio.springboot.domain;

//import lombok.Builder;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean isFollowable;

    public User(){ }

    public User(long id, String name, boolean isFollowable, List<User> followed, List<User> follower) {
        this.id = id;
        this.name = name;
        this.isFollowable = isFollowable;
        this.followed = followed;
        this.follower = follower;
    }

    @ManyToMany
    @JoinTable(name="seller_follower",
            joinColumns=@JoinColumn(name="sellerId"),
            inverseJoinColumns=@JoinColumn(name="clientId")
    )
    private List<User> followed;

    @ManyToMany
    @JoinTable(name="seller_follower",
            joinColumns=@JoinColumn(name="clientId"),
            inverseJoinColumns=@JoinColumn(name="sellerId")
    )
    private List<User> follower;

}
