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
package com.fernandocejas.android10.order.presentation.presenter.buyer;

import android.support.annotation.NonNull;

import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.domain.interactor.buyer.GetOrderList_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.OrderModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.utils.PreferencesUtility;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.HomeActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.BaseOrderListView_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.FeedView_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OfferListView_Buyer;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class FeedPresenter_Buyer extends BaseOrderListPresenter_Buyer {

    private FeedView_Buyer feedView_buyer;

    private GetOrderList_Buyer getOrderListUseCase;

    @Inject
    public FeedPresenter_Buyer(GetOrderList_Buyer getOrderListUseCase, OrderModelDataMapper_Buyer orderModelDataMapper) {
        super(orderModelDataMapper);
        this.getOrderListUseCase = getOrderListUseCase;
    }

    @Override
    public BaseOrderListView_Buyer getView() {
        return feedView_buyer;
    }

    @Override
    public UseCase getOrderListUseCase() {
        return getOrderListUseCase;
    }

    public void setView(@NonNull FeedView_Buyer view) {
        this.feedView_buyer = view;
    }

    @Override
    public void destroy() {
        super.destroy();
        feedView_buyer = null;
    }
}
