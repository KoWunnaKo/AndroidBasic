package com.exciter.androidbasic.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;

import com.exciter.androidbasic.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle("TabLayout");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //TabLayout的tabIndicatorColor取得是colorPrimary的色值，在xml中单独设置颜色无效
        /*
         * <com.google.android.material.tabs.TabLayout
         *     ...
         *     app:tabIndicatorColor="?colorPrimary"
         * />
         */
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        titles.add("Android");
        titles.add("Java");
        titles.add("kotlin");
        titles.add("Flutter");
        titles.add("Dart");
        titles.add("iOS");
        titles.add("Swift");
        titles.add("PHP");
        titles.add(".Net");
        titles.add("小程序");
        titles.add("HTML");
        titles.add("JavaScript");
        titles.add("Css");

        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
            fragments.add(new ListFragment());
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //ViewPager设置适配器
        mViewPager.setAdapter(fragmentAdapter);
        //关联TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        //TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapter);

    }
}