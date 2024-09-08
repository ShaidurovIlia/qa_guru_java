package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductModel {
    @JsonProperty("PRODUCT_ID")
    private String productId;
    @JsonProperty("OFFER_ID")
    private String offerId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
