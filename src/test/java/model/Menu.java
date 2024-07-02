package model;

import com.google.gson.annotations.SerializedName;

public class Menu {
    public String title;
    @SerializedName("Product")
    public Product product;

    public static class Product {
        public String id;
        public boolean value;

    }
}
