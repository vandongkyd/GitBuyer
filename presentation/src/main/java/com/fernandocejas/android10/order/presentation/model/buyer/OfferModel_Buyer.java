package com.fernandocejas.android10.order.presentation.model.buyer;

import java.io.Serializable;

/**
 * Created by vandongluong on 3/14/18.
 */

public class OfferModel_Buyer implements Serializable {

    private String providerid;
    private String orderid;
    private String description;
    private String deviverdate;
    private String providerfee;
    private String shipfee;
    private String surchargefee;
    private String otherfee;
    private String created_at;
    private String name;
    private String logo;
    private String website;
    private String phone;
    private String email;
    private RateModel_Buyer rate;
    private AddressModel_Buyer address;
    private ShipInfoModel_Buyer ship_from_country;

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

    public RateModel_Buyer getRate() {
        return rate;
    }

    public void setRate(RateModel_Buyer rate) {
        this.rate = rate;
    }

    public AddressModel_Buyer getAddress() {
        return address;
    }

    public void setAddress(AddressModel_Buyer address) {
        this.address = address;
    }

    public ShipInfoModel_Buyer getShip_from_country() {
        return ship_from_country;
    }

    public void setShip_from_country(ShipInfoModel_Buyer ship_from_country) {
        this.ship_from_country = ship_from_country;
    }
}
