package com.qa.rohit.restclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class QueryParams {
    private List<QueryParam> queryParamList;

    public QueryParams(){ this(null); }
    public QueryParams(Integer numOfParams){ queryParamList = new ArrayList<>(numOfParams != null ? numOfParams : 0); }

    public QueryParams append(String key, String value) {
        queryParamList.add(new QueryParam(key, value));
        return this;
    }

    public List<QueryParam> getQueryParamsAsList() { return queryParamList; }

    public int size(){ return queryParamList.size(); }

    public String getQueryParamsAsUrl() {
        if (size() == 0) return "";
        StringBuilder url = new StringBuilder("?" + queryParamList.get(0));
        if (size() > 1)
            for (int i = 1; i < queryParamList.size(); i++)
                url.append("&").append(queryParamList.get(i));
        return url.toString();
    }

}
