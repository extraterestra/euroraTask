import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.response.response200.TaxCalculationResponse;
import models.response.response400.BadRequestResponse;
import models.response.response401.UnauthorisedResponse;
import models.response.response404.NotFoundResponse;
import models.response.response500.InternalServerErrorResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TaxCalculationSeparateTests extends TestUtils {
    Response responseCalc;
    UnauthorisedResponse unauthorisedResponse;
    BadRequestResponse badRequestResponse;
    NotFoundResponse notFoundResponse;
    InternalServerErrorResponse internalServerErrorResponse;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {true, "EUR", 250, "CA", 1027.55, 634.29}
                , {true, "EUR", 500, "CA", 1392.05, 859.29}
        };
    }

    @Test(dataProvider = "test-data")
    public void calculate_vat_positive(boolean isRequestValid, String currency, int quantity, String destinationCountry, double vatExpected,
                                       double dutyExpected) throws IOException {

        String payload = setPayload(currency, quantity, destinationCountry);
        responseCalc = given().spec(getRequestSpecification(isRequestValid)).body(payload)
                .when().log().all().post("/v1/shopping-cart");

        responseCalc.then().assertThat().contentType(ContentType.JSON);

        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(200);

        List<TaxCalculationResponse> taxCalcArray = getCart(responseCalc);
        for (TaxCalculationResponse taxCalculationResponse : taxCalcArray) {
            System.out.println("TOTAL VAT: " + taxCalculationResponse.getTotalVat());
            Assertions.assertThat(taxCalculationResponse.getTotalVat()).isEqualTo(vatExpected);
            Assertions.assertThat(taxCalculationResponse.getTotalDuties()).isEqualTo(dutyExpected);
        }
    }

    @Test
    public void calculate_vat_error_code_400() throws IOException {
        String payload = setPayload("YYY", 250, "CA");
        responseCalc = given().spec(getRequestSpecification(true)).body(payload)
                .when().log().all().post(getPath(true));
        responseCalc.then().assertThat().contentType(ContentType.JSON);
        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(400);
        badRequestResponse = responseCalc.getBody().as(BadRequestResponse.class);
        Assertions.assertThat(badRequestResponse.getRows().get(0).getMessage()).isEqualTo("currency not found");

    }

    @Test
    public void calculate_vat_error_code_401() throws IOException {
        String payload = setPayload("EUR", 250, "CA");
        responseCalc = given().spec(getRequestSpecification(false)).body(payload)
                .when().log().all().post(getPath(true));
        responseCalc.then().assertThat().contentType(ContentType.JSON);
        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(401);
        unauthorisedResponse = responseCalc.getBody().as(UnauthorisedResponse.class);
        Assertions.assertThat(unauthorisedResponse.getMessage()).isEqualTo("Unauthorized");
    }

    @Test
    public void calculate_vat_error_code_404() throws IOException {
        String payload = setPayload("EUR", 250, "CA");
        responseCalc = given().spec(getRequestSpecification(true)).body(payload)
                .when().log().all().post(getPath(false));
        responseCalc.then().assertThat().contentType(ContentType.JSON);
        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(404);
        notFoundResponse = responseCalc.getBody().as(NotFoundResponse.class);
        Assertions.assertThat(notFoundResponse.getError()).isEqualTo("Not Found");
    }

    @Test
    public void calculate_vat_error_code_500() throws IOException {
        String payload = setPayload("EUR", 250, "CA-MB");
        responseCalc = given().spec(getRequestSpecification(true)).body(payload)
                .when().log().all().post(getPath(true));
        responseCalc.then().assertThat().contentType(ContentType.JSON);
        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(500);
        internalServerErrorResponse = responseCalc.getBody().as(InternalServerErrorResponse.class);
        Assertions.assertThat(internalServerErrorResponse.getId()).isNotEmpty();
    }

}

