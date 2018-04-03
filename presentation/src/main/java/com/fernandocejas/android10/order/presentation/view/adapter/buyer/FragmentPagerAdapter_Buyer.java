package com.fernandocejas.android10.order.presentation.view.adapter.buyer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fernandocejas.android10.order.presentation.utils.FragmentItemIdStatePagerAdapter;
import com.fernandocejas.android10.sample.presentation.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 3/23/2017.
 */

public class FragmentPagerAdapter_Buyer extends FragmentItemIdStatePagerAdapter {

    private List<BaseFragment> mList;

    public FragmentPagerAdapter_Buyer(FragmentManager fm) {
        super(fm);
        mList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void replaceWith(Collection<BaseFragment> newValues) {
        this.mList.clear();
        this.mList.addAll(newValues);
        notifyDataSetChanged();
    }
}