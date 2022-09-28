package models.response.response200;

import java.util.List;

public class GoodsInResponse {
    private String externalId;
    private String description;
    private double vat;
    private double duty;
    private double vatRate;
    private double dutyRate;
    private double hsCodeCorrectness;
    private String calculatedHsCode;
    private List<String> calculatedHsCodeRestrictions;
    private List<String> providedHsCodeRestrictions;

    public GoodsInResponse() {
    }

    public GoodsInResponse(String externalId, String description, double vat, double duty, double vatRate, double dutyRate, double hsCodeCorrectness, String calculatedHsCode, List<String> calculatedHsCodeRestrictions, List<String> providedHsCodeRestrictions) {
        this.externalId = externalId;
        this.description = description;
        this.vat = vat;
        this.duty = duty;
        this.vatRate = vatRate;
        this.dutyRate = dutyRate;
        this.hsCodeCorrectness = hsCodeCorrectness;
        this.calculatedHsCode = calculatedHsCode;
        this.calculatedHsCodeRestrictions = calculatedHsCodeRestrictions;
        this.providedHsCodeRestrictions = providedHsCodeRestrictions;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getDuty() {
        return duty;
    }

    public void setDuty(double duty) {
        this.duty = duty;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    public double getDutyRate() {
        return dutyRate;
    }

    public void setDutyRate(double dutyRate) {
        this.dutyRate = dutyRate;
    }

    public double getHsCodeCorrectness() {
        return hsCodeCorrectness;
    }

    public void setHsCodeCorrectness(double hsCodeCorrectness) {
        this.hsCodeCorrectness = hsCodeCorrectness;
    }

    public String getCalculatedHsCode() {
        return calculatedHsCode;
    }

    public void setCalculatedHsCode(String calculatedHsCode) {
        this.calculatedHsCode = calculatedHsCode;
    }

    public List<String> getCalculatedHsCodeRestrictions() {
        return calculatedHsCodeRestrictions;
    }

    public void setCalculatedHsCodeRestrictions(List<String> calculatedHsCodeRestrictions) {
        this.calculatedHsCodeRestrictions = calculatedHsCodeRestrictions;
    }

    public List<String> getProvidedHsCodeRestrictions() {
        return providedHsCodeRestrictions;
    }

    public void setProvidedHsCodeRestrictions(List<String> providedHsCodeRestrictions) {
        this.providedHsCodeRestrictions = providedHsCodeRestrictions;
    }
}
