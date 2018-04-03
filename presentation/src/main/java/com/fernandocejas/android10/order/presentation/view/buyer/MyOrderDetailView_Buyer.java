/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;


import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface MyOrderDetailView_Buyer {

    void showBuyerUserInView(String avatar, String name);
    void showBuyerUser_AvatarInView(String url);
    void showBuyerUser_NameInView(String name);

    void showStatusInView(String status);

    void showDateTimeSoldInView(String deviverdate);
    void showDeliveryAddressInView(AddressModel_Buyer address);

    void showServiceFee_offerInView(String fee);
    void showBuyerFee_offerInView(String fee);
    void showShipFee_offerInView(String fee);
    void showSurchargeFee_offerInView(String fee);
    void showOtherFee_offerInVew(String fee);
    void showTaxFee_offerInView(String fee);
    void showTotalPrice_offerInView(String date);
    void showDescription_offerInView(String text);

}

