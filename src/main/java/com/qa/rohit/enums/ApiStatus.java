package com.qa.rohit.enums;

public enum ApiStatus {
    _200_OK(200),
    _201_CREATED(201),
    _400_BAD_REQUEST(400);

    private final int statusCode;

    ApiStatus(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
