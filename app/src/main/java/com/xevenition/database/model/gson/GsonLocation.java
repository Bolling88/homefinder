
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonLocation {

    @SerializedName("namedAreas")
    @Expose
    private List<String> namedAreas = null;
    @SerializedName("address")
    @Expose
    private GsonAddress address;
    @SerializedName("region")
    @Expose
    private GsonRegion region;
    @SerializedName("position")
    @Expose
    private GsonPosition position;

    public List<String> getNamedAreas() {
        return namedAreas;
    }

    public void setNamedAreas(List<String> namedAreas) {
        this.namedAreas = namedAreas;
    }

    public GsonAddress getAddress() {
        return address;
    }

    public void setAddress(GsonAddress address) {
        this.address = address;
    }

    public GsonRegion getRegion() {
        return region;
    }

    public void setRegion(GsonRegion region) {
        this.region = region;
    }

    public GsonPosition getPosition() {
        return position;
    }

    public void setPosition(GsonPosition position) {
        this.position = position;
    }

}
