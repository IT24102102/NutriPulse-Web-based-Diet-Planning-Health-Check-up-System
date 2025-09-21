package com.healthpath.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bmi")
public class BMIController {
    @PostMapping("/calculate")
    public Map<String, Object> calculateBMI(@RequestBody Map<String, Double> request) {
        double weight = request.get("weight");
        double height = request.get("height") / 100.0; // cm → meters

        double bmi = weight / (height * height);
        String category;
        if (bmi < 18.5) category = "Underweight";
        else if (bmi < 24.9) category = "Normal weight";
        else if (bmi < 29.9) category = "Overweight";
        else category = "Obese";

        Map<String, Object> result = new HashMap<>();
        result.put("bmi", String.format("%.2f", bmi));
        result.put("category", category);
        result.put("mealPlan", "Sample meal plan here...");

        return result;
    }
}
