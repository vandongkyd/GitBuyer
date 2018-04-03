/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.fragment.buyer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ShipInfoModel_Buyer;
import com.fernandocejas.android10.order.presentation.presenter.buyer.OrderDetailPresenter_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Utils;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.ProductAdapter_Buyer;
import com.fernandocejas.android10.order.presentation.view.buyer.OrderDetailView_Buyer;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 *
 */
public abstract class BaseOrderDetailFragment_Buyer extends BaseFragment implements OrderDetailView_Buyer {

    @Inject
    OrderDetailPresenter_Buyer orderDetailPresenter;
    @Inject
    ProductAdapter_Buyer productAdapter;

    @Bind(R.id.v_description_padding)
    View v_description_padding;
    @Bind(R.id.tv_description)
    TextView tv_description;
    @Bind(R.id.tv_from)
    TextView tv_from;
    @Bind(R.id.tv_to)
    TextView tv_to;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.lyt_main)
    ViewGroup lyt_main;
    @Bind(R.id.rv_products)
    RecyclerView rv_products;
    @Bind(R.id.tv_order_number)
    TextView tv_order_number;

    //
    @Nullable
    @Bind(R.id.tv_base_total_items)
    TextView tv_base_total_items;
    @Nullable
    @Bind(R.id.tv_base_total_price)
    TextView tv_base_total_price;
    @Nullable
    @Bind(R.id.tv_base_sales_tax)
    TextView tv_base_sales_tax;
    @Nullable
    @Bind(R.id.tv_base_total_estimate)
    TextView tv_base_total_estimate;
    @Nullable
    @Bind(R.id.tv_base_sales_tax_title)
    TextView tv_base_sales_tax_title;

    private ProductAdapter_Buyer.OnItemClickListener onItemProductClickListener = new ProductAdapter_Buyer.OnItemClickListener() {

        @Override
        public void onItemClicked(ProductModel_Buyer productModel) {
            BaseOrderDetailFragment_Buyer.this.showProductDetail(productModel);
        }

        @Override
        public void onItemRemoved() {

        }

        @Override
        public void onLinkClicked(ProductModel_Buyer productModel) {
            BaseOrderDetailFragment_Buyer.this.onLinkClicked(productModel);
        }
    };

    public BaseOrderDetailFragment_Buyer() {
        setRetainInstance(true);
    }

    public abstract int getLayoutResId();

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
        final View fragmentView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecycleView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.orderDetailPresenter.setView(this);
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            Order_Buyer order_buyer = (Order_Buyer) arguments.getSerializable("args_order");
            this.orderDetailPresenter.setOrder(order_buyer);
            loadOrder();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.orderDetailPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.orderDetailPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.orderDetailPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.orderDetailPresenter = null;
        this.onItemProductClickListener = null;
    }

    @Override
    public void showLoading() {
        this.lyt_main.setVisibility(View.GONE);
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
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

    private void setupRecycleView() {
        //for product list
        productAdapter.setOnItemClickListener(onItemProductClickListener);
        this.rv_products.setLayoutManager(new LinearLayoutManager(context()));
        this.rv_products.setAdapter(productAdapter);
        ((SimpleItemAnimator) this.rv_products.getItemAnimator()).setSupportsChangeAnimations(false);

    }

    protected String getPriceWithSymbolCurrency(String amount, String currency) {
        String full_price = "";
        String symbol = Utils.getCurrencySymbol(currency);
        full_price = symbol + "" + Utils.formatDecimal(amount);
        return full_price;
    }

    private String getDeliveryTo(AddressModel_Buyer addressModel) {
        String deliverTo = "";
        String city = addressModel.getCity();
        String country = addressModel.getCountry();

        if (city != null) {
            deliverTo += city;
        }
        if (country != null) {
            if (!deliverTo.isEmpty()) {
                deliverTo += ", ";
            }
            deliverTo += country;
        }
        return deliverTo;
    }
    private String getDeliveryFrom(ShipInfoModel_Buyer shipInfoModelBuyer) {
        String deiverFrom = "";
        if (shipInfoModelBuyer == null){
            return deiverFrom;
        }

        String counname = shipInfoModelBuyer.getCountryName();
        String continame = shipInfoModelBuyer.getContinentName();
        if (counname != null) {
            deiverFrom += counname;
        }
        if (continame != null) {
            if (!deiverFrom.isEmpty()) {
                deiverFrom += " ";
            }
            deiverFrom += continame;
        }

        return deiverFrom;
    }

    private void loadOrder() {
        this.orderDetailPresenter.loadOrderDetail();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {

    }

    @Override
    public void onLinkClicked(ProductModel_Buyer productModel) {
        String link = productModel.getLink();
        if (link == null) {
            return;
        }
        Utils.openDefaultBrowser(activity(), link);
    }

    @Override
    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        orderDetailPresenter.goBack();
    }

    @Override
    public void showOrderNumberInView(String order_number) {
        tv_order_number.setText( order_number);
    }

    @Override
    public void showOrderDetailInView(OrderModel_Buyer orderModel) {

        this.productAdapter.setCurrency_default(orderModel.getCurrency());

        //show order number
        String order_number = orderModel.getId();
        showOrderNumberInView(order_number);

        //show order user
        showOrderUserInView(orderModel.getOrder_info().getAvatar(), orderModel.getOrder_info().getName());

        //show product list
        Collection<ProductModel_Buyer> productModels = orderModel.getProducts();
        if (productModels != null && !productModels.isEmpty()) {
            renderProductList(productModels);
        }

        //show description
        String description = orderModel.getDescription();
        showDescriptionInView(description);

        //show shipping info
        String deliveryFrom = getDeliveryFrom(orderModel.getShip_from_country());
        String deliveryTo = getDeliveryTo(orderModel.getAddress_Model_buyer());
        showShippingInfoInView(deliveryFrom, deliveryTo);
    }

    @Override
    public void renderProductList(Collection<ProductModel_Buyer> productModels) {
        productAdapter.setProductsCollection(productModels);
    }

    @Override
    public void showDescriptionInView(String description) {
        if (description == null || description.isEmpty()) {
            v_description_padding.setVisibility(View.GONE);
            tv_description.setVisibility(View.GONE);
        } else {
            v_description_padding.setVisibility(View.VISIBLE);
            tv_description.setVisibility(View.VISIBLE);
            tv_description.setText(description);
        }
    }

    @Override
    public void showShippingInfoInView(String deliveryFrom, String deliveryTo) {
        tv_from.setText(deliveryFrom);
        tv_to.setText(deliveryTo);
    }

    @Override
    public void showProductDetail(ProductModel_Buyer product) {
        this.orderDetailPresenter.showProductDetail(product);
    }

    @Override
    public void showTotalItemsInView(String value) {
        this.tv_base_total_items.setText(value);
    }

    @Override
    public void showTotalPriceInView(String value) {
        this.tv_base_total_price.setText(value);
    }

    @Override
    public void showSaleTaxInView(String value) {
        this.tv_base_sales_tax.setText(value);
    }

    @Override
    public void showEstimateTotalInView(String value) {
        this.tv_base_total_estimate.setText(value);
    }

    @Override
    public void showSaleTaxPercentInView(String value) {
        this.tv_base_sales_tax_title.setText(context().getString(R.string.order_sales_tax) + " - " + value + "%");
    }

    protected void loadImageFromUrl(Context context, ImageView view, String url, boolean isCircle, boolean hasDefault) {
        if (url == null || url.isEmpty()) {
            if (hasDefault) {
                //show default avatar if we don't have url to show
                Glide.with(context)
                        .load(R.drawable.default_avatar)
                        .asBitmap()
                        .into(new BitmapImageViewTarget(view) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                view.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }
            return;
        }
        if (isCircle) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } else {
            Glide.with(context)
                    .load(url)
                    .into(view);
        }
    }

}
