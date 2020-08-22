package com.github.elwyncrestha.ngfilewithspringbackend;

/**
 * @author Elvin Shrestha on 8/22/2020
 */
public class Response {

    private String value;

    public Response(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
