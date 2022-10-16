package com.qa.rohit.restclient;

public interface RequestBody {

    /**
     * All requests requiring a body are expected to implement this. The pojo of the sub-class will be serialized into json string
     * @return Request Body
     */
    RequestBody body();

}
