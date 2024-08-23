package org.example;
import java.util.Objects;

// 商品类
public class Product {
    protected String id;
    protected String name;
    protected String manufacturer;
    protected String productionDate;
    protected String model;
    protected double purchasePrice;
    protected double retailPrice;
    protected int quantity;

    public Product(){
    }
    public Product(String id, String name, String manufacturer, String productionDate, String model, double purchasePrice, double retailPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public double getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(purchasePrice, product.purchasePrice) == 0 && Double.compare(retailPrice, product.retailPrice) == 0 && quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(productionDate, product.productionDate) && Objects.equals(model, product.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, productionDate, model, purchasePrice, retailPrice, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", productionDate=" + productionDate +
                ", model='" + model + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", quantity=" + quantity +
                '}';
    }
}
