package com.example.lab1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import  android.Manifest;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallActivity extends AppCompatActivity {
    EditText sdt;
    Button back, goi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);

        sdt = findViewById(R.id.sdt);
        back = findViewById(R.id.buttonBack);
        goi = findViewById(R.id.buttonCall);
        Bundle extras = getIntent().getExtras();
        sdt.setText(extras.getString("SDT"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(CallActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        goi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = sdt.getText().toString();
                if(!phoneNumber.isEmpty()){
                    Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));

                    if(ActivityCompat.checkSelfPermission(CallActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CallActivity.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
                    }else {
                        startActivity(call);
                    }
                }
            }
        });
    }
}
