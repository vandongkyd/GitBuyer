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

import com.fernandocejas.android10.order.presentation.view.activity.buyer.CompanyActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OrderDetailActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.CompanyView_Buyer;
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
public class CompanyPresenter_Buyer implements Presenter {

    private CompanyView_Buyer companyViewBuyer;

    @Inject
    public CompanyPresenter_Buyer() {

    }

    public void setView(@NonNull CompanyView_Buyer view) {
        this.companyViewBuyer = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.companyViewBuyer = null;
    }

    private void showViewLoading() {
        this.companyViewBuyer.showLoading();
    }

    private void hideViewLoading() {
        this.companyViewBuyer.hideLoading();
    }

    private void showViewRetry() {
        this.companyViewBuyer.showRetry();
    }

    private void hideViewRetry() {
        this.companyViewBuyer.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.companyViewBuyer.context(),
                errorBundle.getException());
        this.companyViewBuyer.showError(errorMessage);
    }

    public void goBack() {
        if (this.companyViewBuyer.activity() instanceof CompanyActivity_Buyer) {
            ((CompanyActivity_Buyer) this.companyViewBuyer.activity()).handleBackPressed();
        }
    }

}
