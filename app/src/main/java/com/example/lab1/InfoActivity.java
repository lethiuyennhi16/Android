package com.example.lab1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    TextView Ten, MSSV, Lop, SDT, Nam, PTBT, ChuyenNganh, Back;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Ten = findViewById(R.id.textHoTen);
        MSSV = findViewById(R.id.textMSSV);
        Lop = findViewById(R.id.textLop);
        SDT = findViewById(R.id.textsdt);
        Nam = findViewById(R.id.textnam);
        PTBT = findViewById(R.id.textPTBT);
        ChuyenNganh = findViewById(R.id.textchuyennganh);
        Back = findViewById(R.id.btnBack);
        img = findViewById(R.id.imageView);

        Bundle info = getIntent().getExtras();
        assert info != null;
        Ten.setText(info.getString("TEN"));
        MSSV.setText(info.getString("MSSV"));
        Lop.setText(info.getString("LOP"));
        SDT.setText(info.getString("SDT"));
        Nam.setText(String.valueOf(info.getInt("NAM")));
        ChuyenNganh.setText(info.getString("CHUYENNGANH"));
        PTBT.setText(info.getString("PTBT"));

        byte[] byteArray = info.getByteArray("IMAGE");
        if(byteArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img.setImageBitmap(bitmap);
        }

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(info);
                finish();
            }
        });
    }
}
