
package com.xevenition.database.model.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonSoldInfo {

    @SerializedName("totalCount")
    @Expose
    private int totalCount;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("sold")
    @Expose
    private List<GsonSold> sold = null;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("searchParams")
    @Expose
    private GsonSearchParams searchParams;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GsonSold> getSold() {
        return sold;
    }

    public void setSold(List<GsonSold> sold) {
        this.sold = sold;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public GsonSearchParams getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(GsonSearchParams searchParams) {
        this.searchParams = searchParams;
    }

}
