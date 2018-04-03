/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view;

import com.fernandocejas.android10.order.presentation.view.adapter.GroupMessageAdapter;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface GroupMessageAdapterView {

    void showAvatarInView(GroupMessageAdapter.GroupMessageViewHolder viewHolder, String url);

    void showLastMessageTimeInView(GroupMessageAdapter.GroupMessageViewHolder viewHolder, String text);

    void showBuyerNameInView(GroupMessageAdapter.GroupMessageViewHolder viewHolder, String text);

    void showOrderIdInView(GroupMessageAdapter.GroupMessageViewHolder viewHolder, String text);

    void showLastMessageContentInView(GroupMessageAdapter.GroupMessageViewHolder viewHolder, String text);

}

