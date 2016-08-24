package com.creation.adesh.mcassignment2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    private static TextView mHintText = null;
    private static final String RHinted = "HINT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        mHintText = (TextView) findViewById(R.id.hintTextView);
    }
    public void setHintShown(){
        Intent i = new Intent();
        i.putExtra(RHinted,true);
        setResult(Activity.RESULT_OK,i);
    }
    public void showHint(View view){
        setHintShown();
        mHintText.setText(R.string.hint);
    }
}
