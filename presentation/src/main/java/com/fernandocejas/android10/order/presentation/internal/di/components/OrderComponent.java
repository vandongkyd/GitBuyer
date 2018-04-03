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
package com.fernandocejas.android10.order.presentation.internal.di.components;

import com.fernandocejas.android10.order.presentation.internal.di.modules.OrderModule;
import com.fernandocejas.android10.order.presentation.view.fragment.AcceptedFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.AccountInformationFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.ActivationFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.AddAddressFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.ChatMessageFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.GroupMessageFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.OrderDetailAcceptedFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.OrderDetailFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.OrderFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.OrderListFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.PaymentListFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.PhoneCodeListFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.ProductDetailFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.ProductListFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.SettingFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.SignInFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.SignUpFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.SplashScreenFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.TopWebsiteFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.VerifyPhoneFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.BaseOrderDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.BaseOrderListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.CompanyFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOrderListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOfferListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OfferMakeFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OrderFeedDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OrderFeedListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.ProductDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.SettingFragment_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.internal.di.components.ActivityComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.components.ApplicationComponent;
import com.fernandocejas.android10.sample.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects order specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, OrderModule.class})
public interface OrderComponent extends ActivityComponent {

    void inject(AccountInformationFragment accountInformationFragment);

    void inject(AddAddressFragment addAddressFragment);

    void inject(OrderDetailFragment orderDetailFragment);

    void inject(OrderDetailAcceptedFragment orderDetailAcceptedFragment);

    void inject(OrderListFragment orderListFragment);

    void inject(PaymentListFragment paymentListFragment);

    void inject(SettingFragment settingFragment);

    void inject(SignInFragment signInFragment);

    void inject(SignUpFragment signUpFragment);

    void inject(SplashScreenFragment splashScreenFragment);

    void inject(ActivationFragment activationFragment);

    void inject(VerifyPhoneFragment verifyPhoneFragment);

    void inject(PhoneCodeListFragment phoneCodeListFragment);

    void inject(TopWebsiteFragment topWebsiteFragment);

    void inject(ProductDetailFragment productDetailFragment);

    void inject(OrderFragment orderFragment);

    void inject(ProductListFragment productListFragment);

    void inject(AcceptedFragment acceptedFragment);

    void inject(ChatMessageFragment chatMessageFragment);

    void inject(BaseOrderListFragment_Buyer baseOrderListFragment_buyer);

    void inject(OrderFeedListFragment_Buyer orderFeedListFragment_buyer);

    void inject(MyOrderListFragment_Buyer myOrderListFragment_buyer);

    void inject(MyOfferListFragment_Buyer myOfferListFragment_buyer);

    void inject(BaseOrderDetailFragment_Buyer baseOrderDetailFragment_buyer);

    void inject(ProductDetailFragment_Buyer productDetailFragment_buyer);

    void inject(OrderFeedDetailFragment_Buyer orderFeedDetailFragment_buyer);

    void inject(CompanyFragment_Buyer companyFragment_buyer);

    void inject(GroupMessageFragment groupMessageFragment);

    void inject(SettingFragment_Buyer settingFragment_buyer);

    void inject(OfferMakeFragment_Buyer offerMakeFragment_buyer);

}
