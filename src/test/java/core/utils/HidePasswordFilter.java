package core.utils;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class HidePasswordFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        if (requestSpec.getBody() != null) {
            String body = requestSpec.getBody().toString();
            if (body.contains("password")) {
                body = body.replaceAll("\"password\":\"[^\"]*\"", "\"password\":\"***\"");
                Allure.addAttachment("Request body (password hidden)", body);
            }
        }
        return ctx.next(requestSpec, responseSpec);
    }
}
