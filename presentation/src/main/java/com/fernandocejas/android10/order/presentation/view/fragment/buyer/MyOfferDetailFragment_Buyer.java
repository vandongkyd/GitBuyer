/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.fragment.buyer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.view.buyer.MyOfferDetailView_Buyer;

import butterknife.Bind;

/**
 *
 *
 */
public class MyOfferDetailFragment_Buyer extends BaseOrderDetailFragment_Buyer implements MyOfferDetailView_Buyer {

    @Bind(R.id.imv_avatar)
    ImageView imv_avatar;

    @Bind(R.id.tv_service_fee)
    TextView tv_service_fee;

    @Bind(R.id.tv_offer_buy_fee)
    TextView tv_offer_buy_fee;
    @Bind(R.id.tv_offer_ship_fee)
    TextView tv_offer_ship_fee;
    @Bind(R.id.tv_offer_surcharge_fee)
    TextView tv_offer_surcharge_fee;
    @Bind(R.id.tv_offer_other_fee)
    TextView tv_offer_other_fee;
    @Bind(R.id.tv_offer_delivery_date)
    TextView tv_offer_delivery_date;
    @Bind(R.id.tv_offer_description)
    TextView tv_offer_description;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_offer_detail_buyer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(OrderComponent.class).inject(this);
    }

    @Override
    public void showOrderDetailInView(OrderModel_Buyer orderModel) {
        super.showOrderDetailInView(orderModel);

        {
            //show total items
            showTotalItemsInView(orderModel.getQuantity());

            //show total price
            String full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getAmount() + "", orderModel.getCurrency());
            showTotalPriceInView(full_price);

            //show sale tax percent
            showSaleTaxPercentInView(orderDetailPresenter.getSale_tax_in_percent() + "");

            //show sales tax
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getSale_tax_fee() + "", orderModel.getCurrency());
            showSaleTaxInView(full_price);

            //show estimate total
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getTotal() + "", orderModel.getCurrency());
            showEstimateTotalInView(full_price);
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getProvider_fee() + "", orderModel.getCurrency());
            showBuyerFee_offerInView(full_price);
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getShip_fee() + "", orderModel.getCurrency());
            showShipFee_offerInView(full_price);
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getSurcharge_fee() + "", orderModel.getCurrency());
            showSurchargeFee_offerInView(full_price);
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getOther_fee() + "", orderModel.getCurrency());
            showOtherFee_offerInVew(full_price);
            full_price = getPriceWithSymbolCurrency(orderDetailPresenter.getService_fee() + "", orderModel.getCurrency());
            showServiceFee_offerInView(full_price);
            showDeliveryDate_offerInView(orderDetailPresenter.getDeviverdate());
            showDescription_offerInView(orderDetailPresenter.getDescription());
        }

        //
        lyt_main.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOrderUser_AvatarInView(String url) {
        loadImageFromUrl(context(), imv_avatar, url, true, true);
    }

    @Override
    public void showOrderUser_NameInView(String text) {

    }


    @Override
    public void showOrderUserInView(String avatar, String name) {
        showOrderUser_AvatarInView(Constants.USER.getAvatar());
        showOrderUser_NameInView(Constants.USER.getName());
    }


    @Override
    public void showServiceFee_offerInView(String fee) {
        tv_service_fee.setText(fee);
    }

    @Override
    public void showBuyerFee_offerInView(String fee) {
        tv_offer_buy_fee.setText(fee);
    }

    @Override
    public void showShipFee_offerInView(String fee) {
        tv_offer_ship_fee.setText(fee);
    }

    @Override
    public void showSurchargeFee_offerInView(String fee) {
        tv_offer_surcharge_fee.setText(fee);
    }

    @Override
    public void showOtherFee_offerInVew(String fee) {
        tv_offer_other_fee.setText(fee);
    }

    @Override
    public void showTaxFee_offerInView(String fee) {

    }

    @Override
    public void showDeliveryDate_offerInView(String date) {
        tv_offer_delivery_date.setText(date);
    }

    @Override
    public void showDescription_offerInView(String text) {
        tv_offer_description.setText(text);
    }
}
