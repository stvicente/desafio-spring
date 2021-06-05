
package bootcamp.desafio.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean isFollowable;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="seller_follower",
            joinColumns=@JoinColumn(name="sellerId"),
            inverseJoinColumns=@JoinColumn(name="clientId")
    )
    private List<User> follower;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="seller_follower",
            joinColumns=@JoinColumn(name="clientId"),
            inverseJoinColumns=@JoinColumn(name="sellerId")
    )
    private List<User> followed;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(){}

    public User(long id, String name, boolean isFollowable, List<User> follower, List<User> followed, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.isFollowable = isFollowable;
        this.follower = follower;
        this.followed = followed;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFollowable() {
        return isFollowable;
    }

    public void setFollowable(boolean followable) {
        isFollowable = followable;
    }

    public List<User> getFollower() {
        return follower;
    }

    public void setFollower(List<User> follower) {
        this.follower = follower;
    }

    public List<User> getFollowed() {
        return followed;
    }

    public void setFollowed(List<User> followed) {
        this.followed = followed;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}