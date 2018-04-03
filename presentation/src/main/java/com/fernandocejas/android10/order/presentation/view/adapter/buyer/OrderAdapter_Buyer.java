/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.order.presentation.view.adapter.buyer;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.model.buyer.AddressModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OfferModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ProductModel_Buyer;
import com.fernandocejas.android10.order.presentation.model.buyer.ShipInfoModel_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Utils;
import com.fernandocejas.android10.order.presentation.view.buyer.OrderAdapterView_Buyer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderAdapter_Buyer extends RecyclerView.Adapter<OrderAdapter_Buyer.OrderViewHolder> implements OrderAdapterView_Buyer {

    public static final int TYPE_FEEDS = 101;
    public static final int TYPE_MY_OFFERS = 102;
    public static final int TYPE_MY_ORDERS = 103;

    public interface OnItemClickListener {
        void onOrderItemClicked(OrderModel_Buyer orderModel);

        void showMoreImageClicked(Collection<ImageModel_Buyer> imageModels);
    }

    private List<OrderModel_Buyer> ordersCollection;

    private OnItemClickListener onItemClickListener;

    private Context context;
    private LayoutInflater layoutInflater;

    private int adapter_type;

    @Inject
    OrderAdapter_Buyer(Context context) {
        this.context = context;
        this.ordersCollection = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.ordersCollection != null) ? this.ordersCollection.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return adapter_type;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.layoutInflater == null) {
            this.layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = null;
        if (viewType == TYPE_MY_OFFERS) {
            view = this.layoutInflater.inflate(R.layout.row_order_my_offers_buyer, parent, false);
        } else if (viewType == TYPE_MY_ORDERS) {
            view = this.layoutInflater.inflate(R.layout.row_order_my_orders_buyer, parent, false);
        } else {
            view = this.layoutInflater.inflate(R.layout.row_order_feed_buyer, parent, false);
        }
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, final int position) {
        final OrderModel_Buyer orderModel = this.ordersCollection.get(position);
        //show offer list
        Collection<OfferModel_Buyer> offerModels = orderModel.getOffers();
        renderOfferList(holder, offerModels, orderModel.getStatus(), orderModel.getProviderid());
        //show image list
        Collection<ImageModel_Buyer> imageModels = getImageModelList(orderModel.getProducts());
        renderImageList(holder, imageModels);
        //show description
        String description = getDescription(orderModel.getProducts());
        showDescriptionInView(holder, description);
        //show shipping info
        //String deliveryFrom = orderModel.getCountry_name();

        String deliveryFrom = getDeliveryFrom(orderModel.getShip_from_country());

        String deliveryTo = getDeliveryTo(orderModel.getAddress_Model_buyer());
        showShippingInfoInView(holder, deliveryFrom, deliveryTo);
        //show price
        String full_price = getPriceWithSymbolCurrency(orderModel.getAmount(), orderModel.getCurrency());
        showPriceInView(holder,full_price);
        //show order date
        String order_date = getOrderDate(orderModel.getCreated_at());
        showOrderDateInView(holder, order_date);
        //show ship fee
        float provider_fee = getProviderFee(orderModel.getProviderid(), orderModel.getOffers());
        String full_ship_price = getPriceWithSymbolCurrency(
                getShipFeeAmount(provider_fee + "", /*orderModel.getServicefee()*/"0") + "",
                orderModel.getCurrency()
        );
        showShipFeeInView(holder, full_ship_price, orderModel.getStatus());
        //show delivery date
        String delivery_date = getDeliveryDate(orderModel.getDeviverdate());
        showDeliveryDateInView(holder, delivery_date, orderModel.getStatus());
        //show status
        showStatusInView(holder, orderModel.getStatus());
        //action events
        holder.itemView.setOnClickListener(v -> {
            onOrderItemClicked(orderModel);
        });
        holder.cmd_show_image.setOnClickListener(v -> {
            onItemClickListener.showMoreImageClicked(imageModels);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOrdersCollection(Collection<OrderModel_Buyer> ordersCollection, boolean isAppend) {
        this.validateOrdersCollection(ordersCollection);
        if (!isAppend) {
            this.ordersCollection = (List<OrderModel_Buyer>) ordersCollection;
            this.notifyDataSetChanged();
        } else {
            int item_count = getItemCount();
            this.ordersCollection.addAll(ordersCollection);
            this.notifyItemRangeInserted(item_count, getItemCount() - item_count);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setAdapter_type(int adapter_type) {
        this.adapter_type = adapter_type;
    }

    private void validateOrdersCollection(Collection<OrderModel_Buyer> ordersCollection) {
        if (ordersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    private String getOrderDate(String date) {
        return Utils.getTimeAgos(date);
    }

    private String getDeliveryDate(String date) {
        return Utils.getTimeByFormat(date);
    }

    private String getPriceWithSymbolCurrency(String amount, String currency) {
        String full_price = "";
        String symbol = Utils.getCurrencySymbol(currency);
        full_price = symbol + "" + Utils.formatDecimal(amount);
        return full_price;
    }

    private List<ImageModel_Buyer> getImageModelList(Collection<ProductModel_Buyer> productModelList) {
        List<ImageModel_Buyer> imageModelList = new ArrayList<>();
        for (ProductModel_Buyer productModel : productModelList) {
            imageModelList.addAll(productModel.getImages());
        }
        return imageModelList;
    }

    private String getDescription(Collection<ProductModel_Buyer> productModelList) {
        String description = "";
        for (ProductModel_Buyer productModel : productModelList) {
            String name = productModel.getName();
            if (name != null & !name.isEmpty()) {
                if (!description.isEmpty()) {
                    description += "\n";
                }
                description += name;
            }
        }
        return description;
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

    private String getProviderLogo(String provider_id, Collection<OfferModel_Buyer> offerModels) {
        for (OfferModel_Buyer offerModel : offerModels) {
            if (offerModel.getProviderid().equals(provider_id)) {
                return offerModel.getLogo();
            }
        }
        return null;
    }

    private void loadImageFromUrl(Context context, ImageView view, String url, boolean isCircle, boolean hasDefault) {
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

    private float getShipFeeAmount(String providerFee, String serviceFee) {
        float fee = 0;
        try {
            fee += Float.valueOf(providerFee);
        } catch (Exception ex) {
        }
        try {
            fee += Float.valueOf(serviceFee);
        } catch (Exception ex) {
        }
        return fee;
    }

    private float getProviderFee(String provider_id, Collection<OfferModel_Buyer> offerModelList) {
        if (offerModelList == null) {
            return 0;
        }
        float fee = 0;
        for (OfferModel_Buyer offerModel : offerModelList) {
            if (offerModel.getProviderid().equals(provider_id)) {
                float providerfee = 0, shipfee = 0, surchargefee = 0, otherfee = 0;
                try {
                    providerfee = Float.valueOf(offerModel.getProviderfee());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    shipfee = Float.valueOf(offerModel.getShipfee());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    surchargefee = Float.valueOf(offerModel.getSurchargefee());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    otherfee = Float.valueOf(offerModel.getOtherfee());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                fee = providerfee + shipfee + surchargefee + otherfee;
                break;
            }
        }
        return fee;
    }

    @Override
    public void renderOfferList(OrderAdapter_Buyer.OrderViewHolder viewHolder, Collection<OfferModel_Buyer> offerModels, String status, String provider_id) {
        if (offerModels == null || status == null) {
            return;
        }
        if (status.equals("0")) {
            // trạng thái mới tạo thì kiểm tra tiếp offers
            //Danh sách offer nó là image lấy từ field logo của offer trường hợp nhiều hơn 2 offer thì hiển thị + theo sau hình thứ 3 như design, nếu ko có offer như design
            if (offerModels.size() > 2) {
                //show more
                viewHolder.tv_offer_more.setVisibility(View.VISIBLE);
            } else {
                //hide text view more offer
                viewHolder.tv_offer_more.setVisibility(View.INVISIBLE);
            }
            if (offerModels.size() <= 0) {//don't have offer
                //show dont have offer
                viewHolder.tv_no_offer.setVisibility(View.VISIBLE);
                //hide offers list
                viewHolder.rlt_offers.setVisibility(View.GONE);

            } else {
                //hide dont have offer view
                viewHolder.tv_no_offer.setVisibility(View.GONE);
                //show offers list
                viewHolder.rlt_offers.setVisibility(View.VISIBLE);
                //show logo in offer list
                for (int i = 0; i < viewHolder.imv_offer.length; i++) {
                    if (i >= offerModels.size()) {
                        //we hide all view if, no more logo to show
                        viewHolder.imv_offer[i].setVisibility(View.GONE);
                    } else {
                        String logo = null;
                        try {
                            logo = ((List<OfferModel_Buyer>) offerModels).get(i).getLogo();
                        } catch (Exception ex) {
                        }
                        loadImageFromUrl(context, viewHolder.imv_offer[i], logo, true, true);
                    }
                }
            }
            //hide offer cancel
            viewHolder.tv_offer_cancel.setVisibility(View.GONE);
            //show 2 column
//            viewHolder.lyt_with_none_offer.setVisibility(View.VISIBLE);
//            viewHolder.lyt_with_offer.setVisibility(View.GONE);
        } else if (status.equals("1") || status.equals("2") || status.equals("3") || status.equals("4")) {
            //Nếu status = 1, 2,3,4 là 1-accepted, 2-ordered, 3 shipped, 4-done thì hiện dưới là button Accepted và ở trên là provider
            //reset offer view
            for (View view : viewHolder.imv_offer) {
                view.setVisibility(View.GONE);
            }
            //show provider logo
            viewHolder.imv_offer[0].setVisibility(View.VISIBLE);
            loadImageFromUrl(context, viewHolder.imv_offer[0], getProviderLogo(provider_id, offerModels), true, true);
            //hide text view offer more
            viewHolder.tv_offer_more.setVisibility(View.INVISIBLE);
            //hide  don't have offer view
            viewHolder.tv_no_offer.setVisibility(View.GONE);
            //show offers list
            viewHolder.rlt_offers.setVisibility(View.VISIBLE);
            //hide offer cancel
            viewHolder.tv_offer_cancel.setVisibility(View.GONE);
            //show 4 column
//            viewHolder.lyt_with_none_offer.setVisibility(View.GONE);
//            viewHolder.lyt_with_offer.setVisibility(View.VISIBLE);
        } else if (status.equals("5")) {
            //Status = 5 hiện Cancel
            //show offer cancel
            viewHolder.tv_offer_cancel.setVisibility(View.VISIBLE);
            //hide text view offer more
            viewHolder.tv_offer_more.setVisibility(View.INVISIBLE);
            //hide  don't have offer view
            viewHolder.tv_no_offer.setVisibility(View.GONE);
            //hide offers list
            viewHolder.rlt_offers.setVisibility(View.GONE);
            //show 2 column
//            viewHolder.lyt_with_none_offer.setVisibility(View.VISIBLE);
//            viewHolder.lyt_with_offer.setVisibility(View.GONE);
        }
    }

    @Override
    public void renderImageList(OrderAdapter_Buyer.OrderViewHolder viewHolder, Collection<ImageModel_Buyer> imageModels) {

        int listImageSize = imageModels.size();

        if (listImageSize <= 3) {
            if (listImageSize <= 0) {
                viewHolder.v_description_padding.setVisibility(View.GONE);
            } else {
                viewHolder.v_description_padding.setVisibility(View.VISIBLE);
            }
            //hide show more
            viewHolder.tv_image_more.setVisibility(View.GONE);

            for (int i = 0; i < viewHolder.imv_product_images.length; i++) {
                if (i >= listImageSize) {
                    //we have no more image to show
                    viewHolder.layout_imv[i].setVisibility(View.GONE);
                } else {
                    viewHolder.layout_imv[i].setVisibility(View.VISIBLE);
                    //
                    String image_url = null;
                    try {
                        image_url = ((List<ImageModel_Buyer>) imageModels).get(i).getImage();
                    } catch (Exception ex) {
                    }
                    loadImageFromUrl(context, viewHolder.imv_product_images[i], image_url, false, false);
                }
            }
        } else {

            //reset view
            for (int i = 0; i < viewHolder.layout_imv.length; i++) {
                viewHolder.layout_imv[i].setVisibility(View.VISIBLE);
            }

            //show show more
            viewHolder.tv_image_more.setVisibility(View.VISIBLE);
            viewHolder.tv_image_more.setText("+" + (listImageSize - 2));

            //get image
            String image_url_1, image_url_2, image_url_3;
            image_url_1 = ((List<ImageModel_Buyer>) imageModels).get(0).getImage();
            image_url_2 = ((List<ImageModel_Buyer>) imageModels).get(1).getImage();
            image_url_3 = ((List<ImageModel_Buyer>) imageModels).get(2).getImage();
            loadImageFromUrl(context, viewHolder.imv_product_images[0], image_url_1, false, false);
            loadImageFromUrl(context, viewHolder.imv_product_images[1], image_url_2, false, false);
            loadImageFromUrl(context, viewHolder.imv_product_images[2], image_url_3, false, false);
        }
    }

    @Override
    public void showDescriptionInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String description) {
        viewHolder.tv_description.setText(description);
    }



    @Override
    public void showShippingInfoInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String deliveryFrom, String deliveryTo) {
        viewHolder.tv_from.setText(deliveryFrom);
        viewHolder.tv_to.setText(deliveryTo);
    }

    @Override
    public void showShipFeeInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String price, String status) {
        if(viewHolder.ln_ship_fee == null || viewHolder.tv_ship_fee == null){
            return;
        }
        if (status.equals("1") || status.equals("2") || status.equals("3") || status.equals("4")) {
            viewHolder.ln_ship_fee.setVisibility(View.VISIBLE);
            viewHolder.tv_ship_fee.setText(price);
        } else {
            viewHolder.ln_ship_fee.setVisibility(View.VISIBLE);
            viewHolder.tv_ship_fee.setText(price);
        }
    }

    @Override
    public void showDeliveryDateInView(OrderViewHolder viewHolder, String date, String status) {
        if(viewHolder.ln_time_receive == null || viewHolder.tv_time_receive == null){
            return;
        }
        if (status.equals("1") || status.equals("2") || status.equals("3") || status.equals("4")) {
            viewHolder.ln_time_receive.setVisibility(View.VISIBLE);
            viewHolder.tv_time_receive.setText(date);
        } else {
            viewHolder.ln_time_receive.setVisibility(View.VISIBLE);
            viewHolder.tv_time_receive.setText(null);
        }
    }

    @Override
    public void showPriceInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String price) {
        viewHolder.tv_cash.setText(price);
    }

    @Override
    public void showOrderDateInView(OrderViewHolder viewHolder, String date_ago) {
        viewHolder.tv_order_time.setText(date_ago);
    }

    @Override
    public void showStatusInView(OrderAdapter_Buyer.OrderViewHolder viewHolder, String status) {
       if(viewHolder.imv_status == null){
           return;
       }
        viewHolder.imv_status.setVisibility(View.VISIBLE);
        switch (status) {
            case "0":
                viewHolder.imv_status.setImageResource(R.drawable.sts_1);
                break;
            case "1":
                viewHolder.imv_status.setImageResource(R.drawable.sts_2);
                break;
            case "2":
                viewHolder.imv_status.setImageResource(R.drawable.sts_3);
                break;
            case "3":
                viewHolder.imv_status.setImageResource(R.drawable.sts_4);
                break;
            case "4":
                viewHolder.imv_status.setImageResource(R.drawable.sts_5);
                break;
            case "5":
                viewHolder.imv_status.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onOrderItemClicked(OrderModel_Buyer orderModel) {
        if (OrderAdapter_Buyer.this.onItemClickListener != null) {
            OrderAdapter_Buyer.this.onItemClickListener.onOrderItemClicked(orderModel);
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cmd_show_image)
        LinearLayout cmd_show_image;
        @Bind({R.id.imv_1, R.id.imv_2, R.id.imv_3})
        ImageView[] imv_product_images;
        @Bind(R.id.tv_description)
        TextView tv_description;
        @Bind(R.id.tv_from)
        TextView tv_from;
        @Bind(R.id.tv_to)
        TextView tv_to;
        @Bind(R.id.tv_no_offer)
        TextView tv_no_offer;
        @Bind({R.id.imv_offer_1, R.id.imv_offer_2})
        ImageView[] imv_offer;
        @Bind(R.id.tv_offer_more)
        TextView tv_offer_more;
        @Bind(R.id.rlt_offers)
        RelativeLayout rlt_offers;
        @Bind(R.id.tv_cancel)
        TextView tv_offer_cancel;
        @Bind(R.id.tv_image_more)
        TextView tv_image_more;
        @Bind({R.id.layout_imv_1, R.id.layout_imv_2, R.id.layout_imv_3})
        FrameLayout[] layout_imv;
        @Nullable
        @Bind(R.id.imv_status)
        ImageView imv_status;
        @Bind(R.id.v_description_padding)
        View v_description_padding;
        //cash
        @Bind(R.id.tv_cash)
        TextView tv_cash;
        //order time
        @Bind(R.id.tv_order_time)
        TextView tv_order_time;
        //ship
        @Nullable
        @Bind(R.id.ln_ship_fee)
        LinearLayout ln_ship_fee;
        @Nullable
        @Bind(R.id.tv_ship_fee)
        TextView tv_ship_fee;
        //time
        @Nullable
        @Bind(R.id.ln_time_receive)
        LinearLayout ln_time_receive;
        @Nullable
        @Bind(R.id.tv_time_receive)
        TextView tv_time_receive;

        OrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
