/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.GroupMessageModel;
import com.fernandocejas.android10.order.presentation.presenter.GroupMessagePresenter;
import com.fernandocejas.android10.order.presentation.utils.DialogFactory;
import com.fernandocejas.android10.order.presentation.view.GroupMessageView;
import com.fernandocejas.android10.order.presentation.view.adapter.GroupMessageAdapter;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 *
 */
public class GroupMessageFragment extends BaseFragment implements GroupMessageView {

    @Inject
    GroupMessagePresenter groupMessagePresenter;
    @Inject
    GroupMessageAdapter groupMessageAdapter;
    @Bind(R.id.rv_group_messages)
    RecyclerView rv_group_messages;

    private ProgressDialog progressDialog;
    private GroupMessageAdapter.OnItemClickListener onItemChatMessageClickListener = messageModel -> GroupMessageFragment.this.onItemGroupMessageClick(messageModel);

    public GroupMessageFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(OrderComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_group_message, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecycleView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.groupMessagePresenter.setView(this);
        if (savedInstanceState == null) {
            this.groupMessagePresenter.registerChatMessage();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.groupMessagePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.groupMessagePresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.groupMessagePresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.groupMessagePresenter = null;
    }

    @Override
    public void showLoading() {
        if (this.progressDialog == null) {
            this.progressDialog = DialogFactory.createProgressDialog(activity(), R.string.processing);
        }
        this.progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(activity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Activity activity() {
        return getActivity();
    }

    @Override
    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        this.groupMessagePresenter.goBack();
    }

    @Override
    public void renderGroupMessages(List<GroupMessageModel> groupMessageModels) {
        this.groupMessageAdapter.setGroupMessageModels(groupMessageModels);
    }

    @Override
    public void onItemGroupMessageClick(GroupMessageModel groupMessageModel) {
        this.groupMessagePresenter.navigateToChat(groupMessageModel.getOrderId(),
                groupMessageModel.getIdBuyer(), null);
    }


    private void setupRecycleView() {

        this.groupMessageAdapter.setOnItemClickListener(onItemChatMessageClickListener);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context());
        // linearLayoutManager.setReverseLayout(true); // setReverseLayout will change the order of the elements added by the Adapter
        // linearLayoutManager.setStackFromEnd(true);//setStackFromEnd will set the view to show the last element, the layout direction will remain the same
        this.rv_group_messages.setLayoutManager(linearLayoutManager);
        this.rv_group_messages.setAdapter(groupMessageAdapter);
    }

}
