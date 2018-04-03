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
package com.fernandocejas.android10.order.presentation.mapper.buyer;

import com.fernandocejas.android10.order.domain.Address;
import com.fernandocejas.android10.order.domain.buyer.Address_Buyer;
import com.fernandocejas.android10.order.presentation.model.AddressModel;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class AddressModelDataMapper_Buyer {

    @Inject
    public AddressModelDataMapper_Buyer() {
    }

    public AddressModel_Buyer transform(Address_Buyer address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final AddressModel_Buyer addressModel_buyer = new AddressModel_Buyer();
        addressModel_buyer.setId(address.getId());
        addressModel_buyer.setAddress(address.getAddress());
        addressModel_buyer.setCity(address.getCity());
        addressModel_buyer.setState(address.getState());
        addressModel_buyer.setPostcode(address.getPostcode());
        addressModel_buyer.setCode(address.getCode());
        addressModel_buyer.setCountry(address.getCountry());
        return addressModel_buyer;
    }

    public Collection<AddressModel_Buyer> transform(Collection<Address_Buyer> addressCollection) {
        Collection<AddressModel_Buyer> addressModel_buyers;

        if (addressCollection != null && !addressCollection.isEmpty()) {
            addressModel_buyers = new ArrayList<>();
            for (Address_Buyer address_buyer : addressCollection) {
                addressModel_buyers.add(transform(address_buyer));
            }
        } else {
            addressModel_buyers = Collections.emptyList();
        }

        return addressModel_buyers;
    }

    public Address_Buyer transform(AddressModel_Buyer address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final Address_Buyer addressModel_buyer = new Address_Buyer();
        addressModel_buyer.setId(address.getId());
        addressModel_buyer.setAddress(address.getAddress());
        addressModel_buyer.setCity(address.getCity());
        addressModel_buyer.setState(address.getState());
        addressModel_buyer.setPostcode(address.getPostcode());
        addressModel_buyer.setCode(address.getCode());
        addressModel_buyer.setCountry(address.getCountry());
        return addressModel_buyer;
    }
    
}

