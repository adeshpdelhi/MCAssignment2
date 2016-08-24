package com.creation.adesh.mcassignment2;

import java.util.Random;

/**
 * Created by adesh on 8/14/16.
 */
public class Question {
    Integer number = 0;
    Boolean answer = true;
    private void setAnswer(){
        for(int i=2;i<number;i++)
            if(number%i==0)
                answer=false;
    }
    public Question(){
        number = new Random().nextInt(1000)+1;
        setAnswer();
    }

    public Question(Integer number){
        this.number = number;
        setAnswer();
    }

    Integer getNumber(){
        return number;
    }
    Boolean checkAnswer(Boolean givenAnswer){
        if(givenAnswer == answer)
            return true;
        else
            return false;
    }
    Boolean getAnswer(){
        return answer;
    }
}
