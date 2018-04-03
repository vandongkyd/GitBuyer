package com.fernandocejas.android10.order.presentation.view.activity.buyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.domain.Offer;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.domain.buyer.Product_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.DaggerOrderComponent;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.BaseOrderDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOrderDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOfferDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OfferMakeFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OrderFeedDetailFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.ProductDetailFragment_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;

/**
 *
 *
 */

public class OrderDetailActivity_Buyer extends BaseActivity implements HasComponent<OrderComponent> {

    public static final int FRAGMENT_TYPE_FEEDS = 101;
    public static final int FRAGMENT_TYPE_MY_OFFERS = 102;
    public static final int FRAGMENT_TYPE_MY_ORDERS = 103;


    public static Intent getCallingIntent(Context context, Order_Buyer order, int type) {
        Intent intent = new Intent(context, OrderDetailActivity_Buyer.class);
        intent.putExtra("extra_order", order);
        intent.putExtra("extra_type", type);
        return intent;
    }

    private OrderComponent orderComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            Order_Buyer order = (Order_Buyer) getIntent().getSerializableExtra("extra_order");
            int type = getIntent().getIntExtra("extra_type", -1);
            BaseOrderDetailFragment_Buyer orderFragment = null;
            switch (type) {
                case FRAGMENT_TYPE_FEEDS:
                    orderFragment = new OrderFeedDetailFragment_Buyer();
                    break;
                case FRAGMENT_TYPE_MY_OFFERS:
                    orderFragment = new MyOfferDetailFragment_Buyer();
                    break;
                case FRAGMENT_TYPE_MY_ORDERS:
                    orderFragment = new MyOrderDetailFragment_Buyer();
                    break;
                default:
                    break;
            }
            if (orderFragment != null) {
                Bundle arguments = new Bundle();
                arguments.putSerializable("args_order", order);
                orderFragment.setArguments(arguments);
                addFragment(R.id.fragmentContainer, orderFragment);
            }
        }
    }

    private void initializeInjector() {
        this.orderComponent = DaggerOrderComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public OrderComponent getComponent() {
        return orderComponent;
    }

    /**
     * Goes to the order list screen.
     */
    public void goBack() {
        finish();
    }

    public void navigateToProductDetail(Product_Buyer product) {
        ProductDetailFragment_Buyer productDetailFragment = new ProductDetailFragment_Buyer();
        Bundle arguments = new Bundle();
        arguments.putSerializable("args_product", product);
        productDetailFragment.setArguments(arguments);
        addFragment(R.id.fragmentContainer, productDetailFragment, true);
    }

    public void navigateToAcceptedOffer(Offer offer,
                                        String quantity,
                                        String amount,
                                        String weight,
                                        String sale_tax,
                                        String service_fee,
                                        String currency) {
        this.navigator.navigateToAccepted(this, offer, quantity, amount, weight, sale_tax, service_fee, currency);

    }

    public void navigateToChat(String order_id,
                               String provider_id,
                               String user_id,
                               String provider_avatar) {
        this.navigator.navigateToChat(this, order_id, provider_id, user_id, provider_avatar);
    }
//    public void navigateToMakeOffer_Buyer() {
//        this.navigator.navigateToMakeOffer_Buyer(this);
//    }
    public void navigateToMakeOffer_Buyer(Order_Buyer order_buyer) {
        this.navigator.navigateToMakeOffer_Buyer(this, order_buyer);
    }

}
