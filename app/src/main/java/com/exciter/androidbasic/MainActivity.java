package com.exciter.androidbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.exciter.androidbasic.recyclerview.RecyclerViewActivity;

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
                default:
                    break;
            }
        });
    }
}