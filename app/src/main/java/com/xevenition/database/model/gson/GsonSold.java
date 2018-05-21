
package com.xevenition.database.model.gson;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GsonSold {

    @SerializedName("location")
    @Expose
    private GsonLocation location;
    @SerializedName("listPrice")
    @Expose
    private int listPrice;
    @SerializedName("rent")
    @Expose
    private int rent;
    @SerializedName("floor")
    @Expose
    private int floor;
    @SerializedName("livingArea")
    @Expose
    private int livingArea;
    @SerializedName("source")
    @Expose
    private GsonSource source;
    @SerializedName("rooms")
    @Expose
    private int rooms;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("constructionYear")
    @Expose
    private int constructionYear;
    @SerializedName("objectType")
    @Expose
    private String objectType;
    @SerializedName("booliId")
    @Expose
    @PrimaryKey
    private int booliId;
    @SerializedName("soldDate")
    @Expose
    private String soldDate;
    @SerializedName("soldPrice")
    @Expose
    private int soldPrice;
    @SerializedName("apartmentNumber")
    @Expose
    private String apartmentNumber;
    @SerializedName("soldPriceSource")
    @Expose
    private String soldPriceSource;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("plotArea")
    @Expose
    private int plotArea;
    @SerializedName("additionalArea")
    @Expose
    private int additionalArea;

    public GsonLocation getLocation() {
        return location;
    }

    public void setLocation(GsonLocation location) {
        this.location = location;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
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

    public int getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(int livingArea) {
        this.livingArea = livingArea;
    }

    public GsonSource getSource() {
        return source;
    }

    public void setSource(GsonSource source) {
        this.source = source;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getBooliId() {
        return booliId;
    }

    public void setBooliId(int booliId) {
        this.booliId = booliId;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public int getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(int soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getSoldPriceSource() {
        return soldPriceSource;
    }

    public void setSoldPriceSource(String soldPriceSource) {
        this.soldPriceSource = soldPriceSource;
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

    public int getAdditionalArea() {
        return additionalArea;
    }

    public void setAdditionalArea(int additionalArea) {
        this.additionalArea = additionalArea;
    }

}
