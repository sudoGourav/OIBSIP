package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private TextView questionCounter;
    private TextView questionText;
    private TextView feedbackText;

    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button nextButton;

    private Button[] optionButtons;

    private final ArrayList<Question> questionList = new ArrayList<>();

    private int currentQuestion = 0;
    private int score = 0;
    private boolean answered = false;

    private final int defaultOptionColor = Color.parseColor("#1E293B");
    private final int correctColor = Color.parseColor("#16A34A");
    private final int wrongColor = Color.parseColor("#DC2626");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionCounter = findViewById(R.id.questionCounter);
        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        nextButton = findViewById(R.id.nextButton);

        optionButtons = new Button[]{
                option1,
                option2,
                option3,
                option4
        };

        loadQuestions();

        Collections.shuffle(questionList);

        showQuestion();

        option1.setOnClickListener(v -> checkAnswer(0));
        option2.setOnClickListener(v -> checkAnswer(1));
        option3.setOnClickListener(v -> checkAnswer(2));
        option4.setOnClickListener(v -> checkAnswer(3));

        nextButton.setOnClickListener(v -> nextQuestion());
    }

    private void loadQuestions() {

        questionList.add(new Question(
                "What does CPU stand for?",
                new String[]{
                        "Central Processing Unit",
                        "Computer Processing Unit",
                        "Central Program Utility",
                        "Computer Program Unit"
                },
                0
        ));

        questionList.add(new Question(
                "Which language is officially supported for Android development?",
                new String[]{
                        "Java",
                        "Kotlin",
                        "Both Java and Kotlin",
                        "Python"
                },
                2
        ));

        questionList.add(new Question(
                "What does RAM stand for?",
                new String[]{
                        "Random Access Memory",
                        "Read Access Memory",
                        "Rapid Access Module",
                        "Random Application Memory"
                },
                0
        ));

        questionList.add(new Question(
                "Which company developed Android?",
                new String[]{
                        "Apple",
                        "Microsoft",
                        "Google",
                        "IBM"
                },
                2
        ));

        questionList.add(new Question(
                "Which file is used to design Android layouts?",
                new String[]{
                        "Java",
                        "XML",
                        "Python",
                        "SQL"
                },
                1
        ));

        questionList.add(new Question(
                "What does HTML stand for?",
                new String[]{
                        "Hyper Text Markup Language",
                        "High Text Machine Language",
                        "Hyper Transfer Markup Language",
                        "Home Tool Markup Language"
                },
                0
        ));

        questionList.add(new Question(
                "Which database is commonly built into Android?",
                new String[]{
                        "MySQL",
                        "MongoDB",
                        "SQLite",
                        "Oracle"
                },
                2
        ));

        questionList.add(new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{
                        "function",
                        "define",
                        "class",
                        "object"
                },
                2
        ));

        questionList.add(new Question(
                "What does API stand for?",
                new String[]{
                        "Application Programming Interface",
                        "Application Program Internet",
                        "Advanced Programming Interface",
                        "Android Programming Integration"
                },
                0
        ));

        questionList.add(new Question(
                "Which Android component represents a single screen?",
                new String[]{
                        "Service",
                        "Activity",
                        "Broadcast",
                        "Database"
                },
                1
        ));
    }

    private void showQuestion() {

        answered = false;

        feedbackText.setText("");

        nextButton.setEnabled(false);

        Question question = questionList.get(currentQuestion);

        questionCounter.setText(
                "QUESTION " + (currentQuestion + 1)
                        + " OF " + questionList.size()
        );

        questionText.setText(question.getQuestion());

        String[] options = question.getOptions();

        for (int i = 0; i < optionButtons.length; i++) {

            optionButtons[i].setText(options[i]);

            optionButtons[i].setEnabled(true);

            optionButtons[i].setBackgroundTintList(
                    ColorStateList.valueOf(defaultOptionColor)
            );
        }
    }

    private void checkAnswer(int selectedAnswer) {

        if (answered) {
            return;
        }

        answered = true;

        Question question = questionList.get(currentQuestion);

        int correctAnswer = question.getCorrectAnswer();

        for (Button button : optionButtons) {
            button.setEnabled(false);
        }

        if (selectedAnswer == correctAnswer) {

            score++;

            optionButtons[selectedAnswer].setBackgroundTintList(
                    ColorStateList.valueOf(correctColor)
            );

            feedbackText.setText("Correct!");
            feedbackText.setTextColor(correctColor);

        } else {

            optionButtons[selectedAnswer].setBackgroundTintList(
                    ColorStateList.valueOf(wrongColor)
            );

            optionButtons[correctAnswer].setBackgroundTintList(
                    ColorStateList.valueOf(correctColor)
            );

            feedbackText.setText("Incorrect! Correct answer highlighted.");
            feedbackText.setTextColor(wrongColor);
        }

        nextButton.setEnabled(true);

        if (currentQuestion == questionList.size() - 1) {
            nextButton.setText("VIEW RESULTS");
        }
    }

    private void nextQuestion() {

        if (!answered) {
            return;
        }

        currentQuestion++;

        if (currentQuestion < questionList.size()) {

            showQuestion();

        } else {

            Intent intent = new Intent(
                    QuizActivity.this,
                    ResultActivity.class
            );

            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL", questionList.size());

            startActivity(intent);

            finish();
        }
    }
}