/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;


/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface MyOfferDetailView_Buyer {

    void showServiceFee_offerInView(String fee);

    void showBuyerFee_offerInView(String fee);

    void showShipFee_offerInView(String fee);

    void showSurchargeFee_offerInView(String fee);

    void showOtherFee_offerInVew(String fee);

    void showTaxFee_offerInView(String fee);

    void showDeliveryDate_offerInView(String date);

    void showDescription_offerInView(String text);

}

