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

import com.fernandocejas.android10.order.presentation.mapper.buyer.UserModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.view.activity.SettingActivity;
import com.fernandocejas.android10.order.presentation.view.buyer.SettingView_Buyer;
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
public class SettingPresenter_Buyer implements Presenter {

    private SettingView_Buyer settingView;
    private UserModelDataMapper_Buyer userModelDataMapper;

    @Inject
    public SettingPresenter_Buyer(UserModelDataMapper_Buyer userModelDataMapper) {
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull SettingView_Buyer view) {
        this.settingView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.settingView = null;
    }

    private void showViewLoading() {
        this.settingView.showLoading();
    }

    private void hideViewLoading() {
        this.settingView.hideLoading();
    }

    private void showViewRetry() {
        this.settingView.showRetry();
    }

    private void hideViewRetry() {
        this.settingView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.settingView.context(),
                errorBundle.getException());
        this.settingView.showError(errorMessage);
    }

    public void goBack() {
        navigateToOrderList();
    }

    public void loadUserInfo() {
        getUserInfo();
    }

    private void navigateToOrderList() {
        if (this.settingView.activity() instanceof SettingActivity) {
            ((SettingActivity) settingView.activity()).navigateToOrderList();
        }
    }

    private void getUserInfo() {
        this.settingView.showUserInfoInView(userModelDataMapper.transform(Constants.USER));
    }

}
