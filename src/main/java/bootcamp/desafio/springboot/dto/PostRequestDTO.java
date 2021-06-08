package bootcamp.desafio.springboot.dto;

import bootcamp.desafio.springboot.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostRequestDTO {
    private long userId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    @NotBlank(message = "Attribute can not be empty")
    private List<Product> details;
    @NotBlank(message = "Attribute can not be empty")
    private int category;
    @NotBlank(message = "Attribute can not be empty")
    private double price;
    private double discount;

    public PostRequestDTO() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getDetails() {
        return details;
    }

    public void setDetails(List<Product> details) {
        this.details = details;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
