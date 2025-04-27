package com.example.lab5_2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    int[] images = {
            R.drawable.apple,
            R.drawable.apricot,
            R.drawable.banana,
            R.drawable.cherry,
            R.drawable.kiwi,
            R.drawable.lemon,
            R.drawable.mango,
            R.drawable.orange,
            R.drawable.peach,
            R.drawable.pear,
            R.drawable.strawberry
    };

    String[] names = {"Apple", "Apricot", "Banana", "Cherry", "Kiwi", "Lemon", "Mango", "Orange", "Peach", "Pear", "Strawberry"};
    String[] calo = {"52 Calories", "48 Calories", "89 Calories", "50 Calories", "70 Calories", "88 Calories", "23 Calories", "55 Calories", "34 Calories", "56 Calories", "74 Calories"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(this, names, images, calo);
        listview.setAdapter(adapter);
    }
}
