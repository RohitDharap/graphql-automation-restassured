package com.qa.rohit.restful.login;

import com.qa.rohit.apihelpers.login.LoginApiRequest;
import com.qa.rohit.apihelpers.users.GetUsersApiRequest;
import org.testng.annotations.Test;

public class LoginApiTest {
    private LoginApiRequest.ResponsePojo responsePojo;

    @Test
    public void verifyApiStatusCodeForValidRequest(){
        responsePojo = new LoginApiRequest("string", "string", "string").hitAndGetPojoResponse();
    }
}
