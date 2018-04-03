/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.ImageModel;
import com.fernandocejas.android10.order.presentation.model.OfferModel;
import com.fernandocejas.android10.order.presentation.model.OrderModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OfferModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ShipInfoModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.OrderAdapter;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.OrderAdapter_Buyer;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface OrderAdapterView_Buyer {

    void renderOfferList(OrderAdapter_Buyer.OrderViewHolder viewHolder, Collection<OfferModel_Buyer> offerModels, String status, String provider_id);

    void renderImageList(OrderAdapter_Buyer.OrderViewHolder viewHolder, Collection<ImageModel_Buyer> imageModels);

    void showDescriptionInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String description);

    void showShippingInfoInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String deliveryFrom, String deliveryTo);

    void showPriceInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String price);

    void showOrderDateInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String date_ago);

    void showShipFeeInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String price, String status);

    void showDeliveryDateInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String date, String status);

    void showStatusInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String status);

    void onOrderItemClicked(OrderModel_Buyer orderModel);
}

