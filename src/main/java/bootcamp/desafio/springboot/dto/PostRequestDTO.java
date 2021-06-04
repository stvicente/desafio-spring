package bootcamp.desafio.springboot.dto;

import bootcamp.desafio.springboot.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PostRequestDTO {
    private long userId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private List<Product> details;
    private int category;
    private double price;

    public PostRequestDTO(long userId, Date date, List<Product> details, int category, double price) {
        this.userId = userId;
        this.date = date;
        this.details = details;
        this.category = category;
        this.price = price;
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

    public void setDetails(List<Product> detail) {
        this.details = detail;
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

    @Override
    public String toString() {
        return "PostRequestDTO{" +
                "userId=" + userId +
                ", date=" + date +
                ", details=" + details +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
