package models.request;

import java.util.List;

public class DutyTaxCalculationRequest {
    private String externalId;
    private String orderCurrency;
    private double transportationPrice;
    private double insurancePrice;
    private double otherAdditionalCosts;
    private String originCountry;
    private String destinationCountry;
    private String destinationRegion;
    private String additionalValueShare;
    private List<GoodsInRequest> goods;
    private String date;
    private boolean useDeMinimis;
    private boolean hsCodeReplaceAllowed;

    public DutyTaxCalculationRequest(String externalId, String orderCurrency, double transportationPrice, double insurancePrice, double otherAdditionalCosts, String originCountry, String destinationCountry, String destinationRegion, String additionalValueShare, List<GoodsInRequest> goods, String date, boolean useDeMinimis, boolean hsCodeReplaceAllowed) {
        this.externalId = externalId;
        this.orderCurrency = orderCurrency;
        this.transportationPrice = transportationPrice;
        this.insurancePrice = insurancePrice;
        this.otherAdditionalCosts = otherAdditionalCosts;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.destinationRegion = destinationRegion;
        this.additionalValueShare = additionalValueShare;
        this.goods = goods;
        this.date = date;
        this.useDeMinimis = useDeMinimis;
        this.hsCodeReplaceAllowed = hsCodeReplaceAllowed;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public double getTransportationPrice() {
        return transportationPrice;
    }

    public void setTransportationPrice(double transportationPrice) {
        this.transportationPrice = transportationPrice;
    }

    public double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public double getOtherAdditionalCosts() {
        return otherAdditionalCosts;
    }

    public void setOtherAdditionalCosts(double otherAdditionalCosts) {
        this.otherAdditionalCosts = otherAdditionalCosts;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDestinationRegion() {
        return destinationRegion;
    }

    public void setDestinationRegion(String destinationRegion) {
        this.destinationRegion = destinationRegion;
    }

    public String getAdditionalValueShare() {
        return additionalValueShare;
    }

    public void setAdditionalValueShare(String additionalValueShare) {
        this.additionalValueShare = additionalValueShare;
    }

    public List<GoodsInRequest> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInRequest> goods) {
        this.goods = goods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isUseDeMinimis() {
        return useDeMinimis;
    }

    public void setUseDeMinimis(boolean useDeMinimis) {
        this.useDeMinimis = useDeMinimis;
    }

    public boolean isHsCodeReplaceAllowed() {
        return hsCodeReplaceAllowed;
    }

    public void setHsCodeReplaceAllowed(boolean hsCodeReplaceAllowed) {
        this.hsCodeReplaceAllowed = hsCodeReplaceAllowed;
    }
}
