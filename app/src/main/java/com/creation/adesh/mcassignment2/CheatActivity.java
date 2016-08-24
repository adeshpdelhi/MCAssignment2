package com.creation.adesh.mcassignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static TextView mCheatView = null;
    private static Boolean mAnswer = false;
    private static Boolean mCheated = false;
    private static final String RCheated = "CHEATED";
    private static final String RAnswer = "ANSWER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mCheatView = (TextView)findViewById(R.id.cheatView);
        mAnswer = getIntent().getBooleanExtra(RAnswer,false);
        if(savedInstanceState!=null){
            mCheated = savedInstanceState.getBoolean(RCheated, false);
            mAnswer = savedInstanceState.getBoolean(RAnswer, false);
            Log.v("dot dot",mCheated+" "+mAnswer);
        }
    }

    @Override
    protected  void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(RCheated, mCheated);
        savedInstanceState.putBoolean(RAnswer, mAnswer);
    }


    private void setCheated(){
        Intent i = new Intent();
        i.putExtra(RCheated,true);
        setResult(RESULT_OK,i);
        mCheated = true;
    }

    private void showCheat(){
        if(mAnswer)
            mCheatView.setText("Prime number!");
        else
            mCheatView.setText("Not a prime number!");
    }
    public void cheat(View view){
        setCheated();
        showCheat();
    }
}
