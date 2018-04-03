/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.fragment.buyer;

import android.os.Bundle;

import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.presenter.buyer.BaseOrderListPresenter_Buyer;
import com.fernandocejas.android10.order.presentation.presenter.buyer.FeedPresenter_Buyer;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OrderDetailActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.OrderAdapter_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.FeedView_Buyer;

import javax.inject.Inject;

/**
 * Fragment that shows a list of Orders.
 */
public class OrderFeedListFragment_Buyer extends BaseOrderListFragment_Buyer implements FeedView_Buyer {

    @Inject
    FeedPresenter_Buyer feedPresenter_buyer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(OrderComponent.class).inject(this);
        orderAdapter.setAdapter_type(OrderAdapter_Buyer.TYPE_FEEDS);
    }

    @Override
    public BaseOrderListPresenter_Buyer getPresenter() {
        return feedPresenter_buyer;
    }

    @Override
    public int getFragmentDetailType() {
        return OrderDetailActivity_Buyer.FRAGMENT_TYPE_FEEDS;
    }

    @Override
    public void setView() {
        feedPresenter_buyer.setView(this);
    }
}
