package models.request;

public class GoodsInRequest {
    private final String externalId;
    private String gtin;
    private String title;
    private final String description;
    private String hsCode;
    private final Price price;
    private final double weight;
    private final int quantity;
    private int additionalValueShareRatio;

    public GoodsInRequest(String externalId, String description, Price price, double weight, int quantity) {
        this.externalId = externalId;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

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

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public Price getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAdditionalValueShareRatio() {
        return additionalValueShareRatio;
    }

    public void setAdditionalValueShareRatio(int additionalValueShareRatio) {
        this.additionalValueShareRatio = additionalValueShareRatio;
    }

    public static class GoodsInRequestBuilder {
        private final String externalId;
        private String gtin;
        private String title;
        private final String description;
        private String hsCode;
        private final Price price;
        private final double weight;
        private final int quantity;
        private int additionalValueShareRatio;

        public GoodsInRequestBuilder(String externalId,  String description,  Price price, double weight, int quantity) {
            this.externalId = externalId;
            this.description = description;
            this.price = price;
            this.weight = weight;
            this.quantity = quantity;
        }

        public GoodsInRequestBuilder setGtin(String gtin) {
            this.gtin = gtin;
            return this;
        }

        public GoodsInRequestBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public GoodsInRequestBuilder setHsCode(String hsCode) {
            this.hsCode = hsCode;
            return this;
        }

        public GoodsInRequestBuilder setAdditionalValueShareRatio(int additionalValueShareRatio) {
            this.additionalValueShareRatio = additionalValueShareRatio;
            return this;
        }

        public GoodsInRequest build() {
            return new GoodsInRequest( externalId,  gtin,  title,  description,  hsCode,  price,  weight,  quantity,  additionalValueShareRatio);
        }

    }

}
