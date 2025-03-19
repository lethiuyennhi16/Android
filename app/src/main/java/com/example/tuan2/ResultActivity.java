package com.example.tuan2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);
        btnBack = findViewById(R.id.btnBack);

        Bundle bundle = getIntent().getExtras();
        double a = bundle.getDouble("a");
        double b = bundle.getDouble("b");
        double c = bundle.getDouble("c");

        String result = solveQuadratic(a, b, c);
        tvResult.setText(result);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                return c == 0 ? "Phương trình vô số nghiệm" : "Phương trình vô nghiệm";
            } else {
                return "Phương trình có nghiệm: x = " + (-c / b);
            }
        }

        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            return "Phương trình vô nghiệm";
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: x = " + x;
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có 2 nghiệm:\nx1 = " + x1 + "\nx2 = " + x2;
        }
    }
}
