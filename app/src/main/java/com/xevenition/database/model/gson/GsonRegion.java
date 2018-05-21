
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonRegion {

    @SerializedName("municipalityName")
    @Expose
    private String municipalityName;
    @SerializedName("countyName")
    @Expose
    private String countyName;

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

}
