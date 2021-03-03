package com.example.rocketapp;

import com.google.firebase.firestore.Exclude;

public class IntCountTrial extends Trial {
    public static final String TYPE = "IntCount";

    private int posCount;

    public IntCountTrial() {
        posCount = 0;
    }

    public String getType() {
        return TYPE;
    }

    public void addPCount(){
        posCount = posCount + 1;
    }

    @Exclude
    public int getPCount(){
        return posCount;
    }
}