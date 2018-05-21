
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonDistance {

    @SerializedName("ocean")
    @Expose
    private int ocean;

    public int getOcean() {
        return ocean;
    }

    public void setOcean(int ocean) {
        this.ocean = ocean;
    }

}
