package com.fernandocejas.android10.order.presentation.view.activity.buyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.DaggerOrderComponent;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OfferMakeFragment_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;

/**
 * Created by vandongluong on 3/29/18.
 */

public class OfferMakeActivity_Buyer extends BaseActivity implements HasComponent<OrderComponent> {

    public static Intent getCallingIntent(Context context, Order_Buyer order) {
        Intent intent = new Intent(context, OfferMakeActivity_Buyer.class);
        intent.putExtra("extra_order", order);
        return intent;
    }

//    public static Intent getCallingIntent(Context context) {
//        Intent intent = new Intent(context, OfferMakeActivity_Buyer.class);
////        intent.putExtra("extra_order", order);
//        return intent;
//    }
    private OrderComponent orderComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            Order_Buyer order = (Order_Buyer) getIntent().getSerializableExtra("extra_order");
            OfferMakeFragment_Buyer offerMakeFragment_buyer = new OfferMakeFragment_Buyer();
            Bundle arguments = new Bundle();
            arguments.putSerializable("args_order", order);
            offerMakeFragment_buyer.setArguments(arguments);
            addFragment(R.id.fragmentContainer, offerMakeFragment_buyer);

        }
    }
    private void initializeInjector() {
        this.orderComponent = DaggerOrderComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
    public void goBackC() {
        finish();
    }


    @Override
    public OrderComponent getComponent() {
        return orderComponent;
    }
}
