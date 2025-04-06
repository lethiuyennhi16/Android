package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnMul, btnDiv, btnDel, btnEqual, btnDot, btnRem;

    String operator = "";
    boolean decimalUsed = false;
    boolean isResultShown = false;
    StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        display = findViewById(R.id.textView);

        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        btnAdd = findViewById(R.id.buttonAdd);
        btnSub = findViewById(R.id.buttonSub);
        btnMul = findViewById(R.id.buttonMul);
        btnDiv = findViewById(R.id.buttonDiv);
        btnRem = findViewById(R.id.buttonRem);

        btnEqual = findViewById(R.id.buttonEqual);
        btnDel = findViewById(R.id.buttonDel);
        btnDot = findViewById(R.id.buttonDot);

        setNumberListener(btn0, "0");
        setNumberListener(btn1, "1");
        setNumberListener(btn2, "2");
        setNumberListener(btn3, "3");
        setNumberListener(btn4, "4");
        setNumberListener(btn5, "5");
        setNumberListener(btn6, "6");
        setNumberListener(btn7, "7");
        setNumberListener(btn8, "8");
        setNumberListener(btn9, "9");

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!decimalUsed) {
                    if (isResultShown) {
                        expression.setLength(0);
                        isResultShown = false;
                    }
                    expression.append(".");
                    display.setText(expression.toString());
                    decimalUsed = true;
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation("+");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation("-");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation("*");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation("/");
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperation("%");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parts = expression.toString().split(" ");
                if (parts.length >= 3 && parts.length % 2 == 1) {
                    try {
                        double result = Double.parseDouble(parts[0]);
                        for (int i = 1; i < parts.length; i += 2) {
                            String op = parts[i];
                            double b = Double.parseDouble(parts[i + 1]);

                            switch (op) {
                                case "+":
                                    result += b;
                                    break;
                                case "-":
                                    result -= b;
                                    break;
                                case "*":
                                    result *= b;
                                    break;
                                case "/":
                                    result = b != 0 ? result / b : 0;
                                    break;
                                case "%":
                                    result = result % b;
                                    break;
                            }
                        }
                        String resultStr = String.valueOf(result);
                        if (resultStr.endsWith(".0")) {
                            resultStr = resultStr.substring(0, resultStr.length() - 2);
                        }
                        display.setText(resultStr);
                        expression.setLength(0);
                        expression.append(resultStr);
                        decimalUsed = resultStr.contains(".");
                        isResultShown = true;
                        operator = "";
                    } catch (Exception e) {
                        display.setText("Error");
                        expression.setLength(0);
                        operator = "";
                        isResultShown = false;
                    }
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
                expression.setLength(0);
                operator = "";
                decimalUsed = false;
                isResultShown = false;
            }
        });
    }

    private void setNumberListener(Button btn, final String number) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResultShown) {
                    expression.setLength(0);
                    isResultShown = false;
                    decimalUsed = false;
                }
                expression.append(number);
                display.setText(expression.toString());
            }
        });
    }

    private void handleOperation(String op) {
        if (isResultShown) {
            isResultShown = false;
            expression.append(" ").append(op).append(" ");
        } else if (expression.length() != 0 && !endsWithOperator()) {
            expression.append(" ").append(op).append(" ");
        }
        display.setText(expression.toString());
        operator = op;
        decimalUsed = false;
    }

    private boolean endsWithOperator() {
        return expression.toString().trim().endsWith("+") ||
                expression.toString().trim().endsWith("-") ||
                expression.toString().trim().endsWith("*") ||
                expression.toString().trim().endsWith("/") ||
                expression.toString().trim().endsWith("%");
    }
}