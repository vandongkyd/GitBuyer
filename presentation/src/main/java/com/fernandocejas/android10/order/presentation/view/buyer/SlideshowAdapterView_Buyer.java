/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.buyer;

import com.fernandocejas.android10.order.presentation.view.adapter.buyer.SlideshowAdapter_Buyer;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface SlideshowAdapterView_Buyer {

    void showImageInView(SlideshowAdapter_Buyer.ViewHolder viewHolder, String url);

}

