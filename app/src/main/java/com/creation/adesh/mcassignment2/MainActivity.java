package com.creation.adesh.mcassignment2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String RCheated = "CHEATED";
    private static final String RHinted = "HINT";
    private static final String RAnswer = "ANSWER";
    private static TextView mQuestionText = null;
    private Integer number = null;
    private Question presentQuestion = null;
    private static final int cheatRequestCode = 0;
    private static final int hintRequestCode = 1;
    private Boolean mCheated = false;
    private Boolean mHintShown = false;
    private static final String TAG = "MainActivity";
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
            number = savedInstanceState.getInt("number");
            presentQuestion = new Question(number);
            mCheated = savedInstanceState.getBoolean(RCheated, false);
            mHintShown = savedInstanceState.getBoolean(RHinted, false);
        }

        mQuestionText.setText(getString(R.string.questionText,number));
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("number",number);
        savedInstanceState.putBoolean(RCheated,mCheated);
        savedInstanceState.putBoolean(RHinted,mHintShown);
    }
    public void answerTrue(View view){
        if(!checkIntegrity()) return;
        verifyAnswer(true);
    }
    public void answerFalse(View view){
        if(!checkIntegrity()) return;
        verifyAnswer(false);
    }
    public Boolean checkIntegrity(){
        checkHintShown();
        checkCheating();
        if(mHintShown || mCheated)
            return false;
        return true;
    }
    private void verifyAnswer(Boolean answer){
        if(presentQuestion.checkAnswer(answer))
            Toast.makeText(getApplicationContext(),"Correct Response!",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Incorrect Response!",Toast.LENGTH_SHORT).show();
    }

    public Boolean checkHintShown(){
        if(mHintShown){
            Toast.makeText(getApplicationContext(),"You already received hint!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public Boolean checkCheating(){
        if(mCheated){
            Toast.makeText(getApplicationContext(),"You already cheated!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void next(View view){
        presentQuestion= new Question();
        number = presentQuestion.getNumber();
        mQuestionText.setText(getString(R.string.questionText,number));
        mCheated = false;
        mHintShown = false;
    }

    public void showHint(View view){
        Intent i = new Intent(MainActivity.this,HintActivity.class);
        startActivityForResult(i,hintRequestCode);
    }

    public void showCheat(View view){
        Intent i = new Intent(MainActivity.this,CheatActivity.class);
        i.putExtra(RAnswer,presentQuestion.getAnswer());
        startActivityForResult(i,cheatRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i){

        if(requestCode==cheatRequestCode){
            if(resultCode == Activity.RESULT_OK && i!=null){
                mCheated = i.getBooleanExtra(RCheated,false);
                if(mCheated)
                    Toast.makeText(getApplicationContext(),"You cheated!",Toast.LENGTH_SHORT).show();
            }
        }


        if(requestCode==hintRequestCode){
            if(resultCode == Activity.RESULT_OK && i!=null){
                mHintShown = i.getBooleanExtra(RHinted,false);
                if(mHintShown)
                    Toast.makeText(getApplicationContext(),"You received hint!",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
