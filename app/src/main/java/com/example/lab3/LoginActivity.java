package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView username;
    Button logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        username = findViewById(R.id.tvWelcomeUser);
        logout = findViewById(R.id.btnLogout);

        Bundle login = getIntent().getExtras();
        if (login != null) {
            username.setText(login.getString("NAME"));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });
    }
}