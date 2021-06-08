package bootcamp.desafio.springboot.dto;

public class CountPromoDTO {
    public long userId;
    public String userName;
    public long promoProductsCount;

    public CountPromoDTO(long userId, String userName, long promoProductsCount) {
        this.userId = userId;
        this.userName = userName;
        this.promoProductsCount = promoProductsCount;
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

    public long getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(long promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}
