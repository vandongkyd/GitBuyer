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

import com.fernandocejas.android10.order.domain.interactor.buyer.GetOfferList_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.OrderModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.BaseOrderListView_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OfferListView_Buyer;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class OfferListPresenter_Buyer extends BaseOrderListPresenter_Buyer {

    private OfferListView_Buyer offerListView_buyer;

    private GetOfferList_Buyer getOfferListUseCase;

    @Inject
    public OfferListPresenter_Buyer(GetOfferList_Buyer getOfferListUseCase, OrderModelDataMapper_Buyer orderModelDataMapper) {
        super(orderModelDataMapper);
        this.getOfferListUseCase = getOfferListUseCase;
    }

    @Override
    public BaseOrderListView_Buyer getView() {
        return offerListView_buyer;
    }

    @Override
    public UseCase getOrderListUseCase() {
        return getOfferListUseCase;
    }

    public void setView(@NonNull OfferListView_Buyer view) {
        this.offerListView_buyer = view;
    }

    @Override
    public void destroy() {
        super.destroy();
        offerListView_buyer = null;
    }

}
