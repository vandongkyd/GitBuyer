package com.fernandocejas.android10.restrofit.repository.buyer;

import com.fernandocejas.android10.order.domain.buyer.Offer_Buyer;
import com.fernandocejas.android10.order.domain.repository.buyer.OfferRepository_Buyer;
import com.fernandocejas.android10.restrofit.enity.mapper.buyer.OfferEntityDataMapper_Buyer;
import com.fernandocejas.android10.restrofit.net.buyer.RetrofitHelper_Buyer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by vandongluong on 3/16/18.
 */
@Singleton
public class OfferDataRepository_Buyer implements OfferRepository_Buyer {

    private final RetrofitHelper_Buyer retrofitHelper;
    private final OfferEntityDataMapper_Buyer offerEntityDataMapper_buyer;

    @Inject
    OfferDataRepository_Buyer(RetrofitHelper_Buyer retrofitHelper,
                                     OfferEntityDataMapper_Buyer offerEntityDataMapper_buyer) {
        this.retrofitHelper = retrofitHelper;
        this.offerEntityDataMapper_buyer = offerEntityDataMapper_buyer;
    }

    @Override
    public Observable<Offer_Buyer> make_offer(String token,
                                              String orderid,
                                              String deviverdate,
                                              String providerfee,
                                              String shipfee,
                                              String surchargefee,
                                              String otherfee, String description) {
        return retrofitHelper
                .getRestApiService()
                .make_offer("Bearer " + token,
                        orderid,
                        deviverdate,
                        providerfee,
                        shipfee,
                        surchargefee,
                        otherfee,
                        description)
                .map(this.offerEntityDataMapper_buyer::transform);
    }
}
