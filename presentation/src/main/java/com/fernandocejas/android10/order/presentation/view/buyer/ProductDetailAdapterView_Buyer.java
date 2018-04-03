/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.ImageModel;
import com.fernandocejas.android10.order.presentation.model.ProductModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.ProductDetailAdapter;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.ProductDetailAdapter_Buyer;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface ProductDetailAdapterView_Buyer {

    void renderImageList(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, Collection<ImageModel_Buyer> imageModels);

    void showNameProductInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String name);

    void showDescriptionInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String description);

    void showLinkInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String link);

    void showQuantityInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String quantity, int stock);

    void showWeightInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String weight);

    void showPriceInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String price);

    void showWeightUnitInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String unit);

    void showCurrencySymbolInView(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, String symbol);

    void onPlusQuantityClicked(ProductModel_Buyer productModel, int position);

    void onMinusQuantityClicked(ProductModel_Buyer productModel, int position);

    void onWeightInputChanged(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, ProductModel_Buyer productModel);

    void onPriceInputChanged(ProductDetailAdapter_Buyer.ProductViewHolder viewHolder, ProductModel_Buyer productModel);

    void onLinkClicked(ProductModel_Buyer productModel);

    void onPickPhotoClicked(ProductModel_Buyer productModel, int position);

    void onShowSlideshowClicked(ProductModel_Buyer productModel);

}

