
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonListing {

    @SerializedName("booliId")
    @Expose
    private int booliId;
    @SerializedName("listPrice")
    @Expose
    private int listPrice;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("source")
    @Expose
    private GsonSource source;
    @SerializedName("location")
    @Expose
    private GsonLocation location;
    @SerializedName("objectType")
    @Expose
    private String objectType;
    @SerializedName("rooms")
    @Expose
    private int rooms;
    @SerializedName("livingArea")
    @Expose
    private int livingArea;
    @SerializedName("additionalArea")
    @Expose
    private int additionalArea;
    @SerializedName("rent")
    @Expose
    private int rent;
    @SerializedName("floor")
    @Expose
    private int floor;
    @SerializedName("constructionYear")
    @Expose
    private int constructionYear;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("plotArea")
    @Expose
    private int plotArea;

    public int getBooliId() {
        return booliId;
    }

    public void setBooliId(int booliId) {
        this.booliId = booliId;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public GsonSource getSource() {
        return source;
    }

    public void setSource(GsonSource source) {
        this.source = source;
    }

    public GsonLocation getLocation() {
        return location;
    }

    public void setLocation(GsonLocation location) {
        this.location = location;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(int livingArea) {
        this.livingArea = livingArea;
    }

    public int getAdditionalArea() {
        return additionalArea;
    }

    public void setAdditionalArea(int additionalArea) {
        this.additionalArea = additionalArea;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(int plotArea) {
        this.plotArea = plotArea;
    }

}
