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

import com.fernandocejas.android10.order.domain.buyer.Offer_Buyer;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.OrderModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.ProductModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OrderDetailActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.MyOrderDetailView_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OrderDetailView_Buyer;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class OrderDetailPresenter_Buyer implements Presenter {

    private OrderDetailView_Buyer orderDetailView;

    private final OrderModelDataMapper_Buyer orderModelDataMapper;
    private final ProductModelDataMapper_Buyer productModelDataMapper;

    private Order_Buyer order;
    private float amount;
    private float sale_tax_fee;
    private float sale_tax_in_percent;
    private float service_fee;
    private float provider_fee;
    private float ship_fee;
    private float surcharge_fee;
    private float other_fee;
    private String deviverdate;
    private String description;

    @Inject
    public OrderDetailPresenter_Buyer(OrderModelDataMapper_Buyer orderModelDataMapper,
                                      ProductModelDataMapper_Buyer productModelDataMapper) {
        this.orderModelDataMapper = orderModelDataMapper;
        this.productModelDataMapper = productModelDataMapper;
    }

    public void setView(@NonNull OrderDetailView_Buyer view) {
        this.orderDetailView = view;
    }

    public void setOrder(Order_Buyer order) {
        this.order = order;
        if (order != null) {
            try {
                this.amount = Float.valueOf(order.getAmount());
            } catch (Exception ex) {
            }
            try {
                this.sale_tax_in_percent = Float.valueOf(order.getTax_percent());
            } catch (Exception ex) {
            }
            try {
                this.service_fee = 0;/*Float.valueOf(order.getServicefee());*/
            } catch (Exception ex) {
            }
            try {
                this.sale_tax_fee = amount * sale_tax_in_percent / 100;
            } catch (Exception ex) {

            }
            {
                try {
                    this.provider_fee = Float.valueOf(order.getProviderfee());
                } catch (Exception ex) {
                }
                try {
                    this.ship_fee = Float.valueOf(order.getShipfee());
                } catch (Exception ex) {
                }
                try {
                    this.surcharge_fee = Float.valueOf(order.getSurchargefee());
                } catch (Exception ex) {
                }
                try {
                    this.other_fee = Float.valueOf(order.getOtherfee());
                } catch (Exception ex) {
                }
                try {
                    this.deviverdate = order.getDeviverdate();
                }catch (Exception ex){}
                try {
                    this.description = order.getDescription();
                }catch (Exception ex){}
            }

            try {
                ((MyOrderDetailView_Buyer) this.orderDetailView).showBuyerUserInView(
                        Constants.USER.getAvatar(),
                        Constants.USER.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.orderDetailView.showOrderDetailInView(orderModelDataMapper.transform(order));
        }
    }

    public Order_Buyer getOrder() {
        return order;
    }
    //    private Offer_Buyer getProvider(Collection<Offer_Buyer> offers, String provider_id) {
//        for (Offer_Buyer offer : offers) {
//            if (provider_id.equals(offer.getProviderid())) {
//                return offer;
//            }
//        }
//        return null;
//    }

    public void setProvider(Offer_Buyer provider) {
        try {
            this.provider_fee = Float.valueOf(provider.getProviderfee());
        } catch (Exception ex) {
        }
        try {
            this.ship_fee = Float.valueOf(provider.getShipfee());
        } catch (Exception ex) {
        }
        try {
            this.surcharge_fee = Float.valueOf(provider.getSurchargefee());
        } catch (Exception ex) {
        }
        try {
            this.other_fee = Float.valueOf(provider.getOtherfee());
        } catch (Exception ex) {
        }
        try {
            this.deviverdate = provider.getDeviverdate();
        }catch (Exception ex){}
        try {
            this.description = provider.getDescription();
        }catch (Exception ex){}
    }

    public float getService_fee() {
        return service_fee;
    }

    public float getSale_tax_in_percent() {
        return sale_tax_in_percent;
    }

    public float getSale_tax_fee() {
        return sale_tax_fee;
    }

    public float getProvider_fee() {
        return provider_fee;
    }

    public float getShip_fee() {
        return ship_fee;
    }

    public float getSurcharge_fee() {
        return surcharge_fee;
    }

    public float getOther_fee() {
        return other_fee;
    }

    public float getAmount() {
        return amount;
    }

    public String getDeviverdate(){return deviverdate;}
    public String getDescription(){return description;}

    public float getTotal() {
        return amount + sale_tax_fee + service_fee + provider_fee + ship_fee + surcharge_fee + other_fee;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.orderDetailView = null;
    }

    private void showViewLoading() {
        this.orderDetailView.showLoading();
    }

    private void hideViewLoading() {
        this.orderDetailView.hideLoading();
    }

    private void showViewRetry() {
        this.orderDetailView.showRetry();
    }

    private void hideViewRetry() {
        this.orderDetailView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.orderDetailView.context(),
                errorBundle.getException());
        this.orderDetailView.showError(errorMessage);
    }

    public void loadOrderDetail() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getOrderDetail();
    }

    public void showProductDetail(ProductModel_Buyer productModel) {
        if (productModel != null) {
            navigateToProductDetail(productModelDataMapper.transform(productModel));
        }
    }

    private void getOrderDetail() {
        if (order != null) {
            showOrderInView(order);
        }
        hideViewLoading();
    }

    private void showOrderInView(Order_Buyer order) {
        if (order != null) {
            this.orderDetailView.showOrderDetailInView(orderModelDataMapper.transform(order));
        }
    }

    public void goBack() {
        if (this.orderDetailView.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) orderDetailView.activity()).goBack();
        }
    }

    public void gotoChatScreen() {
        this.navigateToChatScreen();
    }

    private void navigateToProductDetail(Product_Buyer product) {
        if (this.orderDetailView.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) orderDetailView.activity()).navigateToProductDetail(product);
        }
    }

    private void navigateToChatScreen() {
        if (this.order == null) {
            return;
        }
        if (this.orderDetailView.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) orderDetailView.activity()).navigateToChat(order.getOrder_info().getId(), Constants.USER.getId(), Constants.USER.getId(), order.getOrder_info().getAvatar());
        }
    }

    public void navigateToMakeOffer(Order_Buyer order_buyer) {
        if (this.orderDetailView.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) this.orderDetailView.activity()).navigateToMakeOffer_Buyer(order_buyer);
        }
    }

}
