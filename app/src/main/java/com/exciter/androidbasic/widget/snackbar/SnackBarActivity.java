package com.exciter.androidbasic.widget.snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.exciter.androidbasic.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarActivity extends AppCompatActivity {

    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        mRootView = findViewById(R.id.root_view);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Snackbar");
        toolbar.setNavigationOnClickListener(v -> finish());
        findViewById(R.id.btn_snack_bar).setOnClickListener(v -> showSnackBar());
    }

    private void showSnackBar() {
        Snackbar.make(mRootView, "这是一个Snackbar", Snackbar.LENGTH_SHORT)
                //设置文本颜色
                .setTextColor(getResources().getColor(android.R.color.holo_orange_dark))
                //设置一个Action
                .setAction("click", v -> Toast.makeText(SnackBarActivity.this, "点击了Snackbar", Toast.LENGTH_SHORT).show())
                //设置Action按钮文本颜色
                .setActionTextColor(getResources().getColor(R.color.white))
                //显示Snackbar
                .show();
    }
}