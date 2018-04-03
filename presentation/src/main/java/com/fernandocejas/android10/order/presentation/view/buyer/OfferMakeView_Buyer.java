package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

/**
 * Created by vandongluong on 3/28/18.
 */

public interface OfferMakeView_Buyer extends LoadDataView {

    // show user

    void showOrderNumberInView(String order_number);

    void showOrderDetailInView(OrderModel_Buyer orderModel);

    void showOrderUserInView(String avatar, String name);

    void showOrderUser_AvatarInView(String url);

    void showOrderUser_NameInView(String name);

    //show make offer

    void showTotalItemsInView(String value);
    void showTotalPriceInView(String value);
    void showSaleTaxInView(String value);
    void showEstimateTotalInView(String value);
    void showSaleTaxPercentInView(String value);
    void showServiceFeeInView(String value);

    void onLayoutBuyerFeeClicked();
    void onLayoutShipFeeClicked();
    void onLayoutSurchargeFeeClicked();
    void onLayoutOtherFeeClicked();
    void onLayoutDesriptionClicked();

    void onOfferMakeClick();
    void onPickerClick();
    void onBackClicked();
    void onChangeAVGClick(boolean isChanged);
}
