package bootcamp.desafio.springboot.dto;

import bootcamp.desafio.springboot.domain.Post;

import java.util.List;

public class PostListDTO {
    public long userId;
    public String userName;
    public List<Post> posts;

    public PostListDTO(){}

    public PostListDTO(long userId, String userName, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
