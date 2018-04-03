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

import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.domain.interactor.buyer.GetMyOrder_Buyer;
import com.fernandocejas.android10.order.domain.interactor.buyer.GetOfferList_Buyer;
import com.fernandocejas.android10.order.domain.interactor.buyer.GetOrderList_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.OrderModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.PreferencesUtility;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.HomeActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.BaseOrderListView_Buyer;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public abstract class BaseOrderListPresenter_Buyer implements Presenter {

    private final OrderModelDataMapper_Buyer orderModelDataMapper;

    private List<Order_Buyer> orders;

    public BaseOrderListPresenter_Buyer(OrderModelDataMapper_Buyer orderModelDataMapper) {
        this.orderModelDataMapper = orderModelDataMapper;
    }

    public abstract BaseOrderListView_Buyer getView();

    public abstract UseCase getOrderListUseCase();

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setOrders(List<Order_Buyer> orders, boolean isAppend) {
        if (isAppend) {
            if (this.orders == null) {
                this.orders = new ArrayList<>();
            }
            this.orders.addAll(orders);
        } else {
            this.orders = orders;
        }
        this.showOrdersCollectionInView(orders, false);
    }

    public List<Order_Buyer> getOrders() {
        return orders;
    }

    /**
     * Loads orders.
     */
    public void loadOrderList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getOrderList();
    }

    public void refreshOrderList() {
        this.showViewRefreshing();
        this.getOrderListByRefresh();
    }

    public void loadMoreOrderList(String offset) {
        this.showViewLoadingMore();
        this.getOrderListWithOffset(offset);
    }

    public void onOrderClicked(OrderModel_Buyer orderModel, int type) {
        if (orderModel != null) {
            navigateToOrderDetail(orderModelDataMapper.transform(orderModel), type);
        }
    }

    private void navigateToOrderDetail(Order_Buyer order_buyer, int type) {
        if (this.getView().activity() instanceof HomeActivity_Buyer) {
            ((HomeActivity_Buyer) this.getView().activity()).navigateToOrderDetail_Buyer(order_buyer, type);
        }
    }

    private void showViewLoading() {
        this.getView().showLoading();
    }

    private void hideViewLoading() {
        this.getView().hideLoading();
    }

    private void showViewRetry() {
        this.getView().showRetry();
    }

    private void hideViewRetry() {
        this.getView().hideRetry();
    }

    private void showViewRefreshing() {
        this.getView().showRefreshing();
    }

    private void hideViewRefreshing() {
        this.getView().hideRefreshing();
    }

    private void showViewLoadingMore() {
        this.getView().showLoadingMore();
    }

    private void hideViewLoadingMore() {
        this.getView().hideLoadingMore();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.getView().context(),
                errorBundle.getException());
        this.getView().showError(errorMessage);
    }

    public void showOrdersCollectionInView(Collection<Order_Buyer> ordersCollection, boolean isAppend) {
        Collection<OrderModel_Buyer> orderModelsCollection;
        if (ordersCollection != null) {
            orderModelsCollection =
                    this.orderModelDataMapper.transform(ordersCollection);
        } else {
            orderModelsCollection =
                    this.orderModelDataMapper.transform(this.orders);
        }
        if (this.getView() != null) {
            this.getView().renderOrderList(orderModelsCollection, isAppend);
        }
    }

    private void getOrderList() {
        String token = PreferencesUtility.getInstance(getView().context())
                .readString(PreferencesUtility.PREF_TOKEN, null);
        if (this.getOrderListUseCase() instanceof GetOrderList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListObserver(), GetOrderList_Buyer.Params.forOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetMyOrder_Buyer) {
            this.getOrderListUseCase().execute(new OrderListObserver(), GetMyOrder_Buyer.Params.forMyOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetOfferList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListObserver(), GetOfferList_Buyer.Params.forOffer(token/*, 0 + ""*/));
        }
    }

    private void getOrderListWithOffset(String offset) {
        String token = PreferencesUtility.getInstance(getView().context())
                .readString(PreferencesUtility.PREF_TOKEN, null);
        if (this.getOrderListUseCase() instanceof GetOrderList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListWithOffsetObserver(), GetOrderList_Buyer.Params.forOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetMyOrder_Buyer) {
            this.getOrderListUseCase().execute(new OrderListWithOffsetObserver(), GetMyOrder_Buyer.Params.forMyOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetOfferList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListWithOffsetObserver(), GetOfferList_Buyer.Params.forOffer(token/*, 0 + ""*/));
        }
    }

    private void getOrderListByRefresh() {
        String token = PreferencesUtility.getInstance(getView().context())
                .readString(PreferencesUtility.PREF_TOKEN, null);
        if (this.getOrderListUseCase() instanceof GetOrderList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListByRefreshObserver(), GetOrderList_Buyer.Params.forOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetMyOrder_Buyer) {
            this.getOrderListUseCase().execute(new OrderListByRefreshObserver(), GetMyOrder_Buyer.Params.forMyOrder(token/*, 0 + ""*/));
        } else if (this.getOrderListUseCase() instanceof GetOfferList_Buyer) {
            this.getOrderListUseCase().execute(new OrderListByRefreshObserver(), GetOfferList_Buyer.Params.forOffer(token/*, 0 + ""*/));
        }
    }

    private void loadMoreError() {
        this.getView().loadMoreError();
    }

    private final class OrderListObserver extends DefaultObserver<List<Order_Buyer>> {

        @Override
        public void onComplete() {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewLoading();
            }
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewLoading();
                BaseOrderListPresenter_Buyer.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
                BaseOrderListPresenter_Buyer.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<Order_Buyer> orders) {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.setOrders(orders, false);
            }
        }
    }

    private final class OrderListWithOffsetObserver extends DefaultObserver<List<Order_Buyer>> {

        @Override
        public void onComplete() {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewLoadingMore();
            }
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewLoadingMore();
                BaseOrderListPresenter_Buyer.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
                BaseOrderListPresenter_Buyer.this.loadMoreError();
            }
        }

        @Override
        public void onNext(List<Order_Buyer> orders) {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.setOrders(orders, true);
            }
        }
    }

    private final class OrderListByRefreshObserver extends DefaultObserver<List<Order_Buyer>> {

        @Override
        public void onComplete() {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewRefreshing();
            }
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.hideViewRefreshing();
                BaseOrderListPresenter_Buyer.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            }
        }

        @Override
        public void onNext(List<Order_Buyer> orders) {
            if (BaseOrderListPresenter_Buyer.this.getView() != null) {
                BaseOrderListPresenter_Buyer.this.setOrders(orders, false);
            }
        }
    }

}
