package com.healthpath.repository;

import com.healthpath.model.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
}