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
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.MyOrderDetailView_Buyer;

import butterknife.Bind;

/**
 *
 *
 */
public class MyOrderDetailFragment_Buyer extends BaseOrderDetailFragment_Buyer implements MyOrderDetailView_Buyer {

    @Bind(R.id.imv_from_avatar)
    ImageView imv_from_avatar;
    @Bind(R.id.tv_from_name)
    TextView tv_from_name;
    @Bind(R.id.imv_to_avatar)
    ImageView imv_to_avatar;
    @Bind(R.id.tv_to_name)
    TextView tv_to_name;
    @Bind(R.id.imv_status)
    ImageView imv_status;
    @Bind(R.id.tv_date_time_sold)
    TextView tv_date_time_sold;
    @Bind(R.id.tv_address)
    TextView tv_address;

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
    @Bind(R.id.order_detail_total_price)
    TextView order_detail_total_price;
    @Bind(R.id.tv_offer_description)
    TextView tv_offer_description;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my_order_detail_buyer;
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
            //show date & time sold
            showDateTimeSoldInView(orderModel.getDeviverdate());

            //show delivery address
            showDeliveryAddressInView(orderModel.getAddress_Model_buyer());

        }
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
        }

        //
        lyt_main.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOrderUserInView(String avatar, String name) {
        showOrderUser_AvatarInView(avatar);
        showOrderUser_NameInView(name);
    }

    @Override
    public void showOrderUser_AvatarInView(String url) {
        loadImageFromUrl(activity(), imv_to_avatar, url, true, true);
    }

    @Override
    public void showOrderUser_NameInView(String name) {
        tv_to_name.setText(name);
    }

    @Override
    public void showBuyerUserInView(String avatar, String name) {
        showBuyerUser_AvatarInView(avatar);
        showBuyerUser_NameInView(name);
    }

    @Override
    public void showBuyerUser_AvatarInView(String url) {
        loadImageFromUrl(activity(), imv_from_avatar, url, true, true);
    }

    @Override
    public void showBuyerUser_NameInView(String name) {
        tv_from_name.setText(name);
    }

    @Override
    public void showStatusInView(String status) {
        imv_status.setVisibility(View.VISIBLE);
        switch (status) {
            case "0":
                imv_status.setImageResource(R.drawable.sts_1);
                break;
            case "1":
                imv_status.setImageResource(R.drawable.sts_2);
                break;
            case "2":
                imv_status.setImageResource(R.drawable.sts_3);
                break;
            case "3":
                imv_status.setImageResource(R.drawable.sts_4);
                break;
            case "4":
                imv_status.setImageResource(R.drawable.sts_5);
                break;
            case "5":
                imv_status.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void showDateTimeSoldInView(String deviverdate) {
        tv_date_time_sold.setText(deviverdate);
    }

    @Override
    public void showDeliveryAddressInView(AddressModel_Buyer address) {
        tv_address.setText(address.getAddress());
    }

    @Override
    public void showServiceFee_offerInView(String fee) {

    }

    @Override
    public void showBuyerFee_offerInView(String fee) {

    }

    @Override
    public void showShipFee_offerInView(String fee) {

    }

    @Override
    public void showSurchargeFee_offerInView(String fee) {

    }

    @Override
    public void showOtherFee_offerInVew(String fee) {

    }

    @Override
    public void showTaxFee_offerInView(String fee) {

    }

    @Override
    public void showTotalPrice_offerInView(String date) {

    }

    @Override
    public void showDescription_offerInView(String text) {

    }

}
