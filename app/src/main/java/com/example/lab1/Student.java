package com.example.lab1;

import android.graphics.Bitmap;

public class Student {
    private int id;
    private String ten;
    private String mssv;
    private String lop;
    private String sdt;
    private int nam;
    private String chuyenNganh;
    private String ptbt;
    private Bitmap image;

    public Student(int id, String ten, String mssv, String lop, String sdt, int nam, String chuyenNganh, String ptbt, Bitmap image) {
        this.id = id;
        this.ten = ten;
        this.mssv = mssv;
        this.lop = lop;
        this.sdt = sdt;
        this.nam = nam;
        this.chuyenNganh = chuyenNganh;
        this.ptbt = ptbt;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getMssv() {
        return mssv;
    }

    public String getLop() {
        return lop;
    }

    public String getSdt() {
        return sdt;
    }

    public int getNam() {
        return nam;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public String getPtbt() {
        return ptbt;
    }

    public Bitmap getImage() {
        return image;
    }
}