package com.fernandocejas.android10.order.presentation.view.activity.buyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.DaggerOrderComponent;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.utils.widgets.WrapContentTabLayout;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.FragmentPagerAdapter_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.GroupMessageFragment;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOrderListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.MyOfferListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.OrderFeedListFragment_Buyer;
import com.fernandocejas.android10.order.presentation.view.fragment.buyer.SettingFragment_Buyer;
import com.fernandocejas.android10.sample.presentation.internal.di.HasComponent;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 *
 */

public class HomeActivity_Buyer extends BaseActivity implements HasComponent<OrderComponent> {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeActivity_Buyer.class);
    }

    private OrderComponent orderComponent;
    @Bind(R.id.tab_layout)
    WrapContentTabLayout tab_layout;
    @Bind(R.id.view_pager)
    ViewPager view_pager;

    private FragmentPagerAdapter_Buyer fragmentPagerAdapter;
    private List<BaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home_buyer);
        ButterKnife.bind(this);

        this.initializeInjector();
        if (savedInstanceState == null) {
            setupTabHost();
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
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

    private void loadFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new OrderFeedListFragment_Buyer());
        fragmentList.add(new MyOfferListFragment_Buyer());
        fragmentList.add(new MyOrderListFragment_Buyer());
        fragmentList.add(new GroupMessageFragment());
        fragmentList.add(new SettingFragment_Buyer());

        //
        fragmentPagerAdapter = new FragmentPagerAdapter_Buyer(getSupportFragmentManager());
        fragmentPagerAdapter.replaceWith(fragmentList);
    }

    private void setupTabHost() {
        loadFragmentList();
        //
        view_pager.setAdapter(fragmentPagerAdapter);
        tab_layout.setupWithViewPager(view_pager);
        try {
            invalidateTabs(
                    new int[]{
                            R.drawable.ic_feed_selector,
                            R.drawable.ic_my_offers_selector,
                            R.drawable.ic_my_orders_selector,
                            R.drawable.ic_message_selector,
                            R.drawable.ic_setting_selector
                    }
//                    new String[]{
//                            getString(R.string.feed),
//                            getString(R.string.my_offers),
//                            getString(R.string.my_orders),
//                            getString(R.string.home_messages),
//                            getString(R.string.home_setting),
//                    }
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void invalidateTabs(int[] icons, String[] text) throws Exception {
//        if (icons.length != text.length) {
//            throw new Exception("Invalid resource!");
//        }
//        for (int i = 0; i < icons.length; i++) {
//            tab_layout.getTabAt(i).setCustomView(
//                    createTabView(this,
//                            icons[i],
//                            text[i]
//                    )
//            );
//        }
//    }

    private void invalidateTabs(int[] icons) throws Exception {
        for (int i = 0; i < icons.length; i++) {
            tab_layout.getTabAt(i).setCustomView(
                    createTabView(this,
                            icons[i]
                    )
            );
        }
    }
//    private View createTabView(Context context, int icon, String text) {
//        View view = LayoutInflater.from(context).inflate(R.layout.row_tab_buyer, null, false);
//        //show icon
//        ImageView imv_icon = view.findViewById(R.id.imv_icon);
//        imv_icon.setImageResource(icon);
//        //show text
//        TextView tv_text = view.findViewById(R.id.tv_text);
//        tv_text.setText(text);
//        return view;
//    }

    private View createTabView(Context context, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_tab_buyer, null, false);
        //show icon
        ImageView imv_icon = view.findViewById(R.id.imv_icon);
        imv_icon.setImageResource(icon);
        //show text
//        TextView tv_text = view.findViewById(R.id.tv_text);
//        tv_text.setText(text);
        return view;
    }
    /**
     * Goes to the order list screen.
     */
    public void navigateToOrderList() {
        finish();
    }

    /**
     * Goes to the order detail screen.
     */
    public void navigateToOrderDetail_Buyer(Order_Buyer order_buyer, int type) {
        this.navigator.navigateToOrderDetail_Buyer(this, order_buyer, type);
    }

    public void navigateToChat(String order_id,
                               String provider_id,
                               String user_id,
                               String provider_avatar) {
        this.navigator.navigateToChat(this, order_id, provider_id, user_id, provider_avatar);
    }
}
