/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.ProductModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.ProductAdapter;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.ProductAdapter_Buyer;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface ProductAdapterView_Buyer {

    void showNameProductInView(ProductAdapter_Buyer.ItemBaseViewHolder viewHolder, String name);

    void showThumbnailInView(ProductAdapter_Buyer.ItemBaseViewHolder viewHolder, String url);

    void showLinkInView(ProductAdapter_Buyer.ItemBaseViewHolder viewHolder, String link);

    void showPriceInView(ProductAdapter_Buyer.ItemBaseViewHolder viewHolder, String price);

    void showQuantityInView(ProductAdapter_Buyer.ItemBaseViewHolder viewHolder, String quantity);

    void onItemClicked(ProductModel_Buyer productModel);

    void onItemRemoved(ProductModel_Buyer productModel, int position);

    void onLinkClicked(ProductModel_Buyer productModel);
}

