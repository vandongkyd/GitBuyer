package com.fernandocejas.android10.order.presentation.model.buyer;

import com.fernandocejas.android10.order.presentation.model.CountryModel;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by vandongluong on 3/14/18.
 */

public class OrderModel_Buyer implements Serializable{

    private String id ;
    private String userid ;
    private String providerid ;
    private String amount ;
    private String quantity ;
    private String weitght ;
    private String tax_percent ;
    private String tax_amount ;
    private String deviverdate ;
    private AddressModel_Buyer address_Model_buyer;
    private String ios ;
    private String country_name ;
    private String currency ;
    private String description ;
    private String status ;
    private String created_at ;
    private String offerid;
    private String offer_description;
    private String providerfee;
    private String shipfee;
    private String surchargefee;
    private String otherfee;
    private ShipInfoModel_Buyer ship_from_country ;
    private String ship_to_country ;
    private Collection<ProductModel_Buyer> products ;
    private Collection<OfferModel_Buyer> offers ;
    private CountryModel country;
    private UserModel_Buyer order_info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeitght() {
        return weitght;
    }

    public void setWeitght(String weitght) {
        this.weitght = weitght;
    }

    public String getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(String tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getDeviverdate() {
        return deviverdate;
    }

    public void setDeviverdate(String deviverdate) {
        this.deviverdate = deviverdate;
    }

    public AddressModel_Buyer getAddress_Model_buyer() {
        return address_Model_buyer;
    }

    public void setAddress_Model_buyer(AddressModel_Buyer address_Model_buyer) {
        this.address_Model_buyer = address_Model_buyer;
    }

    public String getIos() {
        return ios;
    }

    public void setIos(String ios) {
        this.ios = ios;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setOfferid(String offerid) {
        this.offerid = offerid;
    }

    public String getOffer_description() {
        return offer_description;
    }

    public void setOffer_description(String offer_description) {
        this.offer_description = offer_description;
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

    public ShipInfoModel_Buyer getShip_from_country() {
        return ship_from_country;
    }

    public void setShip_from_country(ShipInfoModel_Buyer ship_from_country) {
        this.ship_from_country = ship_from_country;
    }

    public String getShip_to_country() {
        return ship_to_country;
    }

    public void setShip_to_country(String ship_to_country) {
        this.ship_to_country = ship_to_country;
    }

    public Collection<ProductModel_Buyer> getProducts() {
        return products;
    }

    public void setProducts(Collection<ProductModel_Buyer> products) {
        this.products = products;
    }

    public Collection<OfferModel_Buyer> getOffers() {
        return offers;
    }

    public void setOffers(Collection<OfferModel_Buyer> offers) {
        this.offers = offers;
    }

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

    public UserModel_Buyer getOrder_info() {
        return order_info;
    }

    public void setOrder_info(UserModel_Buyer order_info) {
        this.order_info = order_info;
    }
}