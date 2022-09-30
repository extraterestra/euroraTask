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

public class TaxCalulationTest extends TestUtils {
    Response responseCalc;
    UnauthorisedResponse unauthorisedResponse;
    BadRequestResponse badRequestResponse;
    NotFoundResponse notFoundResponse;
    InternalServerErrorResponse internalServerErrorResponse;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {true, true, "EUR", 250, "CA", 1027.55, 634.29, null, 200}
                ,{true, true, "YYY", 250, "CA", 0, 0, "currency not found", 400}
                ,{false, true, "EUR", 250, "CA", 0, 0, "Unauthorized", 401}
                ,{true, false, "EUR", 250, "CA", 0, 0, "Not Found", 404}
                ,{true, true, "EUR", 250, "CA-MB", 0, 0, null, 500}
                ,{true, true, "EUR", 500, "CA", 1392.05, 859.29, null, 200}
        };
    }

    @Test(dataProvider = "test-data")
    public void calculateVat(boolean isRequestValid, boolean isUrlValid, String currency, int quantity, String destinationCountry, double vatExpected,
                             double dutyExpected, String expectedMessage, int expectedResponseCode) throws IOException {

        String payload = setPayload(currency, quantity, destinationCountry);

        responseCalc = given().spec(getRequestSpecification(isRequestValid)).body(payload)
                .when().log().all().post(getPath(isUrlValid));

        System.out.println(responseCalc.getBody().jsonPath().prettify());
        int statusCode = responseCalc.getStatusCode();
        Assertions.assertThat(responseCalc.getStatusCode()).isEqualTo(expectedResponseCode);
        switch (statusCode) {
            case 200:
                System.out.println("OK 200 Test:");
                List<TaxCalculationResponse> taxCalcArray = getCart(responseCalc);
                for (TaxCalculationResponse taxCalculationResponse : taxCalcArray) {
                    System.out.println("TOTAL VAT: " + taxCalculationResponse.getTotalVat());
                    Assertions.assertThat(taxCalculationResponse.getTotalVat()).isEqualTo(vatExpected);
                    Assertions.assertThat(taxCalculationResponse.getTotalDuties()).isEqualTo(dutyExpected);
                }
                break;
            case 400:
                System.out.println("Unauthorised 400 Test:");
                badRequestResponse = responseCalc.getBody().as(BadRequestResponse.class);
                Assertions.assertThat(badRequestResponse.getRows().get(0).getMessage()).isEqualTo(expectedMessage);
                break;
            case 401:
                System.out.println("Bad Request 401 Test:");
                unauthorisedResponse = responseCalc.getBody().as(UnauthorisedResponse.class);
                Assertions.assertThat(unauthorisedResponse.getMessage()).isEqualTo(expectedMessage);
                break;
            case 403:
                System.out.println("Forbidden");
                break;
            case 404:
                System.out.println("Not Found 404 Test:");
                notFoundResponse = responseCalc.getBody().as(NotFoundResponse.class);
                Assertions.assertThat(notFoundResponse.getError()).isEqualTo(expectedMessage);
                break;
            case 500:
                System.out.println("Internal Server Error 500 Test:");
                internalServerErrorResponse = responseCalc.getBody().as(InternalServerErrorResponse.class);
                Assertions.assertThat(internalServerErrorResponse.getId()).isNotEmpty();
                break;
        }
    }
}
