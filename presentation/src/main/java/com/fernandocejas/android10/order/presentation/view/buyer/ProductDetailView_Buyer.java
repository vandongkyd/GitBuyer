/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.ProductModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.ProductDetailAdapterView;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface ProductDetailView_Buyer extends ProductDetailAdapterView_Buyer, LoadDataView {

    void showOrderNumberInView(String order_number);

    void showProductInView(ProductModel_Buyer productModel);

    void onBackClicked();

    void onShowSlideshowClicked();

    void onLinkClicked();

}

