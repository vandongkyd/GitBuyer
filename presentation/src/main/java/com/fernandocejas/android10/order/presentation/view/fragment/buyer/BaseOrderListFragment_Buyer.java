/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.fragment.buyer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.presenter.buyer.BaseOrderListPresenter_Buyer;
import com.fernandocejas.android10.order.presentation.utils.EndlessRecyclerViewScrollListener;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.OrderAdapter_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.BaseOrderListView_Buyer;
import com.fernandocejas.android10.order.presentation.view.dialog.buyer.SlideshowDialog_Buyer;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Orders.
 */
public abstract class BaseOrderListFragment_Buyer extends BaseFragment implements BaseOrderListView_Buyer {

    private String TAG = "OrderFeedListFragment_Buyer";

    @Inject
    OrderAdapter_Buyer orderAdapter;

    @Bind(R.id.rv_orders)
    RecyclerView rv_orders;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.scroll_main)
    NestedScrollView scroll_main;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @Bind(R.id.progress_load_more)
    ProgressBar progress_load_more;

    private SlideshowDialog_Buyer slideshowDialog;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    public BaseOrderListFragment_Buyer() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public abstract BaseOrderListPresenter_Buyer getPresenter();

    public abstract int getFragmentDetailType();

    public abstract void setView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(OrderComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_base_order_list_buyer, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        setupSwipeRefreshLayout();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setView();
        if (savedInstanceState == null) {

        }
        this.loadOrderList();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPresenter().resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getPresenter().pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_orders.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getPresenter().destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRefreshing() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderOrderList(Collection<OrderModel_Buyer> orderModelCollection, boolean isAppend) {
        if (orderModelCollection != null) {
            this.orderAdapter.setOrdersCollection(orderModelCollection, isAppend);
            if (!isAppend) {
                this.scrollListener.resetState();
            }
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
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
    public void loadMoreError() {
        this.scrollListener.onLoadError();
    }


    private OrderAdapter_Buyer.OnItemClickListener onOrderItemClickListener = new OrderAdapter_Buyer.OnItemClickListener() {
        @Override
        public void onOrderItemClicked(OrderModel_Buyer orderModel) {
            if (BaseOrderListFragment_Buyer.this.getPresenter() != null && orderModel != null) {
                BaseOrderListFragment_Buyer.this.getPresenter().onOrderClicked(orderModel, getFragmentDetailType());
            }
        }

        @Override
        public void showMoreImageClicked(Collection<ImageModel_Buyer> imageModels) {
            if (imageModels == null || imageModels.isEmpty()) {
                return;
            }
            if (slideshowDialog == null) {
                slideshowDialog = new SlideshowDialog_Buyer(activity());
            }
            slideshowDialog.setImageModels(imageModels);
            slideshowDialog.show();
        }
    };

    private void setupRecyclerView() {
        this.orderAdapter.setOnItemClickListener(onOrderItemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context());
        this.rv_orders.setLayoutManager(linearLayoutManager);
        this.rv_orders.setAdapter(orderAdapter);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, NestedScrollView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        scroll_main.setOnScrollChangeListener(scrollListener);
    }

    private void setupSwipeRefreshLayout() {
        swipe_refresh.setOnRefreshListener(() -> BaseOrderListFragment_Buyer.this.getPresenter().refreshOrderList());
    }

    /**
     * Loads all orders.
     */
    public void loadOrderList() {
        this.getPresenter().loadOrderList();
    }

    public void loadNextDataFromApi(int offset) {
        Log.d(TAG, "loadNextDataFromApi(" + offset + ")");
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
        this.getPresenter().loadMoreOrderList(offset + "");

    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        BaseOrderListFragment_Buyer.this.loadOrderList();
    }


    @Override
    public void onDismissClicked() {

    }

    @Override
    public void showLoadingMore() {
        progress_load_more.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingMore() {
        progress_load_more.setVisibility(View.INVISIBLE);
    }
}
