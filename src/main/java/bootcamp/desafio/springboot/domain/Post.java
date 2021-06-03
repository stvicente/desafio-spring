package bootcamp.desafio.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Temporal(TemporalType.DATE)
    private Date datePost;
    private boolean hasPromo=false;
    private String category;
    private double price;

    @ManyToOne
    private User userId;

    @OneToMany
    private List<Product> details;

    public Post() {
    }

    public Post(long id, Date datePost, boolean hasPromo, String category, double price, User userId, List<Product> details) {
        this.id = id;
        this.datePost = datePost;
        this.hasPromo = hasPromo;
        this.category = category;
        this.price = price;
        this.userId = userId;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<Product> getDetails() {
        return details;
    }

    public void setDetails(List<Product> details) {
        this.details = details;
    }
}
