package com.exciter.androidbasic.widget.recyclerview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.exciter.androidbasic.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<ItemBean> mData = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            ItemBean bean = new ItemBean();
            bean.setTitle("item" + i);
            bean.setHeight((int) (100 + Math.random() * 300));
            mData.add(bean);
        }
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("RecyclerView");
        toolbar.setNavigationOnClickListener(v -> finish());
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        GridLayoutManager manager = new GridLayoutManager(this,4);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CustomGridItemDecoration(this, R.drawable.divider_custom));
//        recyclerView.addItemDecoration(new CustomLinearItemDecoration(this,
//                CustomLinearItemDecoration.VERTICAL_LIST, R.drawable.divider_custom, 10));
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mData);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条数据",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                new AlertDialog.Builder(RecyclerViewActivity.this)
                        .setTitle("确定删除这条数据吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定",
                                (dialog, which) -> mRecyclerViewAdapter.removeData(position)).show();
            }

            @Override
            public void onItemChildClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击了第" + position + "条数据的标题",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}