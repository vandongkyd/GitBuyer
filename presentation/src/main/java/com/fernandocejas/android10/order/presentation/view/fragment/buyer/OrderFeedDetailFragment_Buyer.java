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
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OrderFeedDetailView_Buyer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 *
 *
 */
public class OrderFeedDetailFragment_Buyer extends BaseOrderDetailFragment_Buyer implements OrderFeedDetailView_Buyer {

    @Bind(R.id.imv_avatar)
    ImageView imv_avatar;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_order_feed_detail_buyer;
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
        }

        //
        lyt_main.setVisibility(View.VISIBLE);

    }

    @Override
    public void showOrderUser_AvatarInView(String url) {
        loadImageFromUrl(context(), imv_avatar, url, true, true);
    }

    @Override
    public void showOrderUser_NameInView(String name) {

    }

    @Override
    public void showOrderUserInView(String avatar, String name) {
        showOrderUser_AvatarInView(avatar);
    }

    @Override
    @OnClick(R.id.btn_make_offer)
    public void onMakeOfferClicked() {
        orderDetailPresenter.navigateToMakeOffer(orderDetailPresenter.getOrder());
    }
}
