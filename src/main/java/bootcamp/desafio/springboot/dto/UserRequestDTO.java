package bootcamp.desafio.springboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
    private String name;
    private boolean followable;


    public UserRequestDTO(String name, boolean followable) {
        this.name = name;
        this.followable = followable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFollowable() {
        return followable;
    }

    public void setFollowable(boolean followable) {
        this.followable = followable;
    }
}
