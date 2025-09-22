package com.healthpath.repositories;

import com.healthpath.models.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
}