package com.fernandocejas.android10.restrofit.enity.buyer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vandongluong on 3/14/18.
 */

public class OfferEntity_Buyer {
    @SerializedName("providerid")
    private String providerid;

    @SerializedName("orderid")
    private String orderid;

    @SerializedName("description")
    private String description;

    @SerializedName("deviverdate")
    private String deviverdate;

    @SerializedName("providerfee")
    private String providerfee;

    @SerializedName("shipfee")
    private String shipfee;

    @SerializedName("surchargefee")
    private String surchargefee;

    @SerializedName("otherfee")
    private String otherfee;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("name")
    private String name;

    @SerializedName("logo")
    private String logo;

    @SerializedName("website")
    private String website;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("rate")
    private RateEntity_Buyer rate;

    @SerializedName("address")
    private AddressEntity_Buyer address;

    @SerializedName("ship_from_country")
    private ShipInfoEntity_Buyer ship_from_country;

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviverdate() {
        return deviverdate;
    }

    public void setDeviverdate(String deviverdate) {
        this.deviverdate = deviverdate;
    }

    public String getProviderfee() {
        return providerfee;
    }

    public void setProviderfee(String providerfee) {
        this.providerfee = providerfee;
    }

    public String getShipfee() {
        return shipfee;
    }

    public void setShipfee(String shipfee) {
        this.shipfee = shipfee;
    }

    public String getSurchargefee() {
        return surchargefee;
    }

    public void setSurchargefee(String surchargefee) {
        this.surchargefee = surchargefee;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RateEntity_Buyer getRate() {
        return rate;
    }

    public void setRate(RateEntity_Buyer rate) {
        this.rate = rate;
    }

    public AddressEntity_Buyer getAddress() {
        return address;
    }

    public void setAddress(AddressEntity_Buyer address) {
        this.address = address;
    }

    public ShipInfoEntity_Buyer getShip_from_country() {
        return ship_from_country;
    }

    public void setShip_from_country(ShipInfoEntity_Buyer ship_from_country) {
        this.ship_from_country = ship_from_country;
    }
}
