package com.fernandocejas.android10.order.domain.repository.buyer;

import com.fernandocejas.android10.order.domain.buyer.Offer_Buyer;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by vandongluong on 3/16/18.
 */

public interface OfferRepository_Buyer {

    Observable<Offer_Buyer> make_offer(String token,
                                       String orderid,
                                       String deviverdate,
                                       String providerfee,
                                       String shipfee,
                                       String surchargefee,
                                       String otherfee,
                                       String description);
}
