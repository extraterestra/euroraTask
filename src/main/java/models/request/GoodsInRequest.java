package models.request;

public class GoodsInRequest {
    private String externalId;
    private String gtin;
    private String title;
    private String description;
    private String hsCode;
    private Price price;
    private double weight;
    private int quantity;
    private int additionalValueShareRatio;

    public GoodsInRequest(String externalId, String gtin, String title, String description, String hsCode, Price price, double weight, int quantity, int additionalValueShareRatio) {
        this.externalId = externalId;
        this.gtin = gtin;
        this.title = title;
        this.description = description;
        this.hsCode = hsCode;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
        this.additionalValueShareRatio = additionalValueShareRatio;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAdditionalValueShareRatio() {
        return additionalValueShareRatio;
    }

    public void setAdditionalValueShareRatio(int additionalValueShareRatio) {
        this.additionalValueShareRatio = additionalValueShareRatio;
    }

}
