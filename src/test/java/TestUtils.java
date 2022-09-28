import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TestUtils {

    public static RequestSpecification request;

    public static RequestSpecification requestSpecification()  {
            request = new RequestSpecBuilder().setBaseUri("https://api.integration.eurora.com/customs-calculator")
                    .addHeader("X-Auth-Token", "ckCi8JagStC6ruNeSWY2kQCpIl5rfl6T")
                    .setContentType(ContentType.JSON).build();
            return request;
    }

    public static RequestSpecification requestSpecificationInvalid() {
            request = new RequestSpecBuilder().setBaseUri("https://api.integration.eurora.com/customs-calculator")
                    .addHeader("X-Auth-Token", "invalidToken")
                    .setContentType(ContentType.JSON).build();
            return request;
        }

}
