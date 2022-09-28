package models.response.response200;

import java.util.List;

public class CartTaxCalcResponse {

    private List<TaxCalculationResponse> cart;

    public CartTaxCalcResponse() {
    }

    public CartTaxCalcResponse(List<TaxCalculationResponse> cart) {
        this.cart = cart;
    }

    public List<TaxCalculationResponse> getCart() {
        return cart;
    }

    public void setCart(List<TaxCalculationResponse> cart) {
        this.cart = cart;
    }
}
