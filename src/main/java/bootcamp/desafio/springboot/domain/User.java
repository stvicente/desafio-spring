package bootcamp.desafio.springboot.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean isFollowable;

    public User(){ }

    public User(long id, String name, boolean isFollowable) {
        this.id = id;
        this.name = name;
        this.isFollowable = isFollowable;
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

}
