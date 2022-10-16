package com.qa.rohit.restclient;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.regex.Matcher;

public interface Response {

    static void verifySchema(ValidatableResponse response){

    }
}
