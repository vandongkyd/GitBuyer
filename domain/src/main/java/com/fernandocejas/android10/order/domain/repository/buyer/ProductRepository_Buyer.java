package com.fernandocejas.android10.order.domain.repository.buyer;

import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;

import io.reactivex.Observable;

/**
 * Created by vandongluong on 3/14/18.
 */

public interface ProductRepository_Buyer {
    Observable<Product_Buyer> parse(String token, String url);
}
