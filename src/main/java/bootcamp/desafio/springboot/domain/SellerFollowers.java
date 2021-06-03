package bootcamp.desafio.springboot.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "sellers_followers")
public class SellerFollowers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long seller_id;
    private long client_id;

    public SellerFollowers() {

    }

    public SellerFollowers(long id, long seller_id, long client_id) {
        this.id = id;
        this.seller_id = seller_id;
        this.client_id = client_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }
}


