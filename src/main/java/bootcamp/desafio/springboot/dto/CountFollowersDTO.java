package bootcamp.desafio.springboot.dto;

public class CountFollowersDTO {
    private long userId;
    private String userName;
    private int followersCount;

    public CountFollowersDTO() {

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

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
}
