package com.healthpath.service;

import com.healthpath.model.BMIResponse;
import org.springframework.stereotype.Service;

@Service
public class BMIService {

    public BMIResponse calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        bmi = Math.round(bmi * 100.0) / 100.0;

        String category = getBMICategory(bmi);
        String mealPlan = getMealPlan(category);

        return new BMIResponse(bmi, category, mealPlan);
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal weight";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    private String getMealPlan(String category) {
        return switch (category) {
            case "Underweight" -> "High-calorie meals with protein shakes, nuts, dairy.";
            case "Normal weight" -> "Balanced meals with carbs, protein, veggies.";
            case "Overweight" -> "Low-carb, high-fiber meals, more greens.";
            case "Obese" -> "Strict low-calorie meals. Avoid sugar and oily food.";
            default -> "Standard meal plan.";
        };
    }
}
