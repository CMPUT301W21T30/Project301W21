package com.example.rocketapp;

import com.google.firebase.firestore.Exclude;

public class CountTrial extends Trial implements Comparable<CountTrial>{
    public static final String TYPE = "Count";

    private int numberCounted;

    public CountTrial(String description) {
        super(description);
        numberCounted = 0;
    }

    public CountTrial(String description, int number){
        super(description);
        numberCounted = number;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void addCount(){
        numberCounted = numberCounted + 1;
    }

    public int getCount(){
        return numberCounted;
    }

    @Override
    public int compareTo(CountTrial trial) {//references: https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/
        int compareCount = ((CountTrial)trial).getCount();
        return this.getCount() - compareCount;
    }
}