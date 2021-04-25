package com.exciter.androidbasic.widget.floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.exciter.androidbasic.R;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("FloatingActionButton");
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}