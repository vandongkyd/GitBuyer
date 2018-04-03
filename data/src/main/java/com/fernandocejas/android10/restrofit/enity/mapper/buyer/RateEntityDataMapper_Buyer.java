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
package com.fernandocejas.android10.restrofit.enity.mapper.buyer;

import com.fernandocejas.android10.order.domain.buyer.Rate_Buyer;
import com.fernandocejas.android10.restrofit.enity.buyer.RateEntity_Buyer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RateEntityDataMapper_Buyer {

    @Inject
    RateEntityDataMapper_Buyer() {
    }

    public Rate_Buyer transform(RateEntity_Buyer rateEntity) {
        Rate_Buyer rate = null;
        if (rateEntity != null) {
            rate = new Rate_Buyer();
            rate.setStart(rateEntity.getStart());
            rate.setCount(rateEntity.getCount());
        }
        return rate;
    }

    public RateEntity_Buyer transform(Rate_Buyer rate) {
        RateEntity_Buyer rateEntity = null;
        if (rate != null) {
            rateEntity = new RateEntity_Buyer();
            rateEntity.setStart(rate.getStart());
            rateEntity.setCount(rate.getCount());
        }
        return rateEntity;
    }

}
