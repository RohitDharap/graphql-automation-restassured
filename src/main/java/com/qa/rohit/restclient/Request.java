package com.qa.rohit.restclient;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public interface Request {

    /**
     * @return complete path of the request, i.e., host/base + resource + query
     */
    default String path() {
        return base() + resource() + (queryParams() == null ? "" : queryParams().getQueryParamsAsUrl());
    }

    /**
     * @return only base/host
     */
    String base();

    /**
     * @return only api resource
     */
    String resource();

    /**
     * @return only query params from request
     */
    QueryParams queryParams();

    /**
     * @return cookie
     */
    Cookie cookie();

    /**
     * @return ValidatableResponse for given request
     */
    ValidatableResponse hitAndGetValidatableResponse();

    /**
     * @return Pojo response for given request. Each request sub-class is expected to override this method and provide
     * pojo class for its response
     * <br/>e.g., <br/>
     * public &lt;T extends Response> Class&lt;T> getResponsePojoClass() {<br/>
     *         return (Class&lt;T>) ResponsePojo.class;<br/>
     *     }<br/>
     *     where ResponsePojo is a class having pojo representation of sample response of given request
     */
    <T extends Response> Class<T> getResponsePojoClass();

    /**
     *
     * @return status code for successful request of given type (typically 200 for Get, 201 for Post, 204 for Put/Delete)
     */
    int getStatusCodeForHappyPath();

    /**
     *
     * @return self instance. Syntactic sugar for BDD kind of code writing.
     */
    default Request and(){
        return this;
    }

    /**
     *
     * @return Response or a subclass, which represents the pojo of actual response.
     * Every API having response body is expected to override this method to return non-null data.
     * @param <T> subclass of Response
     */
    default <T extends Response> T hitAndGetPojoResponse() {
        return hitAndGetValidatableResponse().assertThat().statusCode(getStatusCodeForHappyPath()).extract().as(getResponsePojoClass());
    }

    /**
     *
     * @return default log specification to log request. Subclasses should override this as and when needed
     */
    default RequestSpecification givenLogSpec(){
        RequestSpecification requestSpecification = RestAssured.given().log().method().log().uri().log().headers();
        if (cookie() != null) requestSpecification.log().cookies();
        if (queryParams() != null) requestSpecification.log().params();
        return requestSpecification;
    }

    /**
     *
     * @return mandatory headers. subclasses (individual API classes) should override this as and when needed as per header
     * requirements.
     */
    default Headers headers(){
        return new Headers(new Header("content-type", "application/json"));
    }

}
