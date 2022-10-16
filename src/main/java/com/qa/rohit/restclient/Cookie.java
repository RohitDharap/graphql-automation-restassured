package com.qa.rohit.restclient;

public final class Cookie {
    private String name, value;

    public Cookie(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
