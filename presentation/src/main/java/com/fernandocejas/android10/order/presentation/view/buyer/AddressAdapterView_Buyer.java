/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.model.AddressModel;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.AddressAdapter;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.AddressAdapter_Buyer;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface AddressAdapterView_Buyer {

    void showAddressInView(AddressAdapter_Buyer.AddressViewHolder viewHolder, AddressModel_Buyer addressModel);

}

