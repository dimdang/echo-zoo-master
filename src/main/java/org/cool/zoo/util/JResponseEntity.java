package org.cool.zoo.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class JResponseEntity<T> {

    @JsonProperty("resp_message")
    private String message;
    @JsonProperty("resp_code")
    private int code;
    @JsonProperty("resp_data")
    private List<T> data = new ArrayList<T>();

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public void addBody(T body) {
        if (body != null) {
            if (body instanceof List)
                data.addAll((Collection<? extends T>) body);
            else
                data.add(body);
        }
    }

}
