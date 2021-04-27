package com.exciter.androidbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.exciter.androidbasic.http.HttpActivity;
import com.exciter.androidbasic.http.volley.VolleyActivity;
import com.exciter.androidbasic.widget.cardview.CardViewActivity;
import com.exciter.androidbasic.widget.coordinatorlayout.CoordinatorLayoutActivity;
import com.exciter.androidbasic.widget.floatingactionbutton.FloatingActionButtonActivity;
import com.exciter.androidbasic.widget.navigationview.NavigationViewActivity;
import com.exciter.androidbasic.widget.notification.NotificationActivity;
import com.exciter.androidbasic.widget.recyclerview.RecyclerViewActivity;
import com.exciter.androidbasic.widget.snackbar.SnackBarActivity;
import com.exciter.androidbasic.widget.tablayout.TabLayoutActivity;
import com.exciter.androidbasic.widget.textinputlayout.TextInputLayoutActivity;

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
        mData.add("Http");
        mData.add("Http:volley");
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
                case 9:
                    startActivity(new Intent(MainActivity.this, HttpActivity.class));
                    break;
                case 10:
                    startActivity(new Intent(MainActivity.this, VolleyActivity.class));
                    break;
                default:
                    break;
            }
        });
    }
}