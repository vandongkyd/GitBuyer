/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

import java.util.Collection;


public interface OrderView_Buyer extends LoadDataView {

    void renderOrderList(Collection<OrderModel_Buyer> orderModelCollection, boolean isAppend);

    void onDismissClicked();

    void showRefreshing();

    void hideRefreshing();

    void loadMoreError();

    void showLoadingMore();

    void hideLoadingMore();
}

