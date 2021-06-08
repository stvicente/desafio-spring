package bootcamp.desafio.springboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    private String name;
    private boolean isFollowable;


    public UserRequestDTO(String name, boolean isFollowable) {
        this.name = name;
        this.isFollowable = isFollowable;
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
