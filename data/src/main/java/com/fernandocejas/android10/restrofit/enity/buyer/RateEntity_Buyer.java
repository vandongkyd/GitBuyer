package com.fernandocejas.android10.restrofit.enity.buyer;

import com.google.gson.annotations.SerializedName;

public class RateEntity_Buyer {

    @SerializedName("start")
    private String start;

    @SerializedName("count")
    private String count;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
