package ra.bussiness.entity;

import java.io.Serializable;

public class Flower implements Serializable {
    private String flowerId;
    private String flowerName;
    private FlowerTypes flowerTypes;
    private float importPrice;
    private float price;
    private  String title;
    private  boolean flowerStatus;

    public Flower() {
    }

    public Flower(String flowerId, String flowerName, FlowerTypes flowerTypes, float importPrice, float price, String title, boolean flowerStatus) {
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.flowerTypes = flowerTypes;
        this.importPrice = importPrice;
        this.price = price;
        this.title = title;
        this.flowerStatus = flowerStatus;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public FlowerTypes getFlowerTypes() {
        return flowerTypes;
    }

    public void setFlowerTypes(FlowerTypes flowerTypes) {
        this.flowerTypes = flowerTypes;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlowerStatus() {
        return flowerStatus;
    }

    public void setFlowerStatus(boolean flowerStatus) {
        this.flowerStatus = flowerStatus;
    }
}
