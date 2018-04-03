package com.fernandocejas.android10.restrofit.enity.mapper.buyer;



import com.fernandocejas.android10.order.domain.buyer.ShipInfo_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.ShipInfoEntityResponse_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.ShipInfoEntity_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.ShipInfroListEntityResponse_Buyer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by vandongluong on 3/29/18.
 */
@Singleton
public class ShipInfoEntityDataMapper {
    @Inject
    ShipInfoEntityDataMapper(){

    }
    /**
     * Transform a {@link ShipInfoEntity_Buyer} into an {@link ShipInfo_Buyer}.
     *
     * @param shipInfoEntityBuyer Object to be transformed.
     * @return {@link ShipInfo_Buyer} if valid {@link ShipInfoEntity_Buyer} otherwise null.
     */
    public ShipInfo_Buyer transform(ShipInfoEntity_Buyer shipInfoEntityBuyer) {
        ShipInfo_Buyer shipInfo_buyer = null;
        if (shipInfoEntityBuyer != null) {
            shipInfo_buyer = new ShipInfo_Buyer();
            shipInfo_buyer.setId(shipInfoEntityBuyer.getId());
            shipInfo_buyer.setCountryCode(shipInfoEntityBuyer.getCountryCode());
            shipInfo_buyer.setCountryName(shipInfoEntityBuyer.getCountryName());
            shipInfo_buyer.setCurrencyCode(shipInfoEntityBuyer.getCurrencyCode());
            shipInfo_buyer.setCurrencySymbol(shipInfoEntityBuyer.getCurrencySymbol());
            shipInfo_buyer.setFipsCode(shipInfoEntityBuyer.getFipsCode());
            shipInfo_buyer.setIsoNumeric(shipInfoEntityBuyer.getIsoNumeric());
            shipInfo_buyer.setContinentName(shipInfoEntityBuyer.getContinentName());
            shipInfo_buyer.setContinent(shipInfoEntityBuyer.getContinent());
            shipInfo_buyer.setIsoAlpha3(shipInfoEntityBuyer.getIsoAlpha3());
            shipInfo_buyer.setPhonecode(shipInfoEntityBuyer.getPhonecode());
            shipInfo_buyer.setFormatDate(shipInfoEntityBuyer.getFormatDate());
            shipInfo_buyer.setSaleTax(shipInfoEntityBuyer.getSaleTax());
            shipInfo_buyer.setPrefixCurrencySymbol(shipInfoEntityBuyer.getPrefixCurrencySymbol());

        }
        return shipInfo_buyer;
    }
    /**
     * Transform a List of {@link ShipInfoEntity_Buyer} into a Collection of {@link ShipInfo_Buyer}.
     *
     * @param shipInfoEntityCollection_buyer Object Collection to be transformed.
     * @return {@link ShipInfoEntity_Buyer} if valid {@link AddressEntity_Buyer} otherwise null.
     */
    public List<ShipInfo_Buyer> transform(Collection<ShipInfoEntity_Buyer> shipInfoEntityCollection_buyer) {
        final List<ShipInfo_Buyer> shipInfoList_buyer = new ArrayList<>();
        for (ShipInfoEntity_Buyer shipInfoEntity : shipInfoEntityCollection_buyer) {
            final ShipInfo_Buyer shipInfo = transform(shipInfoEntity);
            if (shipInfo != null) {
                shipInfoList_buyer.add(shipInfo);
            }
        }
        return shipInfoList_buyer;
    }

    public ShipInfoEntity_Buyer transform(ShipInfo_Buyer shipInfobuyer) {
        ShipInfoEntity_Buyer shipInfoEntity_buyer = null;
        if (shipInfobuyer != null) {
            shipInfoEntity_buyer = new ShipInfoEntity_Buyer();
            shipInfoEntity_buyer.setId(shipInfobuyer.getId());
            shipInfoEntity_buyer.setCountryCode(shipInfobuyer.getCountryCode());
            shipInfoEntity_buyer.setCountryName(shipInfobuyer.getCountryName());
            shipInfoEntity_buyer.setCurrencyCode(shipInfobuyer.getCurrencyCode());
            shipInfoEntity_buyer.setCurrencySymbol(shipInfobuyer.getCurrencySymbol());
            shipInfoEntity_buyer.setFipsCode(shipInfobuyer.getFipsCode());
            shipInfoEntity_buyer.setIsoNumeric(shipInfobuyer.getIsoNumeric());
            shipInfoEntity_buyer.setContinentName(shipInfobuyer.getContinentName());
            shipInfoEntity_buyer.setContinent(shipInfobuyer.getContinent());
            shipInfoEntity_buyer.setIsoAlpha3(shipInfobuyer.getIsoAlpha3());
            shipInfoEntity_buyer.setPhonecode(shipInfobuyer.getPhonecode());
            shipInfoEntity_buyer.setFormatDate(shipInfobuyer.getFormatDate());
            shipInfoEntity_buyer.setSaleTax(shipInfobuyer.getSaleTax());
            shipInfoEntity_buyer.setPrefixCurrencySymbol(shipInfobuyer.getPrefixCurrencySymbol());

        }
        return shipInfoEntity_buyer;
    }

    public List<ShipInfoEntity_Buyer> transformToEntity(Collection<ShipInfo_Buyer> addressCollection_buyer) {
        final List<ShipInfoEntity_Buyer> addressEntityList_buyer = new ArrayList<>();
        for (ShipInfo_Buyer address : addressCollection_buyer) {
            final ShipInfoEntity_Buyer addressEntity_buyer = transform(address);
            if (addressEntity_buyer != null) {
                addressEntityList_buyer.add(addressEntity_buyer);
            }
        }
        return addressEntityList_buyer;
    }

    public List<ShipInfo_Buyer> transform(ShipInfroListEntityResponse_Buyer shipListEntityResponse_buyer) throws Exception {
        List<ShipInfo_Buyer> shipList_buyer = null;
        if (shipListEntityResponse_buyer != null) {
            if (shipListEntityResponse_buyer.status() == false) {
                throw new Exception(shipListEntityResponse_buyer.message());
            }
            shipList_buyer = transform(shipListEntityResponse_buyer.data());
        }
        return shipList_buyer;
    }

    public ShipInfo_Buyer transform(ShipInfoEntityResponse_Buyer shipEntityResponse_buyer) throws Exception {
        ShipInfo_Buyer shipInfo_buyer = null;
        if (shipEntityResponse_buyer != null) {
            if (shipEntityResponse_buyer.status() == false) {
                throw new Exception(shipEntityResponse_buyer.message());
            }
            shipInfo_buyer = transform(shipEntityResponse_buyer.data());
        }
        return shipInfo_buyer;
    }

}
