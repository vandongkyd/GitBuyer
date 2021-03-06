/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view;

import com.fernandocejas.android10.order.presentation.model.GroupMessageModel;
import com.fernandocejas.android10.sample.presentation.view.LoadDataView;

import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
public interface GroupMessageView extends LoadDataView {

    void onBackClicked();

    void renderGroupMessages(List<GroupMessageModel> groupMessageModels);

    void onItemGroupMessageClick(GroupMessageModel groupMessageModel);

}

