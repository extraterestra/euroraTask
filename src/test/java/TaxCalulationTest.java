import io.restassured.response.Response;
import models.response.response200.TaxCalculationResponse;
import models.response.response400.BadRequestResponse;
import models.response.response401.UnauthorisedResponse;
import models.response.response404.NotFoundResponse;
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

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {true, true, "EUR", 250, 1027.55, null, 200},
                {true, true, "YYY", 250, 0, "currency not found", 400},
                {false, true, "EUR", 250, 0, "Unauthorized", 401},
                {true, false, "EUR", 250, 0, "Not Found", 404},
                {true, true, "EUR", 500, 1392.05, null, 200}
        };
    }

    @Test(dataProvider = "test-data")
    public void calculateVat(boolean isRequestValid, boolean isUrlValid, String currency, int quantity, double vatExpected,
                             String expectedMessage, int responseCode) throws IOException {

        String payload = setPayload(currency, quantity);

        responseCalc = given().spec(getRequestSpecification(isRequestValid)).body(payload)
                .when().log().all().post(getPath(isUrlValid));

        int statusCode = responseCalc.getStatusCode();

        switch (statusCode) {
            case 200:
                System.out.println("OK");
                responseCalc.then().statusCode(responseCode);
                List<TaxCalculationResponse> taxCalcArray = getCart(responseCalc);
                for (TaxCalculationResponse taxCalculationResponse : taxCalcArray) {
                    System.out.println("TOTAL VAT: " + taxCalculationResponse.getTotalVat());
                    Assertions.assertThat(taxCalculationResponse.getTotalVat()).isEqualTo(vatExpected);
                }
                break;
            case 400:
                System.out.println("Unauthorised");
                responseCalc.then().statusCode(responseCode);
                badRequestResponse = responseCalc.getBody().as(BadRequestResponse.class);
                Assertions.assertThat(badRequestResponse.getRows().get(0).getMessage()).isEqualTo(expectedMessage);
                break;
            case 401:
                System.out.println("Bad Request");
                responseCalc.then().statusCode(responseCode);
                unauthorisedResponse = responseCalc.getBody().as(UnauthorisedResponse.class);
                Assertions.assertThat(unauthorisedResponse.getMessage()).isEqualTo(expectedMessage);
                break;
            case 403:
                System.out.println("Forbidden");
                break;
            case 404:
                System.out.println("Not Found");
                responseCalc.then().statusCode(responseCode);
                notFoundResponse = responseCalc.getBody().as(NotFoundResponse.class);
                Assertions.assertThat(notFoundResponse.getError()).isEqualTo(expectedMessage);
                break;
            case 500:
                System.out.println("Internal Server Error");
                break;
        }
    }
}
