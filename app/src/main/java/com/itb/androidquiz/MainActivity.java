package com.itb.androidquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView questionText, progressText;
    Button trueButton, falseButton;
    ProgressBar progressBar;
    AlertDialog.Builder alertBuilder;

    int contador = 0;

    static final QuestionModel[] questionBank = {
            new QuestionModel(R.string.q1, false),
            new QuestionModel(R.string.q2, true),
            new QuestionModel(R.string.q3, false),
            new QuestionModel(R.string.q4, false),
            new QuestionModel(R.string.q5, false),
            new QuestionModel(R.string.q6, true),
            new QuestionModel(R.string.q7, false),
            new QuestionModel(R.string.q8, true),
            new QuestionModel(R.string.q9, true),
            new QuestionModel(R.string.q10, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.question_text);
        progressText = findViewById(R.id.progressing_text);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        progressBar =  findViewById(R.id.progressBar);

        setQuestionText();
        setProgressText();
        incrementarProgressBar();

        alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Congratulations, you finished the quiz!");
        alertBuilder.setMessage("What do you want to do next");
        alertBuilder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {finish();
            }});
        alertBuilder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contador = 0;
                setQuestionText();
                setProgressText();
                progressBar.setProgress(10);
            }});

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.true_button:
                resultadoQuiz(true);
                break;
            case R.id.false_button:
                resultadoQuiz(false);
                break;
        }
    }

    public void resultadoQuiz(boolean respuesta) {
        if (respuesta == questionBank[contador].getAnswer()) Toast.makeText(this, "Has acertado la respuesta!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "No has acertado la respuesta :(", Toast.LENGTH_SHORT).show();
        if ((contador) == questionBank.length -1) {
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        } else {
            contador ++;
            setQuestionText();
            setProgressText();
            incrementarProgressBar();
        }
    }

    public void setProgressText() {
        String frase = "Question "+(contador + 1)+" out of 10";
        progressText.setText(frase);
    }

    public void setQuestionText() {
        questionText.setText(questionBank[contador].getQuestionText());
    }

    public void incrementarProgressBar() {
        progressBar.incrementProgressBy(10);
    }

}