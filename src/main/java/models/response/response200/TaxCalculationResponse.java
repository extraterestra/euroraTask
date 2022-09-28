package models.response.response200;

import java.util.List;

public class TaxCalculationResponse {

    private String externalId;
    private double totalVat;
    private double totalDuties;
    private List<GoodsInResponse> goods;

    public TaxCalculationResponse() {
    }

    public TaxCalculationResponse(String externalId, double totalVat, double totalDuties, List<GoodsInResponse> goods) {
        this.externalId = externalId;
        this.totalVat = totalVat;
        this.totalDuties = totalDuties;
        this.goods = goods;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public double getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(double totalVat) {
        this.totalVat = totalVat;
    }

    public double getTotalDuties() {
        return totalDuties;
    }

    public void setTotalDuties(double totalDuties) {
        this.totalDuties = totalDuties;
    }

    public List<GoodsInResponse> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsInResponse> goods) {
        this.goods = goods;
    }
}
