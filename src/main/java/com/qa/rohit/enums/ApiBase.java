package com.qa.rohit.enums;

public enum ApiBase {
    REQ_RES_SERVER("https://reqres.in/api"),
    RICK_MORTY_SERVER("https://rickandmortyapi.graphcdn.app");
    private final String hostName;
    ApiBase(String hostName){
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }
}
