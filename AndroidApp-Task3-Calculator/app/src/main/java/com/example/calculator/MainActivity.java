package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay, tvExpression;

    double firstNumber = 0;
    String operator = "";
    boolean isNewNumber = false;

    private void appendNumber(String number) {

        if (isNewNumber) {
            tvDisplay.setText(number);
            isNewNumber = false;
            return;
        }

        if (tvDisplay.getText().toString().equals("0")) {
            tvDisplay.setText(number);
        } else {
            tvDisplay.append(number);
        }
    }
    private double calculate(double first, double second, String op) {

        switch (op) {
            case "+":
                return first + second;

            case "-":
                return first - second;

            case "×":
                return first * second;

            case "÷":
                if (second == 0) {
                    return 0;
                }
                return first / second;
        }

        return second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        tvExpression = findViewById(R.id.tvExpression);

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);

        Button btnDot = findViewById(R.id.btnDot);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        Button btnEquals = findViewById(R.id.btnEquals);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnBackspace = findViewById(R.id.btnBackspace);

        // Number Buttons
        btn0.setOnClickListener(v -> appendNumber("0"));
        btn1.setOnClickListener(v -> appendNumber("1"));
        btn2.setOnClickListener(v -> appendNumber("2"));
        btn3.setOnClickListener(v -> appendNumber("3"));
        btn4.setOnClickListener(v -> appendNumber("4"));
        btn5.setOnClickListener(v -> appendNumber("5"));
        btn6.setOnClickListener(v -> appendNumber("6"));
        btn7.setOnClickListener(v -> appendNumber("7"));
        btn8.setOnClickListener(v -> appendNumber("8"));
        btn9.setOnClickListener(v -> appendNumber("9"));

        // Decimal Point
        btnDot.setOnClickListener(v -> {
            String text = tvDisplay.getText().toString();

            if (isNewNumber) {
                tvDisplay.setText("0.");
                isNewNumber = false;
            } else if (!text.contains(".")) {
                tvDisplay.append(".");
            }
        });

        // Clear
        btnClear.setOnClickListener(v -> {
            tvDisplay.setText("0");
            tvExpression.setText("");
            firstNumber = 0;
            operator = "";
            isNewNumber = false;
        });

        // Backspace
        btnBackspace.setOnClickListener(v -> {
            String text = tvDisplay.getText().toString();

            if (text.length() > 1) {
                tvDisplay.setText(text.substring(0, text.length() - 1));
            } else {
                tvDisplay.setText("0");
            }
        });

        // Plus
        btnPlus.setOnClickListener(v -> {
            firstNumber = Double.parseDouble(tvDisplay.getText().toString());
            operator = "+";
            tvExpression.setText(tvDisplay.getText().toString() + " +");
            isNewNumber = true;
        });

        // Minus
        btnMinus.setOnClickListener(v -> {
            firstNumber = Double.parseDouble(tvDisplay.getText().toString());
            operator = "-";
            tvExpression.setText(tvDisplay.getText().toString() + " -");
            isNewNumber = true;
        });

        // Multiply
        btnMultiply.setOnClickListener(v -> {
            firstNumber = Double.parseDouble(tvDisplay.getText().toString());
            operator = "×";
            tvExpression.setText(tvDisplay.getText().toString() + " ×");
            isNewNumber = true;
        });

        // Divide
        btnDivide.setOnClickListener(v -> {
            firstNumber = Double.parseDouble(tvDisplay.getText().toString());
            operator = "÷";
            tvExpression.setText(tvDisplay.getText().toString() + " ÷");
            isNewNumber = true;
        });

        // Equals
        btnEquals.setOnClickListener(v -> {

            double secondNumber =
                    Double.parseDouble(tvDisplay.getText().toString());

            double result = 0;

            switch (operator) {

                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "-":
                    result = firstNumber - secondNumber;
                    break;

                case "×":
                    result = firstNumber * secondNumber;
                    break;

                case "÷":
                    if (secondNumber == 0) {
                        tvDisplay.setText("Error");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            String first =
                    (firstNumber == (long) firstNumber)
                            ? String.valueOf((long) firstNumber)
                            : String.valueOf(firstNumber);

            String second =
                    (secondNumber == (long) secondNumber)
                            ? String.valueOf((long) secondNumber)
                            : String.valueOf(secondNumber);

            tvExpression.setText(first + " " + operator + " " + second);

            if (result == (long) result) {
                tvDisplay.setText(String.valueOf((long) result));
            } else {
                tvDisplay.setText(String.valueOf(result));
            }

            isNewNumber = true;
        });
    }
}