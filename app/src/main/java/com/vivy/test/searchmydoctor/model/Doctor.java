package com.vivy.test.searchmydoctor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doctor implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("photoId")
    private String photo;
    @SerializedName("address")
    private String address;

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getAddress() {
        return address;
    }
}
