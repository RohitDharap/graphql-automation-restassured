package com.qa.rohit.restclient;

import com.qa.rohit.enums.ApiStatus;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;

public abstract class GetRequest implements Request {
    protected String resource;
    protected QueryParams queryParams;

    public ValidatableResponse hitAndGetValidatableResponse(){
        return givenLogSpec().headers(headers()).get(path()).then().log().all();
    }

    public int getStatusCodeForHappyPath(){
        return ApiStatus._200_OK.getStatusCode();
    }

}
