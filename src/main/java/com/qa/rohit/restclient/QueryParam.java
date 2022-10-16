package com.qa.rohit.restclient;

public final class QueryParam {
    private String key, value;

    QueryParam(String key, String value){
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

}
