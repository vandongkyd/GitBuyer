/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.domain.Address;
import com.fernandocejas.android10.order.domain.buyer.Address_Buyer;
import com.fernandocejas.android10.order.presentation.model.AddressModel;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.UserModel_Buyer;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface SettingView_Buyer extends LoadDataView{

    void onBackClicked();

    void showUserInfoInView(UserModel_Buyer userModel_buyer);
    void showAvatarInView(String url);
    void showNameInView(String name);
    void showPhoneInView(String phone);
    void showEmailInView(String email);

}

