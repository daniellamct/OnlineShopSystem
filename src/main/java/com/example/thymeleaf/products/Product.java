package com.example.thymeleaf.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Document(collection = "my_products_mongo_db") // The name of mongodb collection
public class Product {
    @Id
    @JsonProperty("id")
    private String id; // Database ID

    @JsonProperty("js_id")
    private String js_id;  // ID for the product

    @JsonProperty("Name")
    private String Name;    // Name of the product

    @JsonProperty("Description")
    private String Description; // Description of the product

    @JsonProperty("RegularPrice")
    private double RegularPrice; // Price of the product

    @JsonProperty("ProductCategory")
    private String ProductCategory; // Category of the product

    @JsonProperty("Keywords")
    private List<String> Keywords; // Keywords of the product 

    @JsonProperty("ImageSource")
    private String ImageSource; // ImageSource of the product

    @JsonProperty("Quantity")
    private int Quantity; // Default quantity of the product for customers

    @JsonProperty("Show")
    private boolean Show; // Determine if the product is on sell

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    
    public String getJs_id() {
        return js_id;
    }

    public void setJs_id(String js_id) {
        this.js_id = js_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public double getRegularPrice() {
        return RegularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.RegularPrice = regularPrice;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        this.ProductCategory = productCategory;
    }

    public List<String> getKeywords() {
        return Keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.Keywords = keywords;
    }

    public String getImageSource() {
        return ImageSource;
    }

    public void setImageSource(String imageSource) {
        this.ImageSource = imageSource;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public boolean getShow() {
        return Show;
    }

    public void setShow(boolean show) {
        this.Show = show;
    }
}