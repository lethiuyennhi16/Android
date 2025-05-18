package com.example.lab1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText Ten, MSSV, Lop, SDT, PTBT;
    RadioButton[] Nam;
    CheckBox[] ChuyenNganh;
    Button goiDien, SMS, Gui, chupHinh, dssv;
    Bitmap bitmap;
    ImageView imageView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        // Khởi tạo DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        Ten = findViewById(R.id.Ten);
        MSSV = findViewById(R.id.edtMSSV);
        Lop = findViewById(R.id.edtLop);
        SDT = findViewById(R.id.edtsdt);
        PTBT = findViewById(R.id.PTBT);
        imageView = findViewById(R.id.imageView);
        Nam = new RadioButton[]{
                findViewById(R.id.Nam1),
                findViewById(R.id.Nam2),
                findViewById(R.id.Nam3),
                findViewById(R.id.Nam4)
        };

        ChuyenNganh = new CheckBox[]{
                findViewById(R.id.htn),
                findViewById(R.id.VT),
                findViewById(R.id.dt)
        };

        goiDien = findViewById(R.id.goidien);
        SMS = findViewById(R.id.sms);
        Gui = findViewById(R.id.gui);
        chupHinh = findViewById(R.id.chuphinh);
        dssv = findViewById(R.id.dssv);

        Gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = Ten.getText().toString();
                String mssv = MSSV.getText().toString();
                String lop = Lop.getText().toString();
                String sdt = SDT.getText().toString();
                String ptbt = PTBT.getText().toString();
                int nam = getNamHoc();
                String chuyennganh = getChuyenNganh();

                // Chuyển bitmap thành mảng byte (nếu có ảnh)
                byte[] image = null;
                if (bitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    image = stream.toByteArray();
                }

                // Lưu vào database
                boolean isInserted = databaseHelper.insertStudent(ten, mssv, lop, sdt, nam, chuyennganh, ptbt, image);
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Lưu dữ liệu thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Lưu dữ liệu thất bại!", Toast.LENGTH_SHORT).show();
                }

                // Chuyển sang InfoActivity
                Intent info = new Intent(MainActivity.this, InfoActivity.class);
                info.putExtra("TEN", ten);
                info.putExtra("MSSV", mssv);
                info.putExtra("LOP", lop);
                info.putExtra("SDT", sdt);
                info.putExtra("NAM", nam);
                info.putExtra("CHUYENNGANH", chuyennganh);
                info.putExtra("PTBT", ptbt);
                if (image != null) {
                    info.putExtra("IMAGE", image);
                }
                startActivity(info);
            }
        });

        goiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = SDT.getText().toString();
                Intent call = new Intent(MainActivity.this, CallActivity.class);
                call.putExtra("SDT", sdt);
                startActivity(call);
            }
        });

        SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + SDT.getText().toString()));
                startActivity(smsIntent);
            }
        });

        dssv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }
        chupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentphoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentphoto, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                bitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentphoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentphoto, 100);
            } else {
                Toast.makeText(this, "Ứng dụng cần quyền CAMERA để chụp ảnh!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getNamHoc() {
        for (int i = 0; i < Nam.length; i++) {
            if (Nam[i].isChecked()) {
                return i + 1;
            }
        }
        return 1;
    }

    private String getChuyenNganh() {
        String chuyennganh = "";
        for (int i = 0; i < ChuyenNganh.length; i++) {
            if (ChuyenNganh[i].isChecked()) {
                if (!chuyennganh.isEmpty()) {
                    chuyennganh += ", ";
                }
                chuyennganh += ChuyenNganh[i].getText().toString();
            }
        }
        return chuyennganh;
    }
}