package com.exciter.androidbasic.widget.coordinatorlayout;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private List<Fragment> mFragments;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior, List<String> mTitles, List<Fragment> mFragments) {
        super(fm, behavior);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TextUtils.isEmpty(mTitles.get(position))?"":mTitles.get(position);
    }
}
