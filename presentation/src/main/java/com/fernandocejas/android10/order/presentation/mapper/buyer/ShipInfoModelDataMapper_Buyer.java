package com.fernandocejas.android10.order.presentation.mapper.buyer;

import com.fernandocejas.android10.order.domain.buyer.Address_Buyer;
import com.fernandocejas.android10.order.domain.buyer.ShipInfo_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ShipInfoModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by vandongluong on 3/29/18.
 */
@PerActivity
public class ShipInfoModelDataMapper_Buyer {
    @Inject
    ShipInfoModelDataMapper_Buyer(){}

    public ShipInfoModel_Buyer transform(ShipInfo_Buyer shipInfo_buyer) {
        if (shipInfo_buyer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ShipInfoModel_Buyer shipInfoModel_buyer = new ShipInfoModel_Buyer();
        shipInfoModel_buyer.setId(shipInfo_buyer.getId());
        shipInfoModel_buyer.setCountryCode(shipInfo_buyer.getCountryCode());
        shipInfoModel_buyer.setCountryName(shipInfo_buyer.getCountryName());
        shipInfoModel_buyer.setCurrencyCode(shipInfo_buyer.getCurrencyCode());
        shipInfoModel_buyer.setCurrencySymbol(shipInfo_buyer.getCurrencySymbol());
        shipInfoModel_buyer.setFipsCode(shipInfo_buyer.getFipsCode());
        shipInfoModel_buyer.setIsoNumeric(shipInfo_buyer.getIsoNumeric());
        shipInfoModel_buyer.setContinentName(shipInfo_buyer.getContinentName());
        shipInfoModel_buyer.setContinent(shipInfo_buyer.getContinent());
        shipInfoModel_buyer.setIsoAlpha3(shipInfo_buyer.getIsoAlpha3());
        shipInfoModel_buyer.setPhonecode(shipInfo_buyer.getPhonecode());
        shipInfoModel_buyer.setFormatDate(shipInfo_buyer.getFormatDate());
        shipInfoModel_buyer.setSaleTax(shipInfo_buyer.getSaleTax());
        shipInfoModel_buyer.setPrefixCurrencySymbol(shipInfo_buyer.getPrefixCurrencySymbol());
        return shipInfoModel_buyer;
    }

    public Collection<ShipInfoModel_Buyer> transform(Collection<ShipInfo_Buyer> shipInfoBuyerCollection) {
        Collection<ShipInfoModel_Buyer> shipInfoModel_buyers;

        if (shipInfoBuyerCollection != null && !shipInfoBuyerCollection.isEmpty()) {
            shipInfoModel_buyers = new ArrayList<>();
            for (ShipInfo_Buyer shipInfo_buyer : shipInfoBuyerCollection) {
                shipInfoModel_buyers.add(transform(shipInfo_buyer));
            }
        } else {
            shipInfoModel_buyers = Collections.emptyList();
        }

        return shipInfoModel_buyers;
    }


    public ShipInfo_Buyer transform(ShipInfoModel_Buyer shipInfoModelBuyer) {
        if (shipInfoModelBuyer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ShipInfo_Buyer shipInfoBuyer = new ShipInfo_Buyer();
        shipInfoBuyer.setId(shipInfoModelBuyer.getId());
        shipInfoBuyer.setCountryCode(shipInfoModelBuyer.getCountryCode());
        shipInfoBuyer.setCountryName(shipInfoModelBuyer.getCountryName());
        shipInfoBuyer.setCurrencyCode(shipInfoModelBuyer.getCurrencyCode());
        shipInfoBuyer.setCurrencySymbol(shipInfoModelBuyer.getCurrencySymbol());
        shipInfoBuyer.setFipsCode(shipInfoModelBuyer.getFipsCode());
        shipInfoBuyer.setIsoNumeric(shipInfoModelBuyer.getIsoNumeric());
        shipInfoBuyer.setContinentName(shipInfoModelBuyer.getContinentName());
        shipInfoBuyer.setContinent(shipInfoModelBuyer.getContinent());
        shipInfoBuyer.setIsoAlpha3(shipInfoModelBuyer.getIsoAlpha3());
        shipInfoBuyer.setPhonecode(shipInfoModelBuyer.getPhonecode());
        shipInfoBuyer.setFormatDate(shipInfoModelBuyer.getFormatDate());
        shipInfoBuyer.setSaleTax(shipInfoModelBuyer.getSaleTax());
        shipInfoBuyer.setPrefixCurrencySymbol(shipInfoModelBuyer.getPrefixCurrencySymbol());
        return shipInfoBuyer;
    }
}
