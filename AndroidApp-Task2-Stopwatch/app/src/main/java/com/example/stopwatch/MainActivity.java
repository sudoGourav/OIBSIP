package com.example.stopwatch;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvTimer;

    Button btnStart, btnStop, btnReset;

    Handler handler = new Handler();

    long startTime = 0;
    long timeInMilliseconds = 0;
    long timeSwapBuff = 0;
    long updatedTime = 0;

    boolean isRunning = false;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            timeInMilliseconds =
                    System.currentTimeMillis() - startTime;

            updatedTime =
                    timeSwapBuff + timeInMilliseconds;

            int millis =
                    (int) (updatedTime % 1000) / 10;

            int secs =
                    (int) (updatedTime / 1000);

            int mins =
                    secs / 60;

            secs =
                    secs % 60;

            int hrs =
                    mins / 60;

            mins =
                    mins % 60;

            tvTimer.setText(
                    String.format(
                            "%02d:%02d:%02d.%02d",
                            hrs,
                            mins,
                            secs,
                            millis
                    )
            );

            handler.postDelayed(this, 10);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(v -> {

            if (!isRunning) {

                startTime = System.currentTimeMillis();

                handler.post(runnable);

                isRunning = true;
            }
        });


        btnStop.setOnClickListener(v -> {

            timeSwapBuff += timeInMilliseconds;

            handler.removeCallbacks(runnable);

            isRunning = false;
        });

        btnReset.setOnClickListener(v -> {

            handler.removeCallbacks(runnable);

            startTime = 0;
            timeInMilliseconds = 0;
            timeSwapBuff = 0;
            updatedTime = 0;

            isRunning = false;

            tvTimer.setText("00:00:00.00");
        });
    }
}