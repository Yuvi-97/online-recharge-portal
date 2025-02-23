package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/RechargePlan")
public class RechargePlanController {
    @Autowired
    private RechargePlanService rechargePlanService;

    // Create a new recharge plan
    @PostMapping
    public ResponseEntity<RechargePlan> createRechargePlan(@RequestBody RechargePlan plan) {
        RechargePlan createdPlan = rechargePlanService.createRechargePlan(plan);
        return ResponseEntity.status(201).body(createdPlan);
    }

    // Retrieve all recharge plans
    @GetMapping
    public ResponseEntity<List<RechargePlan>> getAllRechargePlans() {
        return ResponseEntity.ok(rechargePlanService.getAllRechargePlans());
    }

    // Retrieve a recharge plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<RechargePlan> getRechargePlanById(@PathVariable Long id) {
        Optional<RechargePlan> plan = rechargePlanService.getRechargePlanById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing recharge plan
    @PutMapping("/{id}")
    public ResponseEntity<RechargePlan> updateRechargePlan(@PathVariable Long id, @RequestBody RechargePlan updatedPlan) {
        RechargePlan plan = rechargePlanService.updateRechargePlan(id, updatedPlan);
        return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-price-range")
    public List<RechargePlan> getPlansByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {
        return rechargePlanService.getPlansByPriceRange(minPrice, maxPrice);
    }
    
    // Delete a recharge plan by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRechargePlan(@PathVariable Long id) {
        rechargePlanService.deleteRechargePlan(id);
        return ResponseEntity.noContent().build();
    }
}
