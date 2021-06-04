package bootcamp.desafio.springboot.dto;

public class UserRequestDTO {
    public String name;

    public UserRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
