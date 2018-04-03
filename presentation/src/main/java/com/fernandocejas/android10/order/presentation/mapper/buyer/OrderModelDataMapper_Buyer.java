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

import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.CountryModelDataMapper;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class OrderModelDataMapper_Buyer {

    private final AddressModelDataMapper_Buyer addressModelDataMapperBuyer;
    private final ProductModelDataMapper_Buyer productModelDataMapperBuyer;
    private final OfferModelDataMapper_Buyer offerModelDataMapperBuyer;
    private final CountryModelDataMapper countryModelDataMapper;
    private final UserModelDataMapper_Buyer userModelDataMapperBuyer;
    private final ShipInfoModelDataMapper_Buyer shipInfoModelDataMapperBuyer;

    @Inject
    public OrderModelDataMapper_Buyer(AddressModelDataMapper_Buyer addressModelDataMapperBuyer,
                                      ProductModelDataMapper_Buyer productModelDataMapperBuyer,
                                      OfferModelDataMapper_Buyer offerModelDataMapperBuyer,
                                      CountryModelDataMapper countryModelDataMapper,
                                      UserModelDataMapper_Buyer userModelDataMapperBuyer,
                                      ShipInfoModelDataMapper_Buyer shipInfoModelDataMapperBuyer) {
        this.addressModelDataMapperBuyer = addressModelDataMapperBuyer;
        this.productModelDataMapperBuyer = productModelDataMapperBuyer;
        this.offerModelDataMapperBuyer = offerModelDataMapperBuyer;
        this.countryModelDataMapper = countryModelDataMapper;
        this.userModelDataMapperBuyer = userModelDataMapperBuyer;
        this.shipInfoModelDataMapperBuyer = shipInfoModelDataMapperBuyer;
    }

    public OrderModel_Buyer transform(Order_Buyer order) {
        if (order == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final OrderModel_Buyer orderModel = new OrderModel_Buyer();
        orderModel.setId(order.getId());
        orderModel.setUserid(order.getUserid());
        orderModel.setProviderid(order.getProviderid());
        orderModel.setAmount(order.getAmount());
        orderModel.setQuantity(order.getQuantity());
        orderModel.setWeitght(order.getWeitght());
        orderModel.setTax_percent(order.getTax_percent());
        orderModel.setTax_amount(order.getTax_amount());
        orderModel.setDeviverdate(order.getDeviverdate());
        if (order.getDeliverto() != null) {
            orderModel.setAddress_Model_buyer(addressModelDataMapperBuyer.transform(order.getDeliverto()));
        }
        orderModel.setIos(order.getIos());
        orderModel.setCountry_name(order.getCountry_name());
        orderModel.setCurrency(order.getCurrency());
        orderModel.setDescription(order.getDescription());
        orderModel.setStatus(order.getStatus());
        orderModel.setCreated_at(order.getCreated_at());
        orderModel.setOfferid(order.getOfferid());
        orderModel.setOffer_description(order.getOffer_description());
        orderModel.setProviderfee(order.getProviderfee());
        orderModel.setShipfee(order.getShipfee());
        orderModel.setSurchargefee(order.getSurchargefee());
        orderModel.setOtherfee(order.getOtherfee());
        if (order.getShip_from_country() != null) {
            orderModel.setShip_from_country(shipInfoModelDataMapperBuyer.transform(order.getShip_from_country()));
        }
        orderModel.setShip_to_country(order.getShip_to_country());
        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            orderModel.setProducts(productModelDataMapperBuyer.transform(order.getProducts()));
        }
        if (order.getOffers() != null && !order.getOffers().isEmpty()) {
            orderModel.setOffers(offerModelDataMapperBuyer.transform(order.getOffers()));
        }
        if (order.getCountry() != null) {
            orderModel.setCountry(countryModelDataMapper.transform(order.getCountry()));
        }
        if (order.getOrder_info() != null) {
            orderModel.setOrder_info(userModelDataMapperBuyer.transform(order.getOrder_info()));
        }

        return orderModel;
    }

    public Collection<OrderModel_Buyer> transform(Collection<Order_Buyer> ordersCollection) {
        Collection<OrderModel_Buyer> orderModelsCollection;

        if (ordersCollection != null && !ordersCollection.isEmpty()) {
            orderModelsCollection = new ArrayList<>();
            for (Order_Buyer order : ordersCollection) {
                orderModelsCollection.add(transform(order));
            }
        } else {
            orderModelsCollection = Collections.emptyList();
        }

        return orderModelsCollection;
    }

    //

    public Order_Buyer transform(OrderModel_Buyer order) {
        if (order == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Order_Buyer orderModel = new Order_Buyer();
        orderModel.setId(order.getId());
        orderModel.setUserid(order.getUserid());
        orderModel.setProviderid(order.getProviderid());
        orderModel.setAmount(order.getAmount());
        orderModel.setQuantity(order.getQuantity());
        orderModel.setWeitght(order.getWeitght());
        orderModel.setTax_percent(order.getTax_percent());
        orderModel.setTax_amount(order.getTax_amount());
        orderModel.setDeviverdate(order.getDeviverdate());
        if (order.getAddress_Model_buyer() != null) {
            orderModel.setDeliverto(addressModelDataMapperBuyer.transform(order.getAddress_Model_buyer()));
        }
        orderModel.setIos(order.getIos());
        orderModel.setCountry_name(order.getCountry_name());
        orderModel.setCurrency(order.getCurrency());
        orderModel.setDescription(order.getDescription());
        orderModel.setStatus(order.getStatus());
        orderModel.setCreated_at(order.getCreated_at());
        orderModel.setOfferid(order.getOfferid());
        orderModel.setOffer_description(order.getOffer_description());
        orderModel.setProviderfee(order.getProviderfee());
        orderModel.setShipfee(order.getShipfee());
        orderModel.setSurchargefee(order.getSurchargefee());
        orderModel.setOtherfee(order.getOtherfee());
        if (order.getShip_from_country() != null) {
            orderModel.setShip_from_country(shipInfoModelDataMapperBuyer.transform(order.getShip_from_country()));
        }
        orderModel.setShip_to_country(order.getShip_to_country());
        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            orderModel.setProducts(productModelDataMapperBuyer.transformToDomain(order.getProducts()));
        }
        if (order.getOffers() != null && !order.getOffers().isEmpty()) {
            orderModel.setOffers(offerModelDataMapperBuyer.transformToDomain(order.getOffers()));
        }
        if (order.getCountry() != null) {
            orderModel.setCountry(countryModelDataMapper.transform(order.getCountry()));
        }
        if (order.getOrder_info() != null) {
            orderModel.setOrder_info(userModelDataMapperBuyer.transform(order.getOrder_info()));
        }

        return orderModel;
    }


}
