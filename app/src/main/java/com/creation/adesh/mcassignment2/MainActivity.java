package com.creation.adesh.mcassignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static TextView mQuestionText = null;
    private Integer number = null;
    private Question presentQuestion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionText = (TextView) findViewById(R.id.questionText);
        if(savedInstanceState == null) {
            presentQuestion= new Question();
            number = presentQuestion.getNumber();
        }
        else
        {
            number=savedInstanceState.getInt("number");
            presentQuestion = new Question(number);
        }

        mQuestionText.setText(number+" is a prime number.");
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("number",number);
    }
    public void answerTrue(View view){
        if(presentQuestion.checkAnswer(true)==true)
            Toast.makeText(getApplicationContext(),"Correct Response!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Incorrect Response!",Toast.LENGTH_SHORT).show();
    }
    public void answerFalse(View view){
        if(presentQuestion.checkAnswer(false)==true)
            Toast.makeText(getApplicationContext(),"Correct Response!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Incorrect Response!",Toast.LENGTH_SHORT).show();
    }
    public void next(View view){
        presentQuestion= new Question();
        number = presentQuestion.getNumber();
        mQuestionText.setText(number+" is a prime number.");
    }
}
