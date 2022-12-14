import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.request.DutyTaxCalculationRequest;
import models.request.DutyTaxCalculationRequest.DutyTaxCalculationRequestBuilder;
import models.request.GoodsInRequest;
import models.request.GoodsInRequest.GoodsInRequestBuilder;
import models.request.Price;
import models.response.response200.TaxCalculationResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static RequestSpecification request;
    String token;
    String path;

    public RequestSpecification getRequestSpecification(boolean isRequestValid) {
        if (isRequestValid) {
            token = "ckCi8JagStC6ruNeSWY2kQCpIl5rfl6T";
        } else {
            token = "invalidToken";
        }

        request = new RequestSpecBuilder().setBaseUri("https://api.integration.eurora.com/customs-calculator")
                .addHeader("X-Auth-Token", token)
                .setContentType(ContentType.JSON).build();
        return request;
    }

    public String setPayload(String currency, int quantity, String destinationCountry) throws JsonProcessingException {

        Price price = new Price(currency, 11.25);

        List<GoodsInRequest> goodsList = new ArrayList<>();

        GoodsInRequest productFromChina = new GoodsInRequestBuilder("123e4567-e89b-12d3-a456-426655440000",
                "Fidget spinners", price, 0.15, quantity)
                .setGtin("00012345600012")
                .setTitle("Fidget spinners")
                .setHsCode("0101000000")
                .setAdditionalValueShareRatio(1)
                .build();

        goodsList.add(productFromChina);

        DutyTaxCalculationRequest load = new DutyTaxCalculationRequestBuilder("123e4567-e89b-12d3-a456-426655440000",
                "EUR", 5000.55, "CN", destinationCountry, goodsList)
                .setInsurancePrice(100)
                .setOtherAdditionalCosts(15.55)
                .setDestinationRegion("CA-MB")
                .setAdditionalValueShare("MANUAL")
                .setDate("2022-09-27")
                .setUseDeMinimis(true)
                .setHsCodeReplaceAlloweds(true)
                .build();

        List<DutyTaxCalculationRequest> loadList = new ArrayList<>();
        loadList.add(load);

        ObjectMapper objMap = new ObjectMapper();
        String payload = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(loadList);

        return payload;
    }

    public String getPath(boolean isUrlValid) {
        if (isUrlValid) {
            path = "/v1/shopping-cart";
        } else {
            path = "/v1/shopping-cartX";
        }
        return path;
    }

    public List<TaxCalculationResponse> getCart(Response responseCalc) {
        String response = responseCalc.getBody().jsonPath().prettify();
        Gson gson = new Gson();
        Type taxCalcListType = new TypeToken<ArrayList<TaxCalculationResponse>>() {
        }.getType();
        List<TaxCalculationResponse> taxCalcArray = gson.fromJson(response, taxCalcListType);
        return taxCalcArray;
    }

}
