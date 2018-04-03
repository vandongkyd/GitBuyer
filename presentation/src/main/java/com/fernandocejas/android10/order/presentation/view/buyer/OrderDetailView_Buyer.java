/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface OrderDetailView_Buyer extends LoadDataView {

    void onLinkClicked(ProductModel_Buyer productModel);

    void onBackClicked();

    //

    void showOrderNumberInView(String order_number);

    void showOrderDetailInView(OrderModel_Buyer orderModel);

    void showOrderUserInView(String avatar, String name);

    void showOrderUser_AvatarInView(String url);

    void showOrderUser_NameInView(String name);

    void renderProductList(Collection<ProductModel_Buyer> productModels);

    void showShippingInfoInView(String deliveryFrom, String deliveryTo);

    void showDescriptionInView(String description);

    void showProductDetail(ProductModel_Buyer product);

    //

    void showTotalItemsInView(String value);
    void showTotalPriceInView(String value);
    void showSaleTaxInView(String value);
    void showEstimateTotalInView(String value);
    void showSaleTaxPercentInView(String value);

}

