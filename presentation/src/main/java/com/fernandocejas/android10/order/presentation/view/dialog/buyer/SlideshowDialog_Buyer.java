package com.fernandocejas.android10.order.presentation.view.dialog.buyer;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.fernandocejas.android10.R;
import com.fernandocejas.android10.order.presentation.model.ImageModel;
import com.fernandocejas.android10.order.presentation.model.buyer.ImageModel_Buyer;
import com.fernandocejas.android10.order.presentation.view.adapter.SlideshowAdapter;
import com.fernandocejas.android10.order.presentation.view.adapter.buyer.SlideshowAdapter_Buyer;
import com.fernandocejas.android10.sample.presentation.dialog.BaseDialog;
import com.fernandocejas.android10.sample.presentation.view.activity.BaseActivity;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlideshowDialog_Buyer extends BaseDialog {

    @Inject
    SlideshowAdapter_Buyer slideshowAdapter;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_slideshow;
    }

    public SlideshowDialog_Buyer(Activity context) {
        super(context);
        (((BaseActivity) context).getApplicationComponent()).inject(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);
        setupViewPager();
    }

    public void setImageModels(Collection<ImageModel_Buyer> imageModels) {
        this.slideshowAdapter.replaceWith(imageModels);
    }

    private void setupViewPager() {
        viewpager.setAdapter(slideshowAdapter);
    }

    @OnClick(R.id.btn_close)
    public void onCloseClicked() {
        dismiss();
    }

}
