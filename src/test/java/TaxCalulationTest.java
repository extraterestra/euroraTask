import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.DutyTaxCalculationRequest;
import models.request.GoodsInRequest;
import models.request.Price;
import models.response.response200.TaxCalculationResponse;
import models.response.response400.BadRequestResponse;
import models.response.response401.UnauthorisedResponse;
import models.response.response404.NotFoundResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TaxCalulationTest extends TestUtils {
    static RequestSpecification calcRequestSpec;
    static RequestSpecification calcRequestSpecInvalid;
    String path;
    Response responseCalc;
    UnauthorisedResponse unauthorisedResponse;
    BadRequestResponse badRequestResponse;
    NotFoundResponse notFoundResponse;
    RequestSpecification reqSpecInTest;

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
//                {true, true, "EUR", 250, 1027.55, null, 200},
//                {true, true, "YYY", 250, 0, "currency not found", 400},
//                {false, true, "EUR", 250, 0, "Unauthorized", 401},
                {true, false, "EUR", 250, 0, "Not Found", 404}  //,
//                {true, true, "EUR", 500, 1392.05, null, 200}
        };
    }

    @BeforeMethod
    public static void createRequestSpecification() {
        calcRequestSpec = given().spec(requestSpecification());
        calcRequestSpecInvalid = given().spec(requestSpecificationInvalid());
    }

    @Test(dataProvider = "test-data")
    public void calculateVat(boolean isRequestValid, boolean isUrlValid, String currency, int quantity, double vatExpected, String expectedMessage, int responseCode) throws IOException {

        Price price = new Price(currency, 11.25);

        List<GoodsInRequest> goodsList = new ArrayList<>();
        GoodsInRequest productFromChina = new GoodsInRequest("123e4567-e89b-12d3-a456-426655440000", "00012345600012",
                "Fidget spinners", "Fidget spinners", "0101000000", price, 0.15, quantity, 1);

        goodsList.add(productFromChina);
        DutyTaxCalculationRequest load = new DutyTaxCalculationRequest("123e4567-e89b-12d3-a456-426655440000", "EUR", 5000.55,
                100, 15.55, "CN", "CA", "CA-MB", "MANUAL", goodsList,
                "2022-09-27", true, true);

        List<DutyTaxCalculationRequest> loadList = new ArrayList<>();
        loadList.add(load);

        ObjectMapper objMap = new ObjectMapper();
        String payload = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(loadList);

        if (isRequestValid) {
            reqSpecInTest = calcRequestSpec;
        } else {
            reqSpecInTest = calcRequestSpecInvalid;
        }
        if (isUrlValid) {
            path = "/v1/shopping-cart";
        } else {
            path = "/v1/shopping-cartX";
        }

        responseCalc = given().spec(reqSpecInTest).body(payload)
                .when().log().all().post(path);

//        System.out.println(responseCalc.getBody().jsonPath().prettify());

        int statusCode = responseCalc.getStatusCode();

        switch (statusCode) {
            case 200:
                System.out.println("OK");
                responseCalc.then().statusCode(responseCode);
                String response = responseCalc.getBody().jsonPath().prettify();
                Gson gson = new Gson();
                Type taxCalcListType = new TypeToken<ArrayList<TaxCalculationResponse>>() {
                }.getType();
                ArrayList<TaxCalculationResponse> taxCalcArray = gson.fromJson(response, taxCalcListType);
                for (TaxCalculationResponse taxCalculationResponse : taxCalcArray) {
                    System.out.println("TOTAL VAT: " + taxCalculationResponse.getTotalVat());
                    Assertions.assertThat(taxCalculationResponse.getTotalVat()).isEqualTo(vatExpected);
                }
                break;
            case 400:
                System.out.println("Bad Request");
                responseCalc.then().statusCode(responseCode);
                badRequestResponse = responseCalc.getBody().as(BadRequestResponse.class);
                Assertions.assertThat(badRequestResponse.getRows().get(0).getMessage()).isEqualTo(expectedMessage);
                break;
            case 401:
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
            case 5:
                System.out.println("Internal Server Error");
                break;
        }
    }
}
