package com.qa.rohit.grapql.pojomodels;

import com.qa.rohit.restclient.Response;

public class GraphQLResponse implements Response {
    private Data data;

    public Data getData() {
        return data;
    }
}
