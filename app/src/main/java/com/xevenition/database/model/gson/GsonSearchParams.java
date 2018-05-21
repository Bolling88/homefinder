
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonSearchParams {

    @SerializedName("upcomingSale")
    @Expose
    private int upcomingSale;
    @SerializedName("areaId")
    @Expose
    private int areaId;

    public int getUpcomingSale() {
        return upcomingSale;
    }

    public void setUpcomingSale(int upcomingSale) {
        this.upcomingSale = upcomingSale;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

}
