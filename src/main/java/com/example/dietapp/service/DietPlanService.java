package com.example.dietapp.service;

import com.example.dietapp.model.DietPlan;
import com.example.dietapp.repository.DietPlanRepository;
import com.example.dietapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietPlanService {
    @Autowired
    private DietPlanRepository planRepo;
    @Autowired
    private UserRepository userRepo;

    public DietPlan createPlan(Long userId, String planDetails) {
        DietPlan plan = new DietPlan();
        plan.setUserId(userId);
        plan.setPlanDetails(planDetails);
        plan.setApproved(false);
        return planRepo.save(plan);
    }

    public DietPlan approvePlan(Long planId) {
        DietPlan plan = planRepo.findById(planId).orElseThrow(() -> new RuntimeException("Plan not found"));
        plan.setApproved(true);
        return planRepo.save(plan);
    }
}