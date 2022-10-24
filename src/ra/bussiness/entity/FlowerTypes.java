package ra.bussiness.entity;

import java.io.Serializable;

public class FlowerTypes implements Serializable {
    private int flowerTypesId;
    private String flowerTypesName;
    private String title;
    private boolean flowerTypesStatus;

    public FlowerTypes() {
    }

    public FlowerTypes(int flowerTypesId, String flowerTypesName, String title, boolean flowerTypesStatus) {
        this.flowerTypesId = flowerTypesId;
        this.flowerTypesName = flowerTypesName;
        this.title = title;
        this.flowerTypesStatus = flowerTypesStatus;
    }

    public int getFlowerTypesId() {
        return flowerTypesId;
    }

    public void setFlowerTypesId(int flowerTypesId) {
        this.flowerTypesId = flowerTypesId;
    }

    public String getFlowerTypesName() {
        return flowerTypesName;
    }

    public void setFlowerTypesName(String flowerTypesName) {
        this.flowerTypesName = flowerTypesName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlowerTypesStatus() {
        return flowerTypesStatus;
    }

    public void setFlowerTypesStatus(boolean flowerTypesStatus) {
        this.flowerTypesStatus = flowerTypesStatus;
    }
}

