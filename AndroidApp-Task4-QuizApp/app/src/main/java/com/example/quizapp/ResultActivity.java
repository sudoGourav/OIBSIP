package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTitle;
    private TextView scoreText;
    private TextView correctText;
    private TextView incorrectText;

    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTitle = findViewById(R.id.resultTitle);
        scoreText = findViewById(R.id.scoreText);
        correctText = findViewById(R.id.correctText);
        incorrectText = findViewById(R.id.incorrectText);

        restartButton = findViewById(R.id.restartButton);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 10);

        int incorrect = total - score;

        scoreText.setText(score + " / " + total);
        correctText.setText(String.valueOf(score));
        incorrectText.setText(String.valueOf(incorrect));

        setResultTitle(score, total);

        restartButton.setOnClickListener(v -> restartQuiz());
    }

    private void setResultTitle(int score, int total) {

        double percentage = ((double) score / total) * 100;

        if (percentage >= 80) {

            resultTitle.setText("Excellent!");

        } else if (percentage >= 60) {

            resultTitle.setText("Great Job!");

        } else if (percentage >= 40) {

            resultTitle.setText("Good Try!");

        } else {

            resultTitle.setText("Keep Learning!");
        }
    }

    private void restartQuiz() {

        Intent intent = new Intent(
                ResultActivity.this,
                QuizActivity.class
        );

        startActivity(intent);

        finish();
    }
}