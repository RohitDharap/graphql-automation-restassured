package com.qa.rohit.apihelpers.users;

import com.qa.rohit.restclient.Cookie;
import com.qa.rohit.restclient.GetRequest;
import com.qa.rohit.restclient.QueryParams;
import com.qa.rohit.restclient.Response;
import com.qa.rohit.enums.ApiBase;
import com.qa.rohit.restclient.pojomodels.Datum;
import com.qa.rohit.restclient.pojomodels.Support;

public class GetSingleUserApiRequest extends GetRequest {
    private static final String resource = "/users/%s";

    public GetSingleUserApiRequest(Integer id) {
        super.resource = String.format(resource, id);
    }

    @Override
    public String base() {
        return ApiBase.REQ_RES_SERVER.getHostName();
    }

    @Override
    public String resource() {
        return super.resource;
    }

    @Override
    public QueryParams queryParams() {
        return null;
    }

    @Override
    public Cookie cookie() {
        return null;
    }

    @Override
    public <T extends Response> Class<T> getResponsePojoClass() {
        return (Class<T>) Response.class;
    }

    /*========================================Response Body Pojo Below========================================*/

    public static class ResponsePojo implements Response {
        private Datum data;
        private Support support;

        public Datum getData() {
            return data;
        }

        public Support getSupport() {
            return support;
        }
    }

}
