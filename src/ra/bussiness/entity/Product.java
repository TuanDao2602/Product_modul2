package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    private String productId;
    private String prductName;
    private float exportPrice;
    private String content;
    private boolean productStatus;
    private Catalog catalog;
    private List<FlowerOfProduct> listFlower = new ArrayList<>();

    public Product() {
    }

    public Product(String productId, String prductName, float exportPrice, String content, boolean productStatus, Catalog catalog, List<FlowerOfProduct> listFlower) {
        this.productId = productId;
        this.prductName = prductName;
        this.exportPrice = exportPrice;
        this.content = content;
        this.productStatus = productStatus;
        this.catalog = catalog;
        this.listFlower = listFlower;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrductName() {
        return prductName;
    }

    public void setPrductName(String prductName) {
        this.prductName = prductName;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<FlowerOfProduct> getListFlower() {
        return listFlower;
    }

    public void setListFlower(List<FlowerOfProduct> listFlower) {
        this.listFlower = listFlower;
    }
}
