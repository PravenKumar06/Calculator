package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;

    // Define your quiz questions and answers
    private String[] questions = {
            "What is the capital of India?",
            "Who is the Prime Minister of India?",
            "Which city is called the Silicon Valley of India?"
    };

    private String[] correctAnswers = {"Delhi", "Narendra Modi", "Bangalore"};
    private String[] answerOptions = {
            "Delhi", "Bangalore",
            "Salman Khan", "Narendra Modi",
            "Bangalore", "Pune"
    };
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        // Set the first question
        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex < questions.length) {
            questionTextView.setText(questions[questionIndex]);

            // Clear previous selection
            answerRadioGroup.clearCheck();

            // Set answer choices (assuming you have RadioButtons with IDs option1RadioButton and option2RadioButton)
            RadioButton option1 = findViewById(R.id.option1RadioButton);
            RadioButton option2 = findViewById(R.id.option2RadioButton);

            option1.setText(answerOptions[questionIndex * 2]);
            option2.setText(answerOptions[questionIndex * 2 + 1]);

            // Update the current question index
            currentQuestionIndex = questionIndex;
        } else {
            // Quiz is finished
            Toast.makeText(this, "Quiz is finished!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer() {
        int selectedId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect.", Toast.LENGTH_SHORT).show();
            }

            // Move to the next question
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        }
    }
}
