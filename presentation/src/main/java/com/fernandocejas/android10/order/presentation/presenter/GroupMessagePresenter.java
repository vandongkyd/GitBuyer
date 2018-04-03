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
package com.fernandocejas.android10.order.presentation.presenter;

import android.support.annotation.NonNull;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.model.GroupMessageModel;
import com.fernandocejas.android10.order.presentation.model.MessageModel;
import com.fernandocejas.android10.order.presentation.utils.Constants;
import com.fernandocejas.android10.order.presentation.utils.Utils;
import com.fernandocejas.android10.order.presentation.view.GroupMessageView;
import com.fernandocejas.android10.order.presentation.view.activity.buyer.HomeActivity_Buyer;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class GroupMessagePresenter implements Presenter {

    public static final String TAG = "GroupMessagePresenter";

    private GroupMessageView groupMessageView;
    private HashMap<String, Collection<MessageModel>> groupMessage;
    private ValueEventListener chatMessageValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            populateData(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Inject
    public GroupMessagePresenter() {

    }

    public void setView(@NonNull GroupMessageView view) {
        this.groupMessageView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.unregisterChatMessage();
        this.groupMessageView = null;
    }

    private void showViewLoading() {
        this.groupMessageView.showLoading();
    }

    private void hideViewLoading() {
        this.groupMessageView.hideLoading();
    }

    private void showViewRetry() {
        this.groupMessageView.showRetry();
    }

    private void hideViewRetry() {
        this.groupMessageView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.groupMessageView.context(),
                errorBundle.getException());
        this.groupMessageView.showError(errorMessage);
    }

    public void goBack() {
//        if (this.groupMessageView.activity() instanceof GroupMessageActivity) {
//            ((GroupMessageActivity) this.groupMessageView.activity()).goBack();
//        }
    }

    public void navigateToChat(String order_id, String provider_id, String provider_avatar) {
        if (this.groupMessageView.activity() instanceof HomeActivity_Buyer) {
            ((HomeActivity_Buyer) groupMessageView.activity()).navigateToChat(order_id, provider_id, Constants.USER.getId(), provider_avatar);
        }
    }

    public void renderGroupMessageList(List<GroupMessageModel> groupMessageModels) {
        try {
            if (groupMessageModels == null) {
                return;
            }
            if (this.groupMessageView != null) {
                this.groupMessageView.renderGroupMessages(groupMessageModels);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void registerChatMessage() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child("messages").addValueEventListener(chatMessageValueEventListener);
    }

    public void unregisterChatMessage() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.removeEventListener(chatMessageValueEventListener);
    }

    private void populateData(DataSnapshot dataSnapshot) {
        if (groupMessageView == null) {
            return;
        }
        groupMessage = new HashMap<>();
        //get all message and then group by to GROUP (hash map)
        for (DataSnapshot group_snapshot : dataSnapshot.getChildren()) {
            String key = group_snapshot.getKey();
            String[] senderOrReceiver = key.split("_");
            if (senderOrReceiver[1].equals(Constants.USER.getId()) ||
                    senderOrReceiver[2].equals(Constants.USER.getId())) {
                Collection<MessageModel> messageModels = groupMessage.get(key);
                if (messageModels == null) {
                    messageModels = new ArrayList<>();
                    groupMessage.put(key, messageModels);
                }
                try {
                    for (DataSnapshot message_snapshot : group_snapshot.getChildren()) {
                        MessageModel messageModel = message_snapshot.getValue(MessageModel.class);
                        messageModels.add(messageModel);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (groupMessage != null && !groupMessage.isEmpty()) {
            List<GroupMessageModel> groupMessageModels = new ArrayList<>();
            for (Collection<MessageModel> messageModels : groupMessage.values()) {
                //get last message
                MessageModel lastMessageModel = null;
                try {
                    lastMessageModel = (MessageModel) ((List) messageModels).get(messageModels.size() - 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //bind last message info to group
                if (lastMessageModel != null) {
                    GroupMessageModel groupMessageModel = new GroupMessageModel();
                    groupMessageModel.setAvatarBuyer(null);
                    String receiver_id = lastMessageModel.getReceiver() + "";
                    String sender_id = lastMessageModel.getSender() + "";
                    String buyer_id;
                    if (!receiver_id.equals(Constants.USER.getId())) {
                        buyer_id = receiver_id;
                    } else {
                        buyer_id = sender_id;
                    }
                    groupMessageModel.setIdBuyer(buyer_id);
                    groupMessageModel.setNameBuyer(null);
                    groupMessageModel.setOrderId(lastMessageModel.getOrder() + "");
                    groupMessageModel.setLastMessage(
                            (sender_id.equals(Constants.USER.getId()) ? groupMessageView.context().getString(R.string.group_chat_you) : "")
                                    + lastMessageModel.getMessage());
                    try {
                        groupMessageModel.setTimeLastMessage(Utils.getTimeAgos(lastMessageModel.getDatetime()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    groupMessageModels.add(groupMessageModel);
                }
            }
            GroupMessagePresenter.this.renderGroupMessageList(groupMessageModels);
        }
    }

}
