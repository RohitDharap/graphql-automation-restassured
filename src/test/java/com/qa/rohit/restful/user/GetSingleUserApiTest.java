package com.qa.rohit.restful.user;

import com.qa.rohit.apihelpers.users.GetSingleUserApiRequest;
import com.qa.rohit.apihelpers.users.GetUsersApiRequest;
import com.qa.rohit.restclient.QueryParams;
import com.qa.rohit.restclient.pojomodels.Datum;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetSingleUserApiTest {
    private GetSingleUserApiRequest.ResponsePojo responsePojo;
    private final int TEST_ID = 1;

    @Test
    public void verifyApiStatusCodeForValidRequest(){
        responsePojo = new GetSingleUserApiRequest(TEST_ID).hitAndGetPojoResponse();
    }

    @Test(dependsOnMethods = "verifyApiStatusCodeForValidRequest")
    public void verifySingleUserResponseUsingIdMatches_WithAllUsersData() {
        Datum datum = new GetUsersApiRequest().getDataHavingId(null, 1, TEST_ID);
        Assert.assertEquals(datum.getId(), TEST_ID, "Verify first record with id = 1 is returned when limit = 1 and page = null (defaults to 1)");

        Assert.assertEquals(responsePojo.getData(), datum, "Verify same content is returned when data is fetched using id, and in list of all users");
    }

}
