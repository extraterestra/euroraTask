package models.request;

import java.util.List;

public class DutyTaxCalculationRequest {
    private final String externalId;
    private final String orderCurrency;
    private final double transportationPrice;
    private double insurancePrice;
    private double otherAdditionalCosts;
    private final String originCountry;
    private final String destinationCountry;
    private String destinationRegion;
    private String additionalValueShare;
    private final List<GoodsInRequest> goods;
    private String date;
    private boolean useDeMinimis;
    private boolean hsCodeReplaceAllowed;

    public DutyTaxCalculationRequest(String externalId, String orderCurrency, double transportationPrice, String originCountry, String destinationCountry, List<GoodsInRequest> goods) {
        this.externalId = externalId;
        this.orderCurrency = orderCurrency;
        this.transportationPrice = transportationPrice;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.goods = goods;
    }

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

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public double getTransportationPrice() {
        return transportationPrice;
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

    public String getDestinationCountry() {
        return destinationCountry;
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

    public static class DutyTaxCalculationRequestBuilder {
        private final String externalId;
        private final String orderCurrency;
        private final double transportationPrice;
        private double insurancePrice;
        private double otherAdditionalCosts;
        private final String originCountry;
        private final String destinationCountry;
        private String destinationRegion;
        private String additionalValueShare;
        private final List<GoodsInRequest> goods;
        private String date;
        private boolean useDeMinimis;
        private boolean hsCodeReplaceAllowed;


        public DutyTaxCalculationRequestBuilder(String externalId, String orderCurrency, double transportationPrice, String originCountry, String destinationCountry, List<GoodsInRequest> goods) {
            this.externalId = externalId;
            this.orderCurrency = orderCurrency;
            this.transportationPrice = transportationPrice;
            this.originCountry = originCountry;
            this.destinationCountry = destinationCountry;
            this.goods = goods;
        }

        public DutyTaxCalculationRequestBuilder setInsurancePrice(double insurancePrice) {
            this.insurancePrice = insurancePrice;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setOtherAdditionalCosts(double otherAdditionalCosts) {
            this.otherAdditionalCosts = otherAdditionalCosts;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setDestinationRegion(String destinationRegion) {
            this.destinationRegion = destinationRegion;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setAdditionalValueShare(String additionalValueShare) {
            this.additionalValueShare = additionalValueShare;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setUseDeMinimis(boolean useDeMinimis) {
            this.useDeMinimis = useDeMinimis;
            return this;
        }

        public DutyTaxCalculationRequestBuilder setHsCodeReplaceAlloweds(boolean hsCodeReplaceAllowed) {
            this.hsCodeReplaceAllowed = hsCodeReplaceAllowed;
            return this;
        }

        public DutyTaxCalculationRequest build() {
            return new DutyTaxCalculationRequest(externalId, orderCurrency, transportationPrice, insurancePrice, otherAdditionalCosts, originCountry, destinationCountry, destinationRegion, additionalValueShare, goods, date, useDeMinimis, hsCodeReplaceAllowed);
        }
    }

}
