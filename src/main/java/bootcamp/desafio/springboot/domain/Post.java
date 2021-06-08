package bootcamp.desafio.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private int category;
    private double price;
    private boolean hasPromo = false;
    private double discount = 0;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Product> details = new ArrayList<>();

    public Post() {
    }

    public Post(Date date, int category, double price, User user) {
        this.date = date;
        this.category = category;
        this.price = price;
        this.user = user;
    }

    public Post(Date date, int category, double price, boolean hasPromo, double discount, User user) {
        this.date = date;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getDetails() {
        return details;
    }

    public void setDetails(List<Product> details) {
        this.details = details;
    }
}


