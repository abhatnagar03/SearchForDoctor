package com.vivy.test.searchmydoctor.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorsList implements Serializable {
    @SerializedName("doctors")
    private List<Doctor> doctors;

    public List<Doctor> getDoctorsList() {
        return doctors;
    }
}
