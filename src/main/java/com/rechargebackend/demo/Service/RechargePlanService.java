package com.rechargebackend.demo.Service;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RechargePlanService {
    @Autowired
    private RechargePlanRepository rechargePlanRepository;

    public RechargePlan createRechargePlan(RechargePlan plan) {
        return rechargePlanRepository.save(plan);
    }

    public List<RechargePlan> getAllRechargePlans() {
        return rechargePlanRepository.findAll();
    }

    public Optional<RechargePlan> getRechargePlanById(Long id) {
        return rechargePlanRepository.findById(id);
    }

    public RechargePlan updateRechargePlan(Long id, RechargePlan updatedPlan) {
        return rechargePlanRepository.findById(id).map(plan -> {
            plan.setPlanName(updatedPlan.getPlanName());
            plan.setPlanDescription(updatedPlan.getPlanDescription());
            plan.setPlanDuration(updatedPlan.getPlanDuration());
            plan.setPrice(updatedPlan.getPrice());
            return rechargePlanRepository.save(plan);
        }).orElse(null);
    }

    public void deleteRechargePlan(Long id) {
        rechargePlanRepository.deleteById(id);
    }
}
