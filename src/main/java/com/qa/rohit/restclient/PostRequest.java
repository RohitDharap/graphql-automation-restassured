package com.qa.rohit.restclient;

import com.qa.rohit.enums.ApiStatus;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

public abstract class PostRequest implements Request, RequestBody {
    protected String resource;
    protected QueryParams queryParams;

    public ValidatableResponse hitAndGetValidatableResponse(){
        return givenLogSpec().headers(headers()).body(body()).log().body().post(path()).then().log().all();
    }

    public int getStatusCodeForHappyPath(){
        return ApiStatus._201_CREATED.getStatusCode();
    }

}
