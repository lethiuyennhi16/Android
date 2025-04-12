package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText pass;
    EditText conf_pass;
    TextView back;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        username = findViewById(R.id.edtRegisterUsername);
        email = findViewById(R.id.edtRegisterEmail);
        pass = findViewById(R.id.edtRegisterPassword);
        conf_pass = findViewById(R.id.edtConfirmPassword);
        back = findViewById(R.id.tvGoToLogin);
        register = findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username == null || email == null || pass == null || conf_pass == null) {
                    Toast.makeText(RegisterActivity.this, "Lỗi giao diện, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                String usernameText = username.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passText = pass.getText().toString().trim();
                String confPassText = conf_pass.getText().toString().trim();
                if (usernameText.isEmpty() || emailText.isEmpty() || passText.isEmpty() || confPassText.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!passText.equals(confPassText)) {
                    Toast.makeText(RegisterActivity.this, "Xác nhận mật khẩu sai", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Đã đăng ký", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(backIntent); // Thêm startActivity để chuyển về MainActivity
                finish();
            }
        });
    }
}