package com.fernandocejas.android10.restrofit.enity.buyer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vandongluong on 3/29/18.
 */

public class ShipInfoEntity_Buyer {
    @SerializedName("id")
    public String id ;
    @SerializedName("countryCode")
    public String countryCode ;
    @SerializedName("countryName")
    public String countryName ;
    @SerializedName("currencyCode")
    public String currencyCode ;
    @SerializedName("currencySymbol")
    public String currencySymbol ;
    @SerializedName("fipsCode")
    public String fipsCode ;
    @SerializedName("isoNumeric")
    public String isoNumeric ;
    @SerializedName("continentName")
    public String continentName ;
    @SerializedName("continent")
    public String continent ;
    @SerializedName("isoAlpha3")
    public String isoAlpha3 ;
    @SerializedName("phonecode")
    public String phonecode ;
    @SerializedName("formatDate")
    public String formatDate ;
    @SerializedName("saleTax")
    public String saleTax ;
    @SerializedName("prefixCurrencySymbol")
    public String prefixCurrencySymbol ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getFipsCode() {
        return fipsCode;
    }

    public void setFipsCode(String fipsCode) {
        this.fipsCode = fipsCode;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode;
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }

    public String getSaleTax() {
        return saleTax;
    }

    public void setSaleTax(String saleTax) {
        this.saleTax = saleTax;
    }

    public String getPrefixCurrencySymbol() {
        return prefixCurrencySymbol;
    }

    public void setPrefixCurrencySymbol(String prefixCurrencySymbol) {
        this.prefixCurrencySymbol = prefixCurrencySymbol;
    }
}
