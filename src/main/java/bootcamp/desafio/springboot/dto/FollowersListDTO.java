package bootcamp.desafio.springboot.dto;

import java.util.List;

public class FollowersListDTO {
    private long userId;
    private String userName;
    private List<BaseDTO> followers;
    
    public FollowersListDTO() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<BaseDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<BaseDTO> followers) {
        this.followers = followers;
    }

}
