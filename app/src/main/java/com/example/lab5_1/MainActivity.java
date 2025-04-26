package com.example.lab5_1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    int[] images = {
            R.drawable.cupcake15,
            R.drawable.donut16,
            R.drawable.eclair21,
            R.drawable.froyo22,
            R.drawable.gingerbread23,
            R.drawable.honeycomb30
    };

    String[] names = {"Android Cupcake", "Android Donut", "Android Eclair", "Android Froyo", "Android Gingerbread", "Android Honeycomb"};
    String[] versions = {"1.5", "1.6", "2.1", "2.2", "2.3", "3.0"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.androidList);
        ListAdapter adapter = new ListAdapter(this, names, images, versions);
        listview.setAdapter(adapter);
    }
}
