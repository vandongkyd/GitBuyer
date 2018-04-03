package com.fernandocejas.android10.order.presentation.view.fragment.buyer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.domain.buyer.Order_Buyer;
import com.fernandocejas.android10.order.presentation.internal.di.components.OrderComponent;
import com.fernandocejas.android10.order.presentation.model.buyer.OrderModel_Buyer;
import com.fernandocejas.android10.order.presentation.presenter.buyer.OfferMakePresenter_Buyer;
import com.fernandocejas.android10.order.presentation.utils.Utils;
import com.fernandocejas.android10.order.presentation.view.buyer.OfferMakeView_Buyer;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by vandongluong on 3/28/18.
 */

public class OfferMakeFragment_Buyer extends BaseFragment implements OfferMakeView_Buyer {
    @Inject
    OfferMakePresenter_Buyer makeOfferPresenterBuyer;


    @Bind(R.id.tv_order_number_make)
    TextView tv_order_number_make;
    @Bind(R.id.imv_make_offer_user_avatar)
    ImageView imv_make_offer_user_avatar;
    @Bind(R.id.ht_items_make)
    TextView ht_items_make;
    @Bind(R.id.ht_tolal_price_make)
    TextView ht_total_price_make;
    @Bind(R.id.tv_total_avg_make)
    TextView tv_total_avg_make;
    @Bind(R.id.ht_total_avg_make)
    TextView ht_total_avg_make;
    @Bind(R.id.ht_total_estimate_make)
    TextView ht_total_estimate_make;
    @Bind(R.id.ht_servicefee_make)
    TextView ht_servicefee_make;
    @Bind(R.id.ht_buyer_fee_make)
    EditText ht_buyer_fee_make;
    @Bind(R.id.ht_ship_fee_make)
    EditText ht_ship_fee_make;
    @Bind(R.id.ht_surcharge_fee_make)
    EditText ht_surcharge_fee_make;
    @Bind(R.id.ht_other_fee_make)
    EditText ht_other_fee_make;
    @Bind(R.id.tv_make_date)
    TextView tv_make_date;
    @Bind(R.id.spn_make_date)
    Spinner spn_make_date;
    @Bind(R.id.ed_add_description)
    EditText ed_add_description;
    @Bind(R.id.tv_submit_info)
    TextView tv_submit_info;
    @Bind(R.id.tv_buyer_fee_make)
    TextView tv_buyer_fee_make;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.lyt_main)
    ViewGroup lyt_main;


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
        final View fragmentView = inflater.inflate(R.layout.fragment_make_offer_buyer, container, false);
        ButterKnife.bind(this, fragmentView);
        //setupRecycleView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.makeOfferPresenterBuyer.setView(this);
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            Order_Buyer order_buyer = (Order_Buyer) arguments.getSerializable("args_order");
            this.makeOfferPresenterBuyer.setOrder(order_buyer);
            loadOrder();
            Sum();
        }
    }



    @Override
    public void showOrderUserInView(String avatar, String name) {
        showOrderUser_AvatarInView(avatar);
    }

    @Override
    public void showOrderUser_AvatarInView(String url) {
        loadImageFromUrl(context(), imv_make_offer_user_avatar, url, true, true);

    }

    @Override
    public void showOrderUser_NameInView(String name) {

    }

    @Override
    public void showOrderNumberInView(String order_number) {
        this.tv_order_number_make.setText(order_number);
    }

    @Override
    public void showOrderDetailInView(OrderModel_Buyer orderModel) {

        String order_number = orderModel.getId();
        showOrderNumberInView(order_number);
        // show item
        showTotalItemsInView(orderModel.getQuantity());
        //show total price
        String full_price = getPriceWithSymbolCurrency(makeOfferPresenterBuyer.getAmount() + "", orderModel.getCurrency());
        showTotalPriceInView(full_price);
        //show sale tax percent
        showSaleTaxPercentInView(makeOfferPresenterBuyer.getSale_tax_in_percent() + "");

        //show sales tax
        full_price = getPriceWithSymbolCurrency(makeOfferPresenterBuyer.getSale_tax_fee() + "", orderModel.getCurrency());
        showSaleTaxInView(full_price);
        //show estimate total
        full_price = getPriceWithSymbolCurrency(makeOfferPresenterBuyer.getTotal() + "", orderModel.getCurrency());
        showEstimateTotalInView(full_price);

        full_price = getPriceWithSymbolCurrency(makeOfferPresenterBuyer.getService_fee() + "", orderModel.getCurrency());
        showServiceFeeInView(full_price);

    }

    @Override
    public void showTotalItemsInView(String value) {
        this.ht_items_make.setText(value);
    }

    @Override
    public void showTotalPriceInView(String value) {
        this.ht_total_price_make.setText(value);
    }

    @Override
    public void showSaleTaxInView(String value) {
        this.ht_total_avg_make.setText(value);
    }

    @Override
    public void showEstimateTotalInView(String value) {
        this.ht_total_estimate_make.setText(value);
    }

    @Override
    public void showSaleTaxPercentInView(String value) {
        this.tv_total_avg_make.setText(context().getString(R.string.order_sales_tax) + " - " + value + "%");
    }

    @Override
    public void showServiceFeeInView(String value) {
        this.ht_servicefee_make.setText(value);
    }

    @Override
    public void onLayoutBuyerFeeClicked() {
        //ht_buyer_fee_make.requestFocus();
    }

    @Override
    public void onLayoutShipFeeClicked() {
        //ht_ship_fee_make.requestFocus();
    }

    @Override
    public void onLayoutSurchargeFeeClicked() {
        ht_surcharge_fee_make.requestFocus();
    }

    @Override
    public void onLayoutOtherFeeClicked() {
        ht_other_fee_make.requestFocus();
    }

    @Override
    public void onLayoutDesriptionClicked() {
        ed_add_description.requestFocus();
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
        this.lyt_main.setVisibility(View.VISIBLE);
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

    private void loadOrder() {
        this.makeOfferPresenterBuyer.loadMakeOffer();
    }

    ////
    protected String getPriceWithSymbolCurrency(String amount, String currency) {
        String full_price = "";
        String symbol = Utils.getCurrencySymbol(currency);
        full_price = symbol + "" + Utils.formatDecimal(amount);
        return full_price;
    }

    /////Load image User
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


    @Override
    @OnClick(R.id.rlt_submit_make)
    public void onOfferMakeClick() {
        String orderid = makeOfferPresenterBuyer.getOrder().getId();
        String dev = tv_make_date.getText().toString();
        String pro = ht_buyer_fee_make.getText().toString();
        String ship = ht_ship_fee_make.getText().toString();
        String sur = ht_surcharge_fee_make.getText().toString();
        String oth = ht_other_fee_make.getText().toString();
        String des = ed_add_description.getText().toString();
        if(orderid.equals("")||dev.equals("")||pro.equals("")||ship.equals("")||sur.equals("")||oth.equals("")||des.equals("")){
            Toast.makeText(activity(),"Please enter the parameters of Offer fee",Toast.LENGTH_SHORT).show();
        }else {
            this.makeOfferPresenterBuyer.EditMakeOffer(orderid, dev, pro, ship, sur, oth, des);
            Toast.makeText(activity(), "Make Offer Success ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    @OnClick(R.id.cmd_make_date)
    public void onPickerClick() {
        Calendar lich=Calendar.getInstance();
        int ngay=lich.get(Calendar.DAY_OF_MONTH);
        int thang=lich.get(Calendar.MONTH);
        int nam=lich.get(Calendar.YEAR);
        CalendarDatePickerDialogFragment cdp=new CalendarDatePickerDialogFragment();
        cdp.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int
                    monthOfYear, int dayOfMonth) {
                Toast.makeText(activity(), dayOfMonth + "/" + (monthOfYear + 1) + "/" + year,
                        Toast.LENGTH_LONG).show();
                tv_make_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        });
        cdp.setFirstDayOfWeek(Calendar.SUNDAY);
        cdp.setPreselectedDate(nam, thang, ngay);
        cdp.setDateRange(null,null);
        cdp.setThemeLight();
        cdp.show(getFragmentManager(),"tag1");
    }

    @Override
    @OnCheckedChanged(R.id.swtich_make)
    public void onChangeAVGClick(boolean isChanged) {
        if (isChanged){
           // double avg = (makeOfferPresenterBuyer.getAmount() * 8.49)/100;
            //ht_total_avg_make.setText(avg+"");
            Toast.makeText(activity(), "ON", Toast.LENGTH_SHORT).show();
        }else {
            //ht_total_avg_make.setText("0");
            Toast.makeText(activity(), "Off", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    @OnClick(R.id.btn_back)
    public void onBackClicked() {
       this.makeOfferPresenterBuyer.goBackClick();
    }
    public void Sum(){
        ht_buyer_fee_make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ht_ship_fee_make.getText().toString().equals("")== false&&
                        ht_surcharge_fee_make.getText().toString().equals("") == false&&
                        ht_other_fee_make.getText().toString().equals("")==false){

                    String ship=s.toString();
                    double so_a =0;
                    try {
                        so_a=Double.valueOf(ship);
                    }catch (Exception e) {
                    }
                    String ship_b=ht_ship_fee_make.getText().toString();
                    String ship_c= ht_surcharge_fee_make.getText().toString();
                    String ship_d = ht_other_fee_make.getText().toString();

                    double so_b=Double.valueOf(ship_b);
                    double so_c = Double.valueOf(ship_c);
                    double so_d = Double.valueOf(ship_d);

                    //thực hiện tính và đưa ra kết quả
                    double ketqua=so_a + so_b + so_c + so_d;
                    String full_price = getPriceWithDoubleCurrency(ketqua,makeOfferPresenterBuyer.getOrder().getCurrency());
                    tv_submit_info.setText("OFFER " + full_price + "");
                }
                else{
                    tv_submit_info.setText("OFFER "+"0.0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ht_ship_fee_make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ht_buyer_fee_make.getText().toString().equals("")== false&&
                        ht_surcharge_fee_make.getText().toString().equals("")== false&&
                        ht_other_fee_make.getText().toString().equals("")==false) {

                    String ship = s.toString();
                    double so_b =0;
                    try {
                        so_b=Double.valueOf(ship);
                    }catch (Exception e) {
                    }
                    String buyer_a = ht_buyer_fee_make.getText().toString();
                    String buyer_c = ht_surcharge_fee_make.getText().toString();
                    String buyer_d = ht_other_fee_make.getText().toString();

                    double so_a = Double.valueOf(buyer_a);
                    double so_c = Double.valueOf(buyer_c);
                    double so_d = Double.valueOf(buyer_d);

                    //thực hiện tính và đưa ra kết quả
                    double ketqua = so_a + so_b + so_c + so_d;
                    String full_price = getPriceWithDoubleCurrency(ketqua,makeOfferPresenterBuyer.getOrder().getCurrency());
                    tv_submit_info.setText("OFFER " + full_price + "");
                }
                else{
                    tv_submit_info.setText("OFFER "+"0.0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ht_surcharge_fee_make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(ht_buyer_fee_make.getText().toString().equals("")== false &&
                        ht_ship_fee_make.getText().toString().equals("") == false &&
                        ht_other_fee_make.getText().toString().equals("")==false) {

                    String surc = s.toString();
                    double so_c =0;
                    try {
                        so_c=Double.valueOf(surc);
                    }catch (Exception e) {
                    }
                    String surc_a = ht_buyer_fee_make.getText().toString();
                    String surc_b = ht_ship_fee_make.getText().toString();
                    String surc_d = ht_other_fee_make.getText().toString();

                    double so_a = Double.valueOf(surc_a);
                    double so_b = Double.valueOf(surc_b);
                    double so_d = Double.valueOf(surc_d);

                    //thực hiện tính và đưa ra kết quả
                    double ketqua = so_a + so_b + so_c + so_d;
                    String full_price = getPriceWithDoubleCurrency(ketqua,makeOfferPresenterBuyer.getOrder().getCurrency());
                    tv_submit_info.setText("OFFER " + full_price + "");
                }
                else{
                    tv_submit_info.setText("OFFER "+"0.0");
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ht_other_fee_make.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ht_buyer_fee_make.getText().toString().equals("") == false&&
                        ht_ship_fee_make.getText().toString().equals("") == false&&
                        ht_surcharge_fee_make.getText().toString().equals("")==false) {

                    String othe = s.toString();
                    double so_d =0;
                    try {
                        so_d=Double.valueOf(othe);
                    }catch (Exception e) {
                    }
                    String othe_a = ht_buyer_fee_make.getText().toString();
                    String othe_b = ht_ship_fee_make.getText().toString();
                    String othe_c = ht_surcharge_fee_make.getText().toString();

                    double so_a = Double.valueOf(othe_a);
                    double so_b = Double.valueOf(othe_b);
                    double so_c = Double.valueOf(othe_c);
                    //thực hiện tính và đưa ra kết quả

                    double ketqua = so_a + so_b + so_c + so_d;
                    String full_price = getPriceWithDoubleCurrency(ketqua,makeOfferPresenterBuyer.getOrder().getCurrency());
                    tv_submit_info.setText("OFFER " + full_price + "");
                }
                else{
                    tv_submit_info.setText("OFFER "+"0.0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    protected String getPriceWithDoubleCurrency(double amount, String currency) {
        String full_price = "";
        String symbol = Utils.getCurrencySymbol(currency);
        full_price = symbol + "" + amount;
        return full_price;
    }
}

