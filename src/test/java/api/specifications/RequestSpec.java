package api.specifications;

import core.config.ConfigManager;
import core.config.TokenManager;
import core.utils.HidePasswordFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public static RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getConfig().apiUrl())
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new HidePasswordFilter())
                .build();
    }

    public static RequestSpecification authRequest(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getConfig().apiUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer "  + TokenManager.getToken())
                .log(LogDetail.ALL)
                .build();
    }
}
