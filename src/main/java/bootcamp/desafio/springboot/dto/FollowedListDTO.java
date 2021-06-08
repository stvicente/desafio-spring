package bootcamp.desafio.springboot.dto;

import java.util.List;

public class FollowedListDTO {
    private long userId;
    private String userName;
    private List<BaseDTO> followed;

    public FollowedListDTO() {

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

    public List<BaseDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<BaseDTO> followed) {
        this.followed = followed;
    }
}
