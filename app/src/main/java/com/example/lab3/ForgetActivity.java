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

public class ForgetActivity extends AppCompatActivity {
    Button submit;
    EditText mail;
    TextView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        submit = findViewById(R.id.btnSubmit);
        mail = findViewById(R.id.edtForgetEmail);
        back = findViewById(R.id.tvBackToLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mail.getText().toString().isEmpty()) {
                    Toast.makeText(ForgetActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgetActivity.this, "Đã gửi yêu cầu khôi phục tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backLoginIntent = new Intent(ForgetActivity.this, MainActivity.class);
                startActivity(backLoginIntent);
                finish();
            }
        });
    }
}