/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.order.presentation.mapper.buyer;

import com.fernandocejas.android10.order.domain.buyer.Offer_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OfferModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class OfferModelDataMapper_Buyer {

    private final AddressModelDataMapper_Buyer addressModelDataMapperBuyer;
    private final RateModelDataMapper_Buyer rateModelDataMapperBuyer;
    private final ShipInfoModelDataMapper_Buyer shipInfoModelDataMapperBuyer;

    @Inject
    public OfferModelDataMapper_Buyer(AddressModelDataMapper_Buyer addressModelDataMapperBuyer, RateModelDataMapper_Buyer rateModelDataMapperBuyer, ShipInfoModelDataMapper_Buyer shipInfoModelDataMapperBuyer) {
        this.addressModelDataMapperBuyer = addressModelDataMapperBuyer;
        this.rateModelDataMapperBuyer = rateModelDataMapperBuyer;
        this.shipInfoModelDataMapperBuyer = shipInfoModelDataMapperBuyer;
    }

    public OfferModel_Buyer transform(Offer_Buyer offer) {
        if (offer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final OfferModel_Buyer offerModel = new OfferModel_Buyer();
        offerModel.setProviderid(offer.getProviderid());
        offerModel.setOrderid(offer.getOrderid());
        offerModel.setDescription(offer.getDescription());
        offerModel.setDeviverdate(offer.getDeviverdate());
        offerModel.setProviderfee(offer.getProviderfee());
        offerModel.setShipfee(offer.getShipfee());
        offerModel.setSurchargefee(offer.getSurchargefee());
        offerModel.setOtherfee(offer.getOtherfee());
        offerModel.setCreated_at(offer.getCreated_at());
        offerModel.setLogo(offer.getLogo());
        offerModel.setWebsite(offer.getWebsite());
        offerModel.setPhone(offer.getPhone());
        offerModel.setEmail(offer.getEmail());
        offerModel.setName(offer.getName());
        if (offer.getRate() != null) {
            offerModel.setRate(rateModelDataMapperBuyer.transform(offer.getRate()));
        }
        if (offer.getAddress() != null) {
            offerModel.setAddress(addressModelDataMapperBuyer.transform(offer.getAddress()));
        }
        if (offer.getShip_from_country() != null) {
            offerModel.setShip_from_country(shipInfoModelDataMapperBuyer.transform(offer.getShip_from_country()));
        }
        return offerModel;
    }

    public Collection<OfferModel_Buyer> transform(Collection<Offer_Buyer> offersCollection) {
        Collection<OfferModel_Buyer> offerModelsCollection;

        if (offersCollection != null && !offersCollection.isEmpty()) {
            offerModelsCollection = new ArrayList<>();
            for (Offer_Buyer Offer : offersCollection) {
                offerModelsCollection.add(transform(Offer));
            }
        } else {
            offerModelsCollection = Collections.emptyList();
        }

        return offerModelsCollection;
    }

    //

    public Offer_Buyer transform(OfferModel_Buyer offer) {
        if (offer == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Offer_Buyer offerModel = new Offer_Buyer();
        offerModel.setProviderid(offer.getProviderid());
        offerModel.setOrderid(offer.getOrderid());
        offerModel.setDescription(offer.getDescription());
        offerModel.setDeviverdate(offer.getDeviverdate());
        offerModel.setProviderfee(offer.getProviderfee());
        offerModel.setShipfee(offer.getShipfee());
        offerModel.setSurchargefee(offer.getSurchargefee());
        offerModel.setOtherfee(offer.getOtherfee());
        offerModel.setCreated_at(offer.getCreated_at());
        offerModel.setLogo(offer.getLogo());
        offerModel.setWebsite(offer.getWebsite());
        offerModel.setPhone(offer.getPhone());
        offerModel.setEmail(offer.getEmail());
        offerModel.setName(offer.getName());
        if (offer.getRate() != null) {
            offerModel.setRate(rateModelDataMapperBuyer.transform(offer.getRate()));
        }
        if (offer.getAddress() != null) {
            offerModel.setAddress(addressModelDataMapperBuyer.transform(offer.getAddress()));
        }
        if (offer.getShip_from_country() != null) {
            offerModel.setShip_from_country(shipInfoModelDataMapperBuyer.transform(offer.getShip_from_country()));
        }
        return offerModel;
    }

    public Collection<Offer_Buyer> transformToDomain(Collection<OfferModel_Buyer> offersCollection) {
        Collection<Offer_Buyer> offerModelsCollection;

        if (offersCollection != null && !offersCollection.isEmpty()) {
            offerModelsCollection = new ArrayList<>();
            for (OfferModel_Buyer Offer : offersCollection) {
                offerModelsCollection.add(transform(Offer));
            }
        } else {
            offerModelsCollection = Collections.emptyList();
        }

        return offerModelsCollection;
    }
}

