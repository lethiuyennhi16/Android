package com.example.a07032025;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_linear_vertical);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextA = findViewById(R.id.editTextA);
        EditText editTextB = findViewById(R.id.editTextB);
        Button buttonSum = findViewById(R.id.buttonSum);
        TextView textViewResult = findViewById(R.id.textViewResult);
        Button buttonEquation = findViewById(R.id.buttonEquation);
        Button buttonClear = findViewById(R.id.buttonClear);

        buttonSum.setOnClickListener(view -> {
            double numberA = parseDoubleOrZero(editTextA.getText().toString());
            double numberB = parseDoubleOrZero(editTextB.getText().toString());

            double sum = numberA + numberB;
            textViewResult.setText("Result: " + sum);
        });

        buttonEquation.setOnClickListener(view -> {
            double a = parseDoubleOrZero(editTextA.getText().toString());
            double b = parseDoubleOrZero(editTextB.getText().toString());

            String result = solveEquation(a, b);
            textViewResult.setText("Result: " + result);
        });

        buttonClear.setOnClickListener(view -> {
            editTextB.setText("");
            editTextA.setText("");
            textViewResult.setText("");
        });
    }
    private double parseDoubleOrZero(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String solveEquation(double a, double b){
        if(a==0){
            if(b==0){
                return "Infinitely many solutions";
            }
            else{
                return "No solution";
            }
        }
        double x = -b/a;
        return "x = " + x;
    }
}
