package com.example.dietapp.model;

public class BMIResponse {
    private double bmi;
    private String category;
    private String mealPlan;

    public BMIResponse(double bmi, String category, String mealPlan) {
        this.bmi = bmi;
        this.category = category;
        this.mealPlan = mealPlan;
    }

    public double getBmi() { return bmi; }
    public String getCategory() { return category; }
    public String getMealPlan() { return mealPlan; }
}
