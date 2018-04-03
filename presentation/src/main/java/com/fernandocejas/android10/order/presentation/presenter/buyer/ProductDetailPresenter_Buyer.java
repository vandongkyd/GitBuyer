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

import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.ImageModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.ProductModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OrderDetailActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.ProductDetailView_Buyer;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import java.util.Collection;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ProductDetailPresenter_Buyer implements Presenter {

    private ProductDetailView_Buyer productView;
    private final ProductModelDataMapper_Buyer productModelDataMapper;
    private final ImageModelDataMapper_Buyer imageModelDataMapper;

    private Product_Buyer product;

    @Inject
    public ProductDetailPresenter_Buyer(ProductModelDataMapper_Buyer productModelDataMapper,
                                        ImageModelDataMapper_Buyer imageModelDataMapper) {
        this.productModelDataMapper = productModelDataMapper;
        this.imageModelDataMapper = imageModelDataMapper;
    }

    public void setView(@NonNull ProductDetailView_Buyer view) {
        this.productView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.productView = null;
        this.product = null;
    }

    private void showViewLoading() {
        this.productView.showLoading();
    }

    private void hideViewLoading() {
        this.productView.hideLoading();
    }

    private void showViewRetry() {
        this.productView.showRetry();
    }

    private void hideViewRetry() {
        this.productView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.productView.context(),
                errorBundle.getException());
        this.productView.showError(errorMessage);
    }

    public void setProduct(Product_Buyer product) {
        this.product = product;
        this.showProductInView(product);
    }

    public Product_Buyer getProduct() {
        return product;
    }

    public String getLink() {
        if (product == null) {
            return null;
        }
        return product.getLink();
    }

    public Collection<ImageModel_Buyer> getImageModeList() {
        if (product == null) {
            return null;
        }
        if (product.getImages() == null || product.getImages().isEmpty()) {
            return null;
        }
        return imageModelDataMapper.transform(product.getImages());
    }

    private void showProductInView(Product_Buyer product) {
        if (product != null) {
            this.productView.showProductInView(productModelDataMapper.transform(product));
        }
    }

    public void goBack() {
        if (this.productView.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) this.productView.activity()).handleBackPressed();
        }
    }
}
