package com.exciter.androidbasic.widget.textinputlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.exciter.androidbasic.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class TextInputLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("TextInputLayout");
        toolbar.setNavigationOnClickListener(v -> finish());
        TextInputLayout inputPassword = findViewById(R.id.input_password);
        TextInputEditText etPassword = findViewById(R.id.et_password);
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            boolean validate = validatePassword(Objects.requireNonNull(etPassword.getText()).toString());
            if (!validate) {
                inputPassword.setErrorEnabled(true);
                inputPassword.setError("请输入6位以上密码");
            } else {
                inputPassword.setErrorEnabled(false);
            }
        });
    }

    private boolean validatePassword(String password) {
        return password.length() > 6;
    }
}