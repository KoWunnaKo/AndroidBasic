package com.exciter.androidbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.exciter.androidbasic.cardview.CardViewActivity;
import com.exciter.androidbasic.coordinatorlayout.CoordinatorLayoutActivity;
import com.exciter.androidbasic.floatingactionbutton.FloatingActionButtonActivity;
import com.exciter.androidbasic.navigationview.NavigationViewActivity;
import com.exciter.androidbasic.notification.NotificationActivity;
import com.exciter.androidbasic.recyclerview.RecyclerViewActivity;
import com.exciter.androidbasic.snackbar.SnackBarActivity;
import com.exciter.androidbasic.tablayout.TabLayoutActivity;
import com.exciter.androidbasic.textinputlayout.TextInputLayoutActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<String> mData = new ArrayList<>();
    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mData.add("RecyclerView");
        mData.add("CardView");
        mData.add("Notification");
        mData.add("Snackbar");
        mData.add("TextInputLayout");
        mData.add("FloatingActionButton");
        mData.add("TabLayout");
        mData.add("NavigationView");
        mData.add("CoordinatorLayout");
        mHomeAdapter.notifyDataSetChanged();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHomeAdapter = new HomeAdapter(this, mData);
        recyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener((view, position) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, CardViewActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, SnackBarActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, TextInputLayoutActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(MainActivity.this, FloatingActionButtonActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(MainActivity.this, CoordinatorLayoutActivity.class));
                    break;
                default:
                    break;
            }
        });
    }
}