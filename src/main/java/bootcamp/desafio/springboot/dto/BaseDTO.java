package bootcamp.desafio.springboot.dto;

public class BaseDTO {
    private long id;
    private String name;

    public BaseDTO() {}

    public BaseDTO(Long id, String name) {
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
}
