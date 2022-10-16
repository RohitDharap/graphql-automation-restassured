package com.qa.rohit.restful.user;

import com.qa.rohit.restclient.QueryParams;
import com.qa.rohit.restclient.pojomodels.Datum;
import com.qa.rohit.apihelpers.users.GetUsersApiRequest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetUsersApiTest {
    private GetUsersApiRequest.ResponsePojo responsePojo;

    @Test
    public void verifyApiStatusCodeForValidRequest(){
        responsePojo = new GetUsersApiRequest().hitAndGetPojoResponse();
    }

    @Test(dependsOnMethods = "verifyApiStatusCodeForValidRequest")
    public void verifyResponseBodyHasNoMandatoryParamMissing(){
        Assert.assertNotNull(responsePojo.getPage(), "verify page param is not null");
        Assert.assertNotNull(responsePojo.getPer_page(), "verify per_page param is not null");
        Assert.assertNotNull(responsePojo.getTotal(), "verify total param is not null");
        Assert.assertNotNull(responsePojo.getTotal_pages(), "verify total_pages param is not null");
        Assert.assertNotNull(responsePojo.getData(), "verify data param is not null");
        Assert.assertNotNull(responsePojo.getSupport(), "verify support param is not null");
        Assert.assertNotNull(responsePojo.getSupport().getText(), "verify support.text param is not null");
        Assert.assertNotNull(responsePojo.getSupport().getUrl(), "verify support.url param is not null");
    }

    @Test(dependsOnMethods = {"verifyApiStatusCodeForValidRequest", "verifyResponseBodyHasNoMandatoryParamMissing"})
    public void verifyDataInRootOfResponseBody_MatchesExpectation(){
        Assert.assertEquals(responsePojo.getPage(), 1, "verify page 1");
        Assert.assertEquals(responsePojo.getPer_page(), GetUsersApiRequest.DEFAULT_PER_PAGE_USER_COUNT, "verify default per page user count");
        Assert.assertEquals(responsePojo.getTotal_pages(), responsePojo.getTotal() / GetUsersApiRequest.DEFAULT_PER_PAGE_USER_COUNT,
                "verify default per page user count");
        Assert.assertEquals(responsePojo.getData().size(), Math.min(responsePojo.getTotal(), responsePojo.getPer_page()), "verify count of data");
        List<Datum> sortedData = new ArrayList<>(responsePojo.getData());
        Collections.sort(sortedData);
        Assert.assertEquals(responsePojo.getData(), sortedData, "verify data is sorted");
        Assert.assertEquals(responsePojo.getData().get(0).getId(), 1, "verify id of first record is 1");
        sortedData.sort(Collections.reverseOrder());
        Assert.assertEquals(sortedData.get(0).getId(), GetUsersApiRequest.DEFAULT_PER_PAGE_USER_COUNT, "verify id of last record on page 1 is as per default " +
                "per page limit");
    }

    @DataProvider
    private Object[][] queryParamsHappyPath(){
        return new Object[][]{
                {null, 4, "Verify response for null page (should default to 1) and custom per page limit"},
                {2, null, "Verify response for custom page and null per page limit (should have some predefined default value) "},
                {2, 4, "Verify response for custom page and per page limit"}
        };
    }
    @Test(dependsOnMethods = {"verifyApiStatusCodeForValidRequest", "verifyResponseBodyHasNoMandatoryParamMissing"}, dataProvider = "queryParamsHappyPath")
    public void verifyResponseBodyWithDifferentCombinationOfQueryParams(Integer page, Integer perPageLimit, String scenario){
        int expectedPage = page == null ? 1 : page;
        int expectedPerPageLimit = perPageLimit == null ? GetUsersApiRequest.DEFAULT_PER_PAGE_USER_COUNT : perPageLimit;

        QueryParams queryParams = new QueryParams().append("per_page", String.valueOf(perPageLimit)).append("page", String.valueOf(page));
        GetUsersApiRequest.ResponsePojo responsePojo = new GetUsersApiRequest(queryParams).hitAndGetPojoResponse();

        System.out.println("Scenario : " + scenario + "\n");

        Assert.assertEquals(responsePojo.getPage(), expectedPage, "verify page");
        Assert.assertEquals(responsePojo.getPer_page(), expectedPerPageLimit, "verify per page limit");
        Assert.assertEquals(responsePojo.getTotal_pages(), responsePojo.getTotal() / expectedPerPageLimit,
                "verify total pages is a function of total users and per page page limit");
        Assert.assertEquals(responsePojo.getData().size(), Math.min(responsePojo.getTotal(), responsePojo.getPer_page()), "verify count of data");

        List<Datum> sortedData = new ArrayList<>(responsePojo.getData());
        Collections.sort(sortedData);
        Assert.assertEquals(responsePojo.getData(), sortedData, "verify data is sorted");
        Assert.assertEquals(responsePojo.getData().get(0).getId(), (expectedPage - 1) * expectedPerPageLimit + 1, "verify id of first record is 1");
        sortedData.sort(Collections.reverseOrder());
        Assert.assertEquals(sortedData.get(0).getId(), expectedPage * expectedPerPageLimit, "verify id of last record on page 1 is as per default " +
                "per page limit");
    }

}
