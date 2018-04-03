package com.fernandocejas.android10.order.presentation.presenter.buyer;

import android.support.annotation.NonNull;

import com.fernandocejas.android10.order.domain.buyer.Offer_Buyer;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;
import com.fernandocejas.android10.order.domain.interactor.buyer.GetMakeOffer_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.OrderModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.mapper.buyer.ProductModelDataMapper_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.utils.PreferencesUtility;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OfferMakeActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.OrderDetailActivity_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OfferMakeView_Buyer;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;

import javax.inject.Inject;

/**
 * Created by vandongluong on 3/28/18.
 */
@PerActivity
public class OfferMakePresenter_Buyer implements Presenter {

    private OfferMakeView_Buyer offerMakeViewBuyer;

    private final GetMakeOffer_Buyer getMakeOffer_buyer;
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

    @Inject
    public OfferMakePresenter_Buyer(GetMakeOffer_Buyer getMakeOffer_buyer,
                                    OrderModelDataMapper_Buyer orderModelDataMapper,
                                    ProductModelDataMapper_Buyer productModelDataMapper) {
        this.getMakeOffer_buyer = getMakeOffer_buyer;
        this.orderModelDataMapper = orderModelDataMapper;
        this.productModelDataMapper = productModelDataMapper;
    }

    public void setView(@NonNull OfferMakeView_Buyer view) {
        this.offerMakeViewBuyer = view;
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
            }

            try {
                ((OfferMakeView_Buyer) this.offerMakeViewBuyer).showOrderUserInView(
                        Constants.USER.getAvatar(),
                        Constants.USER.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.offerMakeViewBuyer.showOrderDetailInView(orderModelDataMapper.transform(order));
        }
    }

//    private Offer_Buyer getProvider(Collection<Offer_Buyer> offers, String provider_id) {
//        for (Offer_Buyer offer : offers) {
//            if (provider_id.equals(offer.getProviderid())) {
//                return offer;
//            }
//        }
//        return null;
//    }


    public Order_Buyer getOrder() {
        return order;
    }

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
        this.offerMakeViewBuyer = null;
    }

    private void showViewLoading() {
        this.offerMakeViewBuyer.showLoading();
    }

    private void hideViewLoading() {
        this.offerMakeViewBuyer.hideLoading();
    }

    private void showViewRetry() {
        this.offerMakeViewBuyer.showRetry();
    }

    private void hideViewRetry() {
        this.offerMakeViewBuyer.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.offerMakeViewBuyer.context(),
                errorBundle.getException());
        this.offerMakeViewBuyer.showError(errorMessage);
    }

    public void loadMakeOffer() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getMakeOffer();
    }

    public void EditMakeOffer(String orderid,String deviverdate,
                              String providerfee, String shipfee,
                              String surchargefee, String otherfee ,
                              String description) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getMakeOfferBuyer(orderid,deviverdate,providerfee,shipfee,surchargefee,otherfee,description);
    }
    public void showProductDetail(ProductModel_Buyer productModel) {
        if (productModel != null) {
            navigateToProductDetail(productModelDataMapper.transform(productModel));
        }
    }
    public void goBackClick() {
        goBack();
    }

    private void getMakeOffer() {
        if (order != null) {
            showOrderInView(order);
        }
        hideViewLoading();
    }

    private void showOrderInView(Order_Buyer order) {
        if (order != null) {
            this.offerMakeViewBuyer.showOrderDetailInView(orderModelDataMapper.transform(order));
        }
    }

    private void goBack() {
        if (this.offerMakeViewBuyer.activity() instanceof OfferMakeActivity_Buyer) {
            ((OfferMakeActivity_Buyer) offerMakeViewBuyer.activity()).goBackC();
        }
    }


    public void gotoChatScreen() {
        this.navigateToChatScreen();
    }

    private void navigateToProductDetail(Product_Buyer product) {
        if (this.offerMakeViewBuyer.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) offerMakeViewBuyer.activity()).navigateToProductDetail(product);
        }
    }

    private void navigateToChatScreen() {
        if (this.order == null) {
            return;
        }
        if (this.offerMakeViewBuyer.activity() instanceof OrderDetailActivity_Buyer) {
            ((OrderDetailActivity_Buyer) offerMakeViewBuyer.activity()).navigateToChat(order.getOrder_info().getId(), Constants.USER.getId(), Constants.USER.getId(), order.getOrder_info().getAvatar());
        }
    }


    private void getMakeOfferBuyer(String orderid,String deviverdate,
                                   String providerfee, String shipfee,
                                   String surchargefee, String otherfee ,
                                   String description) {
        String token = PreferencesUtility.getInstance(offerMakeViewBuyer.context())
                .readString(PreferencesUtility.PREF_TOKEN, null);
        // String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vNDUuNzkuMTMwLjIyMy9hcGlrb2RhL2J1eWVyL3NpZ25pbiIsImlhdCI6MTUyMjEyMzUzMCwiZXhwIjoxNTIyMTI3MTMwLCJuYmYiOjE1MjIxMjM1MzAsImp0aSI6Ijh3dmkxb3FFSFFMUXVLTlMiLCJzdWIiOjg4LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.7bKWgN7j3MPKnMEmYlh4exnF7wKuI7Girzb6sKsPulY";
        this.getMakeOffer_buyer.execute(new MakeOfferObserver(), GetMakeOffer_Buyer.Params.forMake(token, orderid,
                deviverdate,providerfee,shipfee,
                surchargefee,otherfee,description));
    }
    private final class MakeOfferObserver extends DefaultObserver<Offer_Buyer> {

        @Override
        public void onComplete() {
            OfferMakePresenter_Buyer.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            OfferMakePresenter_Buyer.this.hideViewLoading();
            OfferMakePresenter_Buyer.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            OfferMakePresenter_Buyer.this.showViewRetry();
        }

        @Override
        public void onNext(Offer_Buyer order) {
//            MakeOfferPresenter_Buyer.this.s(order);
            OfferMakePresenter_Buyer.this.setProvider(order);
        }
    }
}
